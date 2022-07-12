package com.code.line.system.service.impl;

import com.code.line.system.service.GitApiService;
import com.code.line.system.service.ISysConfigService;
import com.codeline.framwork.api.gitlab.GitLabTools;
import com.codeline.framwork.constant.GitStorageType;
import com.codeline.framwork.dto.BranchDto;
import com.codeline.framwork.exception.SysException;
import com.codeline.framwork.request.BaseConfigBo;
import com.codeline.framwork.response.ApiResult;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
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
        BranchDto branchDto = new BranchDto();
        try {
            Branch main = instance.createBranch(gitUrl, branchName, ref);
            branchDto.setName(main.getWebUrl());
            branchDto.setName(main.getName());
        } catch (SysException e) {
            throw new SysException("创建分支失败",e);
        }
        return branchDto;
    }


    //System.out.println("createBranch success ="+ JSON.toJSONString(main));

    //MergeRequest merge = instance.createMerge(gitUrl, "dev1.0", "main", "dev1.0 merge test", "merge test");
    //System.out.println("createMerge success ="+ JSON.toJSONString(merge));
    //System.out.println("createMerge success MR_ID="+ merge.getIid());


    //MergeRequest mergeRequest = instance.acceptMergeRequest(gitUrl, 1l);
    //System.out.println("acceptMergeRequest success ="+ JSON.toJSONString(mergeRequest));


    //Tag tag = instance.createTag(gitUrl, "tag_1.1", "main");
    //System.out.println("createTag success ="+ JSON.toJSONString(tag));

    //Release release = instance.createRelease(gitUrl, "tag_1.2", "release_1.3","备注");
    //System.out.println("createRelease success ="+ JSON.toJSONString(release));


    private GitLabTools init() throws SysException {
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
