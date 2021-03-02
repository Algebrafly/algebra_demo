package com.algebra.demo.auth;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;
import org.apache.ibatis.mapping.BoundSql;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class SqlHandler {

    private BoundSql boundSql;

    protected SqlHandler(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    public String getSql() {
        return boundSql.getSql();
    }

    public Object getParameter() {
        return boundSql.getParameterObject();
    }

    public String concatAuthSql(String originalSql, String sqlAlias, String sqlFilterData) {
        try {
            Select selectStatement = (Select) CCJSqlParserUtil.parse(originalSql);
            if (selectStatement.getSelectBody() instanceof PlainSelect) {
                PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
                // todo: to filter table
                Expression originalWhere = plainSelect.getWhere();
                Expression buildExpress = concatAuthExpression(originalWhere,sqlAlias,sqlFilterData);
                plainSelect.setWhere(buildExpress);

                return plainSelect.toString();
            } else if (selectStatement.getSelectBody() instanceof SetOperationList) {
                SetOperationList setOperationList = (SetOperationList) selectStatement.getSelectBody();
                // todo: don't known how to resole
                return setOperationList.toString();
            } else if (selectStatement.getSelectBody() instanceof WithItem) {
                // todo: don't known how to resole
                return originalSql;
            } else {
                return originalSql;
            }
        } catch (JSQLParserException e) {
            log.warn("failed to concat orderBy from IPage, exception=" + e.getMessage());
        }
        return originalSql;
    }

    /**
     * 通过Expression拼接权限条件
     * @param originalWhere
     * @return
     */
    public Expression concatAuthExpression(Expression originalWhere, String sqlAlias, String sqlFilterData) {
        final InExpression inExpression = new InExpression();

        List<String> filterList = JSON.parseObject(sqlFilterData, List.class);
        // if filter data is empty, return origin condition
        if (CollectionUtils.isEmpty(filterList)) {
            return originalWhere;
        }
        inExpression.setLeftExpression(new Column(sqlAlias));
        final ExpressionList itemsList = new ExpressionList();
        final List<Expression> inValues =
                filterList
                        .stream()
                        .map(e-> new StringValue(String.valueOf(e)))
                        .collect(Collectors.toList());
        itemsList.setExpressions(inValues);
        inExpression.setRightItemsList(itemsList);
        if (Objects.isNull(originalWhere)) {
            return inExpression;
        }
        Expression finalExpress = new AndExpression(originalWhere,inExpression);
        return finalExpress;
    }

}
