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

        //MergeRequest merge = instance.createMerge(gitUrl, "dev1.0", "main", "dev1.0 merge test", "merge test");
        //System.out.println("createMerge success ="+ JSON.toJSONString(merge));
        //System.out.println("createMerge success MR_ID="+ merge.getIid());


        //MergeRequest mergeRequest = instance.acceptMergeRequest(gitUrl, 1l);
        //System.out.println("acceptMergeRequest success ="+ JSON.toJSONString(mergeRequest));


        //Tag tag = instance.createTag(gitUrl, "tag_1.1", "main");
        //System.out.println("createTag success ="+ JSON.toJSONString(tag));

        Release release = instance.createRelease(gitUrl, "tag_1.2", "release_1.3","备注");
        System.out.println("createRelease success ="+ JSON.toJSONString(release));

    }

}
