package com.gnxcode.securityjwt.controller;

import com.gnxcode.securityjwt.controller.request.CreateUserDTO;
import com.gnxcode.securityjwt.models.ERole;
import com.gnxcode.securityjwt.models.RoleEntity;
import com.gnxcode.securityjwt.models.UserEntity;
import com.gnxcode.securityjwt.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("hello")
    public String hello(){
        return "Hello not security";
    }

    @GetMapping("security")
    public String security(){
        return "Hello with security";
    }

    @PostMapping("user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setName(ERole.valueOf(role));
                    return roleEntity;
                })
                .collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(createUserDTO.getEmail());
        userEntity.setUsername(createUserDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        userEntity.setRoles(roles);

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/user")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));

        return "Delete user id: ".concat(id);
    }
}
