package com.gogi.meatyou.repository;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.UserDetailsDTO;
import com.gogi.meatyou.bean.UsersDTO;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<Member, Long> {
	public UsersDTO login(String username);
	void save(UsersDTO users);
	String GetByAuth(String username);
	String regCheck(String username);
	UsersDTO FindByUser(String username);
	UsersDTO FindByEmail(String email);
	
	void createDetails(String username);
	void createReviews(String username);
	void createMypage(String username);
	void createPayment(String username);
	void createImages(String username);
	void addDetails(UserDetailsDTO details);
	
	void changePass(@Param("password") String password,@Param("username") String username);
}