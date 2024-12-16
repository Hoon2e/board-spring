package com.hoon.article.dto.article;

import com.hoon.article.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleCreateDto {
	private String title;
	private String content;
	private Long authroId;

	public Article toEntity() {
		return Article.builder()
				.title(title)
				.content(content)
				.authorId(authroId)
				.build();
	}
}
