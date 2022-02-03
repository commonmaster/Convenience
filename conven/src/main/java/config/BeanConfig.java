package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


@Configuration
public class BeanConfig {
	
//	@Bean
//	public 편의점업무컨트롤러 요청컨트롤러( ) {// @controller
//		return new 편의점업무컨트롤러( );
//	}	
//	
//	@Bean
//	public 로그인컨트롤러 로그인컨트롤러() {
//		return new 로그인컨트롤러();
//	}
//	
//	@Bean 회원관리컨트롤러 회원관리컨트롤러() {
//		return new 회원관리컨트롤러();
//	}
//	
//	@Bean 
//	public 편의점사이트관리서비스 편의점사이트관리서비스() { //@service
//		return new 편의점사이트관리서비스();
//	}
//	
//	@Bean
//	public 편의점업무DAO 편의점업무dao() { //@dao
//		return new 편의점업무DAO();
//	}
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
}
