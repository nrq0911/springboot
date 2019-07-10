package com.example;

import com.example.service.UserDetailsServiceImpl;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private UserService userService;

	@Resource
	private UserDetailsServiceImpl userDetailsService;

	@Test
	public void contextLoads() {

		BeanDefinition beanDefinition;
		BeanDefinitionReaderUtils a;

		ClassPathXmlApplicationContext applicationContex = null;

		// com.example.service.UserService
		System.out.println(userService.getClass().getName());

		// com.example.service.UserDetailsServiceImpl$$EnhancerBySpringCGLIB$$9ecb9a14
		System.out.println(userDetailsService.getClass().getName());
	}

}
