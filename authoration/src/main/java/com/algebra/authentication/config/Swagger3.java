package com.algebra.authentication.config;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author al
 * @desc swagger3接口访问地址： http://[IP]:[port]/swagger-ui/index.html
 */
@Configuration
@EnableOpenApi
public class Swagger3 {

    @Autowired
    private DemoServerSystemProperties properties;

    @Bean
    public Docket swaggerApi() {
        DemoSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage())
                .securitySchemes(securityScheme2(swagger))
                .securityContexts(securityContext2(swagger));
    }

    private ApiInfo apiInfo(DemoSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("Token")
                .required(false)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
//                .example(new ExampleBuilder().value("123").build())
                .build());
        /*parameters.add(new RequestParameterBuilder()
                .name("appId")
                .description("唯一id")
                .required(false)
                .in(ParameterType.QUERY)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        parameters.add(new RequestParameterBuilder()
                .name("lang")
                .description("语言")
                .required(false)
                .in(ParameterType.PATH)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());*/
        // ... ...
        return parameters;
    }

    //生成通用响应信息
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
        return responseList;
    }

    // --------------------------------以下为安全配置项----------------------------------------

    private SecurityScheme securityScheme(DemoSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());

        return new OAuthBuilder()
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private List<SecurityScheme> securityScheme2(DemoSwaggerProperties swagger) {

        List<SecurityScheme> schemes = new ArrayList<>();
        // 全局Token
        ApiKey token = new ApiKey(swagger.getHeader(), swagger.getHeader(),"header");
        schemes.add(token);
        return schemes;
    }

    private SecurityContext securityContext(DemoSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityContext> securityContext2(DemoSwaggerProperties swagger) {
        List<SecurityContext> contexts = new ArrayList<>();
        SecurityContext context2 = SecurityContext.builder()
                .securityReferences(defaultAuth(swagger))
                .forPaths(PathSelectors.any())
                .build();
        contexts.add(context2);
        return contexts;
    }

    private AuthorizationScope[] scopes(DemoSwaggerProperties swagger) {
        String[] scopesStr = swagger.getScope().split(",");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[scopesStr.length];
        for (int i = 0; i < scopesStr.length; i++) {
            authorizationScopes[i] = new AuthorizationScope(scopesStr[i], StringUtils.EMPTY);
        }
        return authorizationScopes;
    }

    List<SecurityReference> defaultAuth(DemoSwaggerProperties swagger) {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        //验证增加（有许多教程说明中这个地方是Authorization,导致不能带入全局token，因为securitySchemes()方法中header写入token，所以这个地方我改为token就可以了）
        securityReferences.add(new SecurityReference(swagger.getHeader(), authorizationScopes));
        return securityReferences;
    }

}
