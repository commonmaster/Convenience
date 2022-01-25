package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.stone.springmvc.env.로그인확인자;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.stone")
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
	
	}
	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/* '/js/**'로 호출하는 자원은 '/js/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		/* '/css/**'로 호출하는 자원은 '/css/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		/* '/img/**'로 호출하는 자원은 '/img/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		/* '/font/**'로 호출하는 자원은 '/font/' 폴더 아래에서 찾는다. */ 
        registry.addResourceHandler("/font/**").addResourceLocations("/font/"); 
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new 로그인확인자())
				.addPathPatterns("/**")
				.excludePathPatterns("/main")
				.excludePathPatterns("/home")
				.excludePathPatterns("/login")
				.excludePathPatterns("/join")
				.excludePathPatterns("/duplication")
				.excludePathPatterns("/boards")
				.excludePathPatterns("/board")
				.excludePathPatterns("/products")
				.excludePathPatterns("/product")
				.excludePathPatterns("/img/*")
				.excludePathPatterns("/css/*");
				
	}
	
}
