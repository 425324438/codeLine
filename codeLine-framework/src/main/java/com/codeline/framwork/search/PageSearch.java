package com.codeline.framwork.search;

import lombok.Data;

/**
 * @author: syl
 * @Date: 2022/8/29 22:45
 * @Description:
 */
@Data
public class PageSearch<T> {

    private T search;

    private int currentPage;

    private Integer pageSize = 20;

    private Integer pageNum;

}
