package com.FreshHome.app.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class request {
	private String email;
	private String password;

	public request(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public request() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
