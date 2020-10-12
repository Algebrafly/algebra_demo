package com.algebra.authentication.util.language;

/**
 * @author al
 * @date 2020/7/15 9:32
 * @description
 */
public enum LanguageTypeEnum {

    /**
     * 语言类型
     */
    CHINESE("zh-CN","zh_CN", "zh_cn"),
    ENGLISH("en-US","en_US", "en"),
    SPANISH("es-ES","es_ES", "es"),
    JAPANESE("ja_JP","ja_JP", "ja")

    ;

    LanguageTypeEnum(String name, String type, String code){
        this.name = name;
        this.type = type;
        this.code = code;
    }

    private String name;

    private String type;

    private String code;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static String getTypeByName(String name){
        if(name == null){
            return ENGLISH.getType();
        }
        for (LanguageTypeEnum value : LanguageTypeEnum.values()) {
            if(value.getName().equals(name)){
                return value.getType();
            }
        }
        return ENGLISH.getType();
    }

    public static String getCodeByName(String name){
        if(name == null){
            return ENGLISH.getCode();
        }
        for (LanguageTypeEnum value : LanguageTypeEnum.values()) {
            if(value.getName().equals(name)){
                return value.getCode();
            }
        }
        return null;
    }

    public static String getCodeByType(String type){
        if(type == null){
            return ENGLISH.getCode();
        }
        for (LanguageTypeEnum value : LanguageTypeEnum.values()) {
            if(value.getType().equals(type)){
                return value.getCode();
            }
        }
        return null;
    }

}
