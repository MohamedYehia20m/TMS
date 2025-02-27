package com.ropulva.tms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;


@Configuration
public class FactoryBean {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
