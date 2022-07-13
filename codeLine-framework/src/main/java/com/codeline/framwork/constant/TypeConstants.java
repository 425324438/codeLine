package com.codeline.framwork.constant;

/**
 * @author: syl
 * @Date: 2022/7/14 00:43
 * @Description:
 */
public interface TypeConstants {

    public interface SprintExecStatus {
        public int execSuccess = 0;
        public int execError   = 1;
    }

    public interface SprintConfigKey {
        public String mainBranchName = "mainBranchName";
        public String AssigneeId = "AssigneeId";
    }

    public interface SprintActionParamJsonKey {
        public String MergeIid = "MergeIid";

    }
}
