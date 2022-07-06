package com.codeline.framwork.api.gitlab;

import com.codeline.framwork.exception.SysRunException;
import jdk.nashorn.internal.ir.ReturnNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.gitlab4j.api.*;
import org.gitlab4j.api.models.*;

import java.util.List;

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


    /**
     * 创建分支
     * @param projectPath gitLab中项目的访问地址webUrl 例：https://gitlab.com/425324438/test
     * @param branchName  新分支名称
     * @param ref         基于某个分支/tag创建
     * @return
     */
    public Branch createBranch(String projectPath,String branchName, String ref) throws SysRunException {
        RepositoryApi repositoryApi = gitLabApi.getRepositoryApi();
        try {
            return repositoryApi.createBranch(getProjectUrl(projectPath), branchName, ref);
        } catch (GitLabApiException e) {
            log.info("gitLab createBranch Exception, projectPath={},branchName={},ref={}",projectPath,branchName,ref);
            log.info("gitLab createBranch Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 创建MR
     * @param projectPath 项目路径
     * @param sourceBranch 源分支
     * @param targetBranch 目标分支
     * @param title 标题
     * @param description   描述
     */
    public MergeRequest createMerge(String projectPath, String sourceBranch, String targetBranch, String title, String description)
            throws SysRunException {
        MergeRequestApi mergeRequestApi = gitLabApi.getMergeRequestApi();
        try {
            return mergeRequestApi.createMergeRequest(getProjectUrl(projectPath), sourceBranch, targetBranch,
                    title, description, null);
        } catch (GitLabApiException e) {
            log.info("gitLab createMerge Exception, projectPath={},sourceBranch={},targetBranch={},title={},description={}",projectPath,sourceBranch,targetBranch,title,description);
            log.info("gitLab createMerge Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 合并MR请求
     * @param projectPath 项目路径
     * @param mergeRequestIid id
     */
    public MergeRequest acceptMergeRequest(String projectPath, Long mergeRequestIid) throws SysRunException {
        MergeRequestApi mergeRequestApi = gitLabApi.getMergeRequestApi();
        try {
            return mergeRequestApi.acceptMergeRequest(getProjectUrl(projectPath), mergeRequestIid);
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
    public Tag createTag(String projectPath, String tagName,String ref) throws SysRunException {
        TagsApi tagsApi = gitLabApi.getTagsApi();
        try {
            return tagsApi.createTag(getProjectUrl(projectPath), tagName, ref);
        } catch (GitLabApiException e) {
            log.info("gitLab createTag Exception, projectPath={},tagName={}, ref={}",projectPath, tagName, ref);
            log.info("gitLab createTag Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 查询项目的 Tags
     * @param projectPath 项目路径
     * @return
     */
    public List<Tag> tagList(String projectPath) throws SysRunException {
        TagsApi tagsApi = gitLabApi.getTagsApi();
        try {
            return tagsApi.getTags(getProjectUrl(projectPath));
        } catch (GitLabApiException e) {
            log.info("gitLab tagList Exception, projectPath={}",projectPath);
            log.info("gitLab tagList Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }

    /**
     * 创建release
     * @param projectPath 项目路径
     * @param tagName   Tag名称
     * @param releaseName 说明，支持MarkDown形式
     * @exception  同一个Tag只能创建一次Release，如果重复创建则会抛出异常：Release already exists
     */
    public Release createRelease(String projectPath, String tagName, String releaseName,String description)
            throws SysRunException {
        ReleasesApi releasesApi = gitLabApi.getReleasesApi();
        try {
            ReleaseParams releaseParams = new ReleaseParams();
            releaseParams.setName(releaseName);
            releaseParams.setTagName(tagName);
            releaseParams.setDescription(description);

            return releasesApi.createRelease(getProjectUrl(projectPath), releaseParams);
        } catch (GitLabApiException e) {
            log.info("gitLab createRelease Exception, projectPath={},tagName={}, releaseName={}",projectPath, tagName, releaseName);
            log.info("gitLab createRelease Exception, errMsg={},e={}",e.getMessage(),e);
            throw new SysRunException(e.getMessage(),e);
        }
    }


    private String getProjectUrl(String projectPath){
        if (StringUtils.isBlank(projectPath))
            throw new NullPointerException("projectPath is null");
        String[] split = projectPath.split("/");
        return split[split.length-2] +"/"+ split[split.length-1];
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
