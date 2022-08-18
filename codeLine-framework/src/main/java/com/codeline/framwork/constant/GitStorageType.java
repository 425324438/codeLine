package com.codeline.framwork.constant;

/**
 * @author: syl
 * @Date: 2022/7/7 00:20
 * @Description:
 */
public enum GitStorageType {
    gitlab("gitlab"),
    github("github"),
    gitee("gitee"),
    ;

    private String type;

    GitStorageType(String type) {
        this.type = type;
    }

    public static GitStorageType getByName(String name){
        for (GitStorageType value : GitStorageType.values()) {
            if (value.getType().equals(name))
                return value;
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
