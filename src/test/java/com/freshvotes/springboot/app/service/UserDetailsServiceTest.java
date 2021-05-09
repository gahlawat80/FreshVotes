package com.freshvotes.springboot.app.service;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceTest {

	@Test
	public void test() {
		String pwd = "myapp";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd = encoder.encode(pwd);
		System.out.println(encodedPwd);
		assertThat(pwd, not(encodedPwd));
	}

}
