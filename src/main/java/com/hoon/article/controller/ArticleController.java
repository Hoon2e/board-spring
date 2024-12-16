package com.hoon.article.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoon.article.dto.article.ArticleCreateDto;
import com.hoon.article.dto.common.ResponseDto;
import com.hoon.article.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {
	private final ArticleService articleService;

	@PostMapping
	public ResponseEntity<ResponseDto<Long>> createArticle(@RequestBody ArticleCreateDto articleCreateDto) {
		Long id = articleService.createArticle(articleCreateDto);
		ResponseDto<Long> res = ResponseDto.success(id, HttpStatus.CREATED);
		return ResponseEntity.status(res.getStatusCode()).body(res);
	}
}
