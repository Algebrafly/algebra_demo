package com.algebra.demo.util;

import com.algebra.demo.dto.Tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/12/18 15:10
 * @description
 */
public class MapTreeUtil {

    public List<Tree> menuCommon;
    public List<Object> list = new ArrayList<>();

    public List<Object> menuList(List<Tree> menu) {
        this.menuCommon = menu;
        for (Tree x : menu) {
            Map<String, Object> mapArr = new LinkedHashMap<>();
            if ("0".equals(x.getPId())) {
                mapArr.put("id", x.getId());
                mapArr.put("name", x.getName());
                mapArr.put("pid", x.getPId());
                mapArr.put("childList", menuChild(x.getId()));
                list.add(mapArr);
            }
        }
        return list;
    }

    public List<?> menuChild(String id) {
        List<Object> lists = new ArrayList<>();
        for (Tree a : menuCommon) {
            Map<String, Object> childArray = new LinkedHashMap<>();
            if (a.getPId().equals(id)) {
                childArray.put("id", a.getId());
                childArray.put("name", a.getName());
                childArray.put("pid", a.getPId());
                childArray.put("childList", menuChild(a.getId()));
                lists.add(childArray);
            }
        }
        return lists;
    }

}
