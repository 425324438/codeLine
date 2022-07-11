package com.codeline.framwork.constant;

import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/7/10 22:49
 * @Description:
 */

public enum SprintTypeEnums {
    urgent("紧急迭代"),
    routine("常规迭代"),
    ;

    private String typeName;

    SprintTypeEnums(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
