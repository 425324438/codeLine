package com.code.line.system;

/**
 * @author: syl
 * @Date: 2022/8/30 01:15
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        String git = "https://gitlab.com/codeline1/codelineTest";
        //System.out.println(git.split(".com/")[1].split("/")[0]);

        if (git.split(".com/").length > 1){
            //String  gitGroup =  git.split(".com/")[1];
            String  gitGroup =  git.split(".com/")[1].split("/")[0];
            System.out.println(gitGroup);
        }
    }
}
