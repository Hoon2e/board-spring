package com.hoon.article.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.hoon.article.dto.user.UserSearchCond;
import com.hoon.article.entity.User;
import com.hoon.article.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private final UserMapper userMapper;
	
	public Optional<User> findById(Long id) {
		return userMapper.findById(id);
	}
	
	public Optional<User> findFirstByCond(UserSearchCond cond)
	{
		return userMapper.findFirstByCond(cond);
	}
	
	public List<User> findAll() {
		return userMapper.findAll();
	}
	
	public Optional<User> findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	public boolean isExistByUsernameOrEmail(String username, String email) {
		return userMapper.isExistByUsernameOrEmail(username, email);
	}
	
	public Long save(User user)
	{
		userMapper.save(user);
		return user.getUserId();
	}
	
	public void update(Long id, User user) {
		userMapper.update(id, user);
	}
	
	public void delete(Long id) {
		userMapper.delete(id);
	}
}
