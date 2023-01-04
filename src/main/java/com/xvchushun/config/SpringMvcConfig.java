package com.xvchushun.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan({"com.xvchushun.controller","com.xvchushun.aop","com.xvchushun.service"})
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        registry.jsp("/pages/",".jsp");
    }
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        List<MediaType> mediaTypes = new ArrayList<MediaType>();
//        mediaTypes.add(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
//        mediaTypes.add(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
//        mediaTypes.add(new MediaType(MediaType.APPLICATION_XML, Charset.forName("UTF-8")));
//        mediaTypes.add(new MediaType(MediaType.MULTIPART_FORM_DATA,Charset.forName("UTF-8")));
//        converter.setSupportedMediaTypes(mediaTypes);
//
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//
//        converter.setFastJsonConfig(fastJsonConfig);
//
//        converters.add(converter);
//    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converters.add(converter);
    }
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(2097152);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }


}
