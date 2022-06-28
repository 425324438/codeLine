package com.codeline.framwork.gitlab;

import com.alibaba.fastjson.JSON;
import com.codeline.framwork.api.gitlab.GitLabTools;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Release;
import org.gitlab4j.api.models.Tag;

/**
 * @author: syl
 * @Date: 2022/6/28 22:13
 * @Description:
 */
public class Main {

    private static String token = "";
    private static String url = "";

    static String gitUrl = "";
    static GitLabTools instance = GitLabTools.getInstance(url, token);

    public static void main(String[] args) throws GitLabApiException {
        Branch master = instance.createBranch(gitUrl, "dev1.1.7", "tag_1.1.6");
        System.out.println("Branch success ="+ JSON.toJSONString(master));

        //Tag master = instance.createTag(gitUrl, "tag_1.1.6", "master");
        //System.out.println("Tag success ="+ JSON.toJSONString(master));

    }
}
