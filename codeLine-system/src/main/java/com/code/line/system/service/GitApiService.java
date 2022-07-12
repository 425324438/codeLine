package com.code.line.system.service;

import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.dto.BranchDto;
import com.codeline.framwork.exception.SysException;

/**
 * @author: syl
 * @Date: 2022/7/7 00:19
 * @Description: git仓库操作
 */
public interface GitApiService {

    GitStorageType getStorageType();

    BranchDto createBranch(String gitUrl,String branchName, String ref) throws SysException;
    //Branch main = instance.createBranch(gitUrl, "dev1.0", "main");


}
