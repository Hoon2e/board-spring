package com.hoon.article.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hoon.article.dto.user.UserSearchCond;
import com.hoon.article.entity.Article;

@Mapper
public interface ArticleMapper {
	Optional<Article> findById(Long id);

	List<Article> findAll();

	Optional<Article> findByTitle(String title);

	void save(Article user);

	void delete(Long id);

}
