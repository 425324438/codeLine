package com.codeline.framwork.gitlab;

import com.alibaba.fastjson.JSON;
import com.codeline.framwork.api.gitlab.GitLabTools;
import com.codeline.framwork.exception.SysException;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.*;

/**
 * @author: syl
 * @Date: 2022/6/28 22:13
 * @Description:
 */
public class Main {

    private static String token = "glpat-C28EvDXB9zXNHosy9yFt";
    private static String url = "https://gitlab.com/";

    static String gitUrl = "https://gitlab.com/codeline1/codelineTest";
    static GitLabTools instance = GitLabTools.getInstance(url, token);

    public static void main(String[] args) throws GitLabApiException, SysException {
        //Branch main = instance.createBranch(gitUrl, "dev1.0", "main");
        //System.out.println("createBranch success ="+ JSON.toJSONString(main));

        MergeRequest merge = instance.createMerge(gitUrl, "dev1.0", "main", "dev1.0 merge test", "merge test");
        //{"assignees":[],"author":{"avatarUrl":"https://secure.gravatar.com/avatar/7ff2d0cadd36ab365c2f205b608fb54e?s=80&d=identicon","id":11987312,"name":"code line","state":"active","username":"codeLineTest","webUrl":"https://gitlab.com/codeLineTest"},"blockingDiscussionsResolved":true,"createdAt":1657726847580,"description":"merge test","diffRefs":{"baseSha":"4fd97049c0fe2587ffac8907f16ed3a7339beca5","headSha":"4fd97049c0fe2587ffac8907f16ed3a7339beca5","startSha":"2caeb542b298b8054aea879c7ec230df91809f27"},"downvotes":0,"hasConflicts":false,"id":165715509,"iid":4,"labels":[],"mergeStatus":"checking","mergeWhenPipelineSucceeds":false,"projectId":37552469,"references":{"full":"codeline1/codelineTest!4","relative":"!4","short":"!4"},"reviewers":[],"sha":"4fd97049c0fe2587ffac8907f16ed3a7339beca5","sourceBranch":"dev1.0","sourceProjectId":37552469,"squash":false,"state":"opened","subscribed":true,"targetBranch":"main","targetProjectId":37552469,"taskCompletionStatus":{"completedCount":0,"count":0},"timeStats":{"timeEstimate":0,"totalTimeSpent":0},"title":"dev1.0 merge test","updatedAt":1657726847580,"upvotes":0,"userNotesCount":0,"webUrl":"https://gitlab.com/codeline1/codelineTest/-/merge_requests/4","workInProgress":false}
        System.out.println("createMerge success ="+ JSON.toJSONString(merge));
        System.out.println("createMerge success MR_ID="+ merge.getIid());


        //MergeRequest mergeRequest = instance.acceptMergeRequest(gitUrl, 1l);
        //System.out.println("acceptMergeRequest success ="+ JSON.toJSONString(mergeRequest));


        //Tag tag = instance.createTag(gitUrl, "tag_1.1", "main");
        //System.out.println("createTag success ="+ JSON.toJSONString(tag));

        Release release = instance.createRelease(gitUrl, "tag_1.2", "release_1.3","备注");
        System.out.println("createRelease success ="+ JSON.toJSONString(release));

    }

}
