package io.github.ordinarykai.config;

import io.github.ordinarykai.framework.file.config.FileUploadProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author wukai
 * @date 2022/8/11 17:45
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    FileUploadProperties properties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 解决跨域问题
        // springboot版本是2.5.8，设置allowedOrigins("*")且.allowCredentials(true)会报错
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 配置静态资源的访问
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + properties.getPath());
    }

}
