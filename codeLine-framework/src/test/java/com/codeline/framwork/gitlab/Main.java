package com.codeline.framwork.gitlab;

import com.alibaba.fastjson.JSON;
import com.codeline.framwork.api.gitlab.GitLabTools;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;

import java.util.List;

/**
 * @author: syl
 * @Date: 2022/6/27 00:31
 * @Description:
 */
public class Main {
    private static String token = "glpat-ENJv9XZSgHHAtdpQkscP";
    private static String url = "https://gitlab.com/";

    public static void main(String[] args) throws GitLabApiException {
        String gitUrl = "https://gitlab.com/425324438/test";
        GitLabTools instance = GitLabTools.getInstance(url, token);
        Branch master = instance.createBranch(gitUrl, "dev1.1.5", "master");
        System.out.println("success ="+ JSON.toJSONString(master));

    }
}
