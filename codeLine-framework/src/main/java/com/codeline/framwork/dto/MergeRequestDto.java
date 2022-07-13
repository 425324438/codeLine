package com.codeline.framwork.dto;

import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/7/13 23:42
 * @Description:
 */
@Data
public class MergeRequestDto {

    /*
    "webUrl": "https://gitlab.com/codeline1/codelineTest/-/merge_requests/4",
    "sourceBranch": "dev1.0",
    "targetBranch": "main",
    "title": "dev1.0 merge test",
     */
    private String webUrl;
    private String sourceBranch;
    private String targetBranch;
    private String title;
    private Long   iid;

}
