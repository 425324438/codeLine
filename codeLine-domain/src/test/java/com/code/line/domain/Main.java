package com.code.line.domain;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.codeline.framwork.constant.SprintEnvStatusEnums;
import com.codeline.framwork.constant.SprintTypeEnums;

import java.util.Date;
import java.util.Random;

/**
 * @author: syl
 * @Date: 2022/11/13 00:04
 * @Description:
 */
public class Main {
    public static void main(String[] args) {


        String name = "迭代名称_";
        SprintTypeEnums[] sprint_type = SprintTypeEnums.values();
        SprintEnvStatusEnums[] sprintEnvStatus = SprintEnvStatusEnums.values();
        String version = "V_";

        Random random = new Random();
        for (int i = 0; i < 243; i++) {
            String sql = "INSERT INTO `codeLine`.`t_sprint`"
                    + "(`name`, `sprint_type`, `sprint_template_id`,"
                    + " `sprint_env_status`, `git_storage_type`, "
                    + "`version`, `has_error`, `creator`, `creator_id`, `created_time`, `modifier`, `modifier_id`, `modified_time`, `status`) VALUES "
                    + "('${name}', '${sprint_type}', ${sprint_template_id},"
                    + " '${sprintEnvStatus}', 'gitlab',"
                    + " '${version}', ${has_error}, 'syl', 11111, now(), 'syl2', 11112, now(), 1);";

            sql = sql.replace("${name}",name + i);
            sql = sql.replace("${sprint_type}", sprint_type[i % 2].getTypeName());
            sql = sql.replace("${sprint_template_id}",i + random.nextInt(10000) + "");
            sql = sql.replace("${sprintEnvStatus}", sprintEnvStatus[i % 4].getEnvName());
            sql = sql.replace("${version}",version + DateUtil.format(new Date(),"yyyyHHmm") + i + random.nextInt(10000) + "");
            sql = sql.replace("${has_error}",i % 2 + "");

            System.out.println(sql);
        }


    }
}
