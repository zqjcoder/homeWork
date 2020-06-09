package com.myblog.mapper;

import com.myblog.entity.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文章Mapper
 */
public interface ArticleMapper {

    @Select("select * from t_article where id=#{id}")
    Article findById(Integer id);

    @Select("select * from t_article")
    List<Article> findPage();
}
