package com.ghj.springboot;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ghj.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootOnlinetestApplicationTests {

	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void addUserTest(){
		userService.addUser("wangwu","20191308099","9999","root");
		//System.out.println(userService.getUserByUsername("20151308088"));
	}
}
