package com.code.line.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.line.system.service.GitApiService;
import com.code.line.system.service.ISysConfigService;
import com.codeline.framwork.api.gitlab.GitLabTools;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.dto.BranchDto;
import com.codeline.framwork.dto.MergeRequestDto;
import com.codeline.framwork.dto.ReleaseDto;
import com.codeline.framwork.dto.TagDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.request.BaseConfigBo;
import com.codeline.framwork.response.ApiResult;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Release;
import org.gitlab4j.api.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: syl
 * @Date: 2022/7/7 00:21
 * @Description:
 */
@Service
public class GitLabApiServiceImpl implements GitApiService {

    private static GitLabTools instance = null;

    @Autowired
    private ISysConfigService sysConfigService;

    @Override
    public GitStorageType getStorageType() {
        return GitStorageType.gitlab;
    }

    @Override
    public BranchDto createBranch(String gitUrl, String branchName, String ref) throws SysException {
        init();
        BranchDto branchDto = new BranchDto();
        try {
            Branch main = instance.createBranch(gitUrl, branchName, ref);
            branchDto.setWebUrl(main.getWebUrl());
            branchDto.setName(main.getName());
            return branchDto;
        } catch (SysException e) {
            throw new SysException("创建分支失败，"+e.getMessage(),e);
        }
    }

    @Override
    public MergeRequestDto createMerge(String gitUrl, String sourceBranch, String targetBranch, String title, String description, Long assigneeId)
            throws SysException {
        init();
        MergeRequestDto mergeRequest = new MergeRequestDto();
        try {
            MergeRequest merge = instance.createMerge(gitUrl, sourceBranch, targetBranch, title, description,assigneeId);
            mergeRequest.setWebUrl(merge.getWebUrl());
            mergeRequest.setSourceBranch(merge.getSourceBranch());
            mergeRequest.setTargetBranch(merge.getTargetBranch());
            mergeRequest.setTitle(merge.getTitle());
            mergeRequest.setIid(merge.getIid());
            return mergeRequest;
        } catch (SysException e) {
            throw new SysException("创建MergeRequest失败，"+e.getMessage(),e);
        }
    }

    @Override
    public MergeRequestDto acceptMergeRequest(String gitUrl, Long mergeRequestIid) throws SysException {
        init();
        MergeRequestDto mergeRequest = new MergeRequestDto();
        try {
            MergeRequest merge = instance.acceptMergeRequest(gitUrl, mergeRequestIid);
            mergeRequest.setWebUrl(merge.getWebUrl());
            mergeRequest.setSourceBranch(merge.getSourceBranch());
            mergeRequest.setTargetBranch(merge.getTargetBranch());
            mergeRequest.setTitle(merge.getTitle());
            mergeRequest.setIid(merge.getIid());
            return mergeRequest;
        } catch (SysException e) {
            throw new SysException("MergeRequest自动合并失败，"+e.getMessage(),e);
        }
    }

    @Override
    public TagDto createTag(String projectPath, String tagName, String ref) throws SysException {
        init();
        TagDto tagDto = new TagDto();
        try {
            Tag tag = instance.createTag(projectPath, tagName, ref);
            tagDto.setName(tag.getName());
            tagDto.setWebUrl(tag.getCommit().getWebUrl());
            return tagDto;
        } catch (SysException e) {
            throw new SysException("创建Tag失败，"+e.getMessage(),e);
        }
    }

    @Override
    public ReleaseDto createRelease(String projectPath, String tagName, String releaseName, String description)
            throws SysException {
        init();
        ReleaseDto releaseDto = new ReleaseDto();
        try {
            Release release = instance.createRelease(projectPath, tagName, releaseName, description);
            releaseDto.setTagName(release.getTagName());
            releaseDto.setName(release.getName());
            return releaseDto;
        } catch (SysException e) {
            throw new SysException("创建Release失败，"+e.getMessage(),e);
        }
    }


    private GitLabTools init() throws SysException {
        if (instance != null){
            return instance;
        }
        ApiResult<BaseConfigBo> gitLabBaseConfig = sysConfigService.getGitLabBaseConfig();
        if (gitLabBaseConfig.isErr()){
            throw new SysException("查询GitLab配置失败");
        }
        if (gitLabBaseConfig.isSuccess()){
            BaseConfigBo data = gitLabBaseConfig.getData();
            instance = GitLabTools.getInstance(data.getUrl(), data.getToken());
        }
        return instance;
    }


}
