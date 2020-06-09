package com.myblog.service;

import com.myblog.pojo.PageRequest;
import com.myblog.pojo.PageResult;

/**
 * 文章service
 */
public interface ArticleService {

    PageResult findPage(PageRequest pageRequest);
}
