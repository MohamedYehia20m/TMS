package com.ropulva.tms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class FactoryBean {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
