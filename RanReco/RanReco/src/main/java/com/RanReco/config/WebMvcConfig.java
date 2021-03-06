package com.RanReco.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.RanReco.common.Constants;




/**
 * MVC 설정 클래스
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// 정적 resource
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:src/main/resources/templates/", "file:src/main/resources/static/") // 정적 데이터 접근
                .addResourceLocations("file:///"+ Constants.getUploadProfilePath() + File.separator,
                		"file:///"+ Constants.getUploadLocationPath() + File.separator,
                		"file:///"+ Constants.getUploadFoodPath() + File.separator,
                		"file:///"+ Constants.getUploadFoodInfoPath() + File.separator,
                		"file:///"+ Constants.getUploadFashionPath() + File.separator,
                		"file:///"+ Constants.getUploadFashionInfoPath() + File.separator); // 물리적 파일 접근
	}
	
}