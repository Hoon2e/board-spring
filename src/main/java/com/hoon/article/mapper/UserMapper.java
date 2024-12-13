package com.hoon.article.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hoon.article.dto.user.UserSearchCond;
import com.hoon.article.entity.User;

@Mapper
public interface UserMapper {
	 Optional<User> findById(Long id);
	 
	 Optional<User> findFirstByCond(UserSearchCond cond);
	 
	 List<User> findAll();
	 
	 Optional<User> findByUsername(String username);
	 
	 boolean isExistByUsernameOrEmail(@Param("username") String username, @Param("email") String email);
	 
	 void save(User user);
	 
	 void update(@Param("id") Long id, @Param("updateParam") User user);
	 
	 void delete(Long id);
}
