package com.spring.psring_postman.service;


import com.spring.psring_postman.dto.request.UserCreationRequest;
import com.spring.psring_postman.dto.request.UserUpdateRequest;
import com.spring.psring_postman.entity.User;
import com.spring.psring_postman.enums.Role;
import com.spring.psring_postman.exception.AppException;
import com.spring.psring_postman.exception.ErrorCode;
import com.spring.psring_postman.mapper.UserMapper;
import com.spring.psring_postman.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@NoArgsConstructor
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private UserMapper userMapper;\
    @Autowired
    private PasswordEncoder passwordEncoder ;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXIST);
        }
//c1
//        UserCreationRequest userCreationRequest = UserCreationRequest.builder()
//                .username("")
//                .firstname("hi123")
//                .build();
        //tien loi hon

        //c2
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setGrade(request.getGrade());
        user.setMajor(request.getMajor());
        user.setDepartment(request.getDepartment());
        user.setTrainingSystem(request.getTrainingSystem());
        user.setCohort(request.getCohort());
        user.setDob(request.getDob());



        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        //c3 nhanh nhat
//        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUserById(userId);

        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setGrade(request.getGrade());
        user.setMajor(request.getMajor());
        user.setDepartment(request.getDepartment());
        user.setTrainingSystem(request.getTrainingSystem());
        user.setCohort(request.getCohort());
        user.setDob(request.getDob());
//        cach nay dang bi loi
//        User user = getUserById(userId);
//        userMapper.setUserUpdateRequest(user,request);


        return userRepository.save(user);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        log.info(" in method getAllUsers");
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
