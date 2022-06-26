package com.codeline.framwork.api.gitlab;

import com.codeline.framwork.exception.SysRunException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.*;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Release;
import org.gitlab4j.api.models.Tag;
import org.springframework.stereotype.Component;

/**
 * @author: syl
 * @Date: 2022/6/26 23:06
 * @Description:
 */
@Slf4j
@Data
public class GitLabTools {

    private volatile static GitLabTools gitLabTools;

    private GitLabApi gitLabApi;
    private String gitLabUrl;
    private String gitLabToken;

    private GitLabTools (){};


    public Branch createBranch(String projectPath,String branchName, String ref){
        RepositoryApi repositoryApi = gitLabApi.getRepositoryApi();
        try {
            return repositoryApi.createBranch(projectPath, branchName, ref);
        } catch (GitLabApiException e) {
            log.info("gitLab createBranch Exception, projectPath={},branchName={},ref={}",projectPath,branchName,ref);
            log.info("gitLab createBranch Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 创建mr
     * @param projectPath 项目路径
     * @param sourceBranch 源分支
     * @param targetBranch 目标分支
     * @param title 标题
     * @param description   描述
     */
    public MergeRequest createMerge(String projectPath, String sourceBranch, String targetBranch, String title, String description){
        MergeRequestApi mergeRequestApi = gitLabApi.getMergeRequestApi();
        try {
            return mergeRequestApi.createMergeRequest(projectPath, sourceBranch, targetBranch,
                    title, description, null);
        } catch (GitLabApiException e) {
            log.info("gitLab createMerge Exception, projectPath={},sourceBranch={},targetBranch={},title={},description={}",projectPath,sourceBranch,targetBranch,title,description);
            log.info("gitLab createMerge Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 批准合并请求
     * @param projectPath 项目路径
     * @param mergeRequestIid id
     */
    public MergeRequest approveMergeRequest(String projectPath, Long mergeRequestIid){
        MergeRequestApi mergeRequestApi = gitLabApi.getMergeRequestApi();
        try {
            return mergeRequestApi.approveMergeRequest(projectPath, mergeRequestIid, "");
        } catch (GitLabApiException e) {
            log.info("gitLab approveMergeRequest Exception, projectPath={},mergeRequestIid={}",projectPath,mergeRequestIid);
            log.info("gitLab approveMergeRequest Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 创建Tag
     * @param projectPath 项目路径
     * @param tagName   名称，一般都是版本号
     * @param ref   来源，一般是指某一个分支
     */
    public Tag createTag(String projectPath, String tagName,String ref){
        TagsApi tagsApi = gitLabApi.getTagsApi();
        try {
            return tagsApi.createTag(projectPath, tagName, ref);
        } catch (GitLabApiException e) {
            log.info("gitLab createTag Exception, projectPath={},tagName={}, ref={}",projectPath, tagName, ref);
            log.info("gitLab createTag Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 创建release
     * @param projectPath 项目路径
     * @param tagName   Tag名称
     * @param releaseNotes 说明，支持MarkDown形式
     */
    public Release createRelease(String projectPath, String tagName, String releaseNotes){
        TagsApi tagsApi = gitLabApi.getTagsApi();
        try {
            return tagsApi.createRelease(projectPath, tagName, releaseNotes);
        } catch (GitLabApiException e) {
            log.info("gitLab createRelease Exception, projectPath={},tagName={}, releaseNotes={}",projectPath, tagName, releaseNotes);
            log.info("gitLab createRelease Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }





    public static GitLabTools getInstance(String gitLabUrl,String gitLabToken){
        if (gitLabTools == null){
            synchronized (GitLabTools.class){
                if (gitLabTools == null){
                    gitLabTools = new GitLabTools();
                    GitLabApi gitLabApi = new GitLabApi(gitLabUrl, gitLabToken);
                    gitLabTools.setGitLabApi(gitLabApi);
                }
            }
        }
        return gitLabTools;
    }
}
