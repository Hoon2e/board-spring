package com.hoon.article.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hoon.article.entity.Article;
import com.hoon.article.mapper.ArticleMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
	private final ArticleMapper articleMapper;
	
	public Optional<Article> findById(Long id){
		return articleMapper.findById(id);
	}
	
	public List<Article> findAll(){
		return articleMapper.findAll();
	}
	
	public Optional<Article> findByTitle(String title){
		return articleMapper.findByTitle(title);
	}

	
	public void save(Article article) {
		articleMapper.save(article);
	}
	
	public void delete(Long id) {
		articleMapper.delete(id);
	}
}

