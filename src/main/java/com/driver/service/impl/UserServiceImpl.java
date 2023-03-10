package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import jdk.jfr.internal.tool.PrettyWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity =UserEntity.builder().id(user.getId())
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail()).build();
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);

        UserDto userDto = UserDto.builder().id(userEntity.getId())
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail()).build();

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {

        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = UserDto.builder().id(userEntity.getId())
                .userId(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail()).build();
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
         userEntity = UserEntity.builder().id(user.getId())
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail()).build();
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> list = (List<UserEntity>) userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();

        for(UserEntity x: list) {
            UserDto userDto = UserDto.builder().id(x.getId())
                    .userId(x.getUserId())
                    .firstName(x.getFirstName())
                    .lastName(x.getLastName())
                    .email(x.getEmail()).build();

            dtoList.add(userDto);
        }
        return dtoList;
    }
}
