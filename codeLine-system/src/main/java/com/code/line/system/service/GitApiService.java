package com.code.line.system.service;

import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.dto.BranchDto;
import com.codeline.framwork.dto.MergeRequestDto;
import com.codeline.framwork.dto.ReleaseDto;
import com.codeline.framwork.dto.TagDto;
import com.codeline.framwork.exception.SysException;
import org.gitlab4j.api.models.Member;

/**
 * @author: syl
 * @Date: 2022/7/7 00:19
 * @Description: git仓库操作
 */
public interface GitApiService {

    GitStorageType getStorageType();

    public boolean addMember(String projectPath,Long assigneeId) throws SysException;

    public boolean addHook(String projectPath,String hookUrl) throws SysException;

    BranchDto createBranch(String gitUrl,String branchName, String ref) throws SysException;

    MergeRequestDto createMerge(String gitUrl,String sourceBranch,String targetBranch,String title, String description, Long assigneeId)throws SysException;

    MergeRequestDto acceptMergeRequest(String gitUrl, Long mergeRequestIid) throws SysException;

    TagDto createTag(String projectPath, String tagName,String ref) throws SysException;

    ReleaseDto createRelease(String projectPath, String tagName, String releaseName,String description) throws SysException;

}
