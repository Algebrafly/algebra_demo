package com.algebra.demo.study.strategyfactory;

/**
 * @author al
 * @date 2020/1/19 9:46
 * @description
 */
public enum UserType {
    NORMAL("normal","0"),
    SILVER_VIP("silver","1"),
    GOLD_VIP("gold","2"),
    PLATINUM_VIP("platinum","3")
    ;
    private String type;
    private String code;
    UserType(String type, String code){
        this.code = code;
        this.type = type;
    }
    public String getCode(){
        return code;
    }
}
