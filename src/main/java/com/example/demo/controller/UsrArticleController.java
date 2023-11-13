package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public Article doWrite(String title, String body) {
		
		articleService.writeArticle(title, body);
		
		int id = articleService.getLastInsertId();
		
		return articleService.getArticleById(id);
	}
	
	@RequestMapping("/usr/article/showList")
	@ResponseBody
	public List<Article> showList() {
		return articleService.getArticles();
	}
	
	@RequestMapping("/usr/article/showDetail")
	@ResponseBody
	public Object showDetail(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.f("%d번 게시물은 존재하지 않습니다", id);
		}
		
		return article;
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.f("%d번 게시물은 존재하지 않습니다", id);
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.f("%d번 게시물을 수정했습니다", id);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Util.f("%d번 게시물은 존재하지 않습니다", id);
		}
		
		articleService.deleteArticle(id);
		
		return Util.f("%d번 게시물을 삭제했습니다", id);
	}
	
}