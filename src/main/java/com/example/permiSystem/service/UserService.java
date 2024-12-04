package com.example.permiSystem.service;

import com.example.permiSystem.dto.CreateUserRequest;

public interface UserService {

    void createUser(CreateUserRequest createUserRequest) throws Exception;
}
