package com.codeline.framwork.constant;

/**
 * @author: syl
 * @Date: 2022/7/7 00:20
 * @Description:
 */
public enum GitStorageType {
    gitlab,
    github,
    gitee,
    ;

    public static GitStorageType getByName(String name){
        for (GitStorageType value : GitStorageType.values()) {
            if (value.name().equals(name))
                return value;
        }
        return null;
    }
}
