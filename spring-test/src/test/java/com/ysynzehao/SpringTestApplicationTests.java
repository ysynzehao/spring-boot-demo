package com.ysynzehao;

import com.ysynzehao.entry.Role;
import com.ysynzehao.entry.User;
import com.ysynzehao.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetUser() {
		User user = userService.getUser(1);
		Set<Role> roles = user.getRoles();
		System.out.println(roles);
	}
}
