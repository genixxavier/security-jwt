package com.gnxcode.securityjwt;

import com.gnxcode.securityjwt.models.ERole;
import com.gnxcode.securityjwt.models.RoleEntity;
import com.gnxcode.securityjwt.models.UserEntity;
import com.gnxcode.securityjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class SecurityJwtApplication {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtApplication.class, args);
    }

    @Bean
    CommandLineRunner init(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(ERole.valueOf(ERole.ADMIN.name()));

        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setName(ERole.valueOf(ERole.USER.name()));

        RoleEntity roleEntity3 = new RoleEntity();
        roleEntity3.setName(ERole.valueOf(ERole.INVITED.name()));


        return args -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail("gg@gmail.com");
            userEntity.setUsername("genix");
            userEntity.setPassword(passwordEncoder.encode("123456"));
            userEntity.setRoles(Set.of(roleEntity));
            userRepository.save(userEntity);

            UserEntity userEntity2 = new UserEntity();
            userEntity2.setEmail("vv@gmail.com");
            userEntity2.setUsername("vv");
            userEntity2.setPassword(passwordEncoder.encode("123456"));
            userEntity2.setRoles(Set.of(roleEntity2));
            userRepository.save(userEntity2);

            UserEntity userEntity3 = new UserEntity();
            userEntity3.setEmail("maria@gmail.com");
            userEntity3.setUsername("maria");
            userEntity3.setPassword(passwordEncoder.encode("123456"));
            userEntity3.setRoles(Set.of(roleEntity3));
            userRepository.save(userEntity3);
        };
    }

}
