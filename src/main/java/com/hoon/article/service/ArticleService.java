package com.hoon.article.service;

import org.springframework.stereotype.Service;

import com.hoon.article.dto.article.ArticleCreateDto;
import com.hoon.article.entity.Article;
import com.hoon.article.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;

	public Long createArticle(ArticleCreateDto articleCreateDto) {
		Article article = articleCreateDto.toEntity();
		articleRepository.save(article);
		return article.getArticleId();
	}
}
