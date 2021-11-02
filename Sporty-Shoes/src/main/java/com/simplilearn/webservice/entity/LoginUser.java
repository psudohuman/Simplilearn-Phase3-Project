package com.simplilearn.webservice.entity;

public class LoginUser {
		private String username1;
		private String password1;
		public String getUsername() {
			return username1;
		}
		public void setUsername(String username1) {
			this.username1 = username1;
		}
		public String getPassword1() {
			return password1;
		}
		public void setPassword(String password1) {
			this.password1 = password1;
		}
		public LoginUser(String username1, String password1) {
			super();
			this.username1 = username1;
			this.password1 = password1;
		}
		public LoginUser() {
			super();
		}	
	}
