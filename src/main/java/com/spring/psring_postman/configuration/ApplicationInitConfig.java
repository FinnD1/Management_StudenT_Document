package com.spring.psring_postman.configuration;

import com.spring.psring_postman.entity.User;
import com.spring.psring_postman.enums.Role;
import com.spring.psring_postman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private final PasswordEncoder passwordEncoder ;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
           if (userRepository.findByUserName("admin").isEmpty()) {
               var roles= new HashSet<String>();

               roles.add(Role.ADMIN.name());

               User user = User.builder()
                       .userName("admin")
                       .password(passwordEncoder.encode("admin"))
                       .roles(roles)
                       .build();
               //lỗi chưa add đc roles ADMIM -> end

               userRepository.save(user);
               log.warn("Admin user added with default password !!");
           }
        };
    }
}
