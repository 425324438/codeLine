package com.code.line.system.constant;

/**
 * @author: syl
 * @Date: 2022/7/12 01:28
 * @Description:
 */
public enum ActionBeanTypeName {
    CreateBranch("CreateBranch","创建分支"),
    CreateMerge("CreateMerge","创建MR"),
    AcceptMerge("AcceptMerge","合并MR"),
    CreateTag("CreateTag","创建Tag"),
    CreateRelease("CreateRelease","创建Release"),
    ;

    private String beanCode;
    private String beanName;

    public String getBeanCode() {
        return beanCode;
    }

    public String getBeanName() {
        return beanName;
    }

    ActionBeanTypeName(String beanCode, String beanName) {
        this.beanCode = beanCode;
        this.beanName = beanName;
    }

    public static ActionBeanTypeName getByBeanCode(String beanCode){
        for (ActionBeanTypeName value : ActionBeanTypeName.values()) {
            if (value.getBeanCode().equals(beanCode)){
                return value;
            }
        }
        return null;
    }
}
