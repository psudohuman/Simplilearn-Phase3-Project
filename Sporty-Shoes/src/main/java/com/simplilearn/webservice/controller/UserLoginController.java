package com.simplilearn.webservice.controller;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.entity.Login;
import com.simplilearn.webservice.entity.User;
import com.simplilearn.webservice.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserLoginController {
	@Autowired
	UserRepository uRepo;
	
	
	HttpSession session1;
	@PostMapping("/login")
	public String login(@RequestBody Login login,HttpServletRequest request) {
		
		String a=login.getUsername();
		String b=login.getPassword();
		List<User> users = uRepo.findAll();

		for(User u:users)
		{
			if(u.getName().equals(a)&&u.getPass().equals(b))
			{
				session1 = request.getSession(true);
				session1.setAttribute("id",u.getId());
				return "User Login Success !";
				
			}
			else
				return "Login failed 02";
		}
			
		
		return "Login try again ";
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request) {
		session1 = request.getSession(false);
		session1.invalidate();
	}
}