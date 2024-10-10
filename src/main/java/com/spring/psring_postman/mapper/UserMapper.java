package com.spring.psring_postman.mapper;


import com.spring.psring_postman.dto.reponse.UserReponse;
import com.spring.psring_postman.dto.request.UserCreationRequest;
import com.spring.psring_postman.dto.request.UserUpdateRequest;
import com.spring.psring_postman.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserReponse toUserReponse(User user);
//    void setUserUpdateRequest(@MappingTarget User user, UserUpdateRequest request);
}
