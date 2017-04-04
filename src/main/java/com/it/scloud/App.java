package com.it.scloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @SpringBootApplication相当于@Configuration、@EnableAutoConfiguration和  @ComponentScan，你也可以同时使用这3个注解。
 * @Configuration、@ComponentScan是spring框架的语法，在spring 3.x就有了，用于代码方式创建配置信息和扫描包。
 * @EnableAutoConfiguration是spring boot语法，表示将使用自动配置
 * */
@Controller
@SpringBootApplication
public class App {
	
	@ResponseBody
	@RequestMapping("/")
	String home(){
		return "hello world";
	}
	
	/** 这是一个web应用，使用了嵌入式的tomcat
	    package后：java -jar scloud-0.0.1-SNAPSHOT.jar --server.port=8081
	*/
    public static void main( String[] args ){
        SpringApplication.run(App.class,  args);
    }
}
