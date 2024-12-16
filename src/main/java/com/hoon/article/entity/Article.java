package com.hoon.article.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
	private Long articleId;
	private String title;
	private String content;
	private Long authorId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
