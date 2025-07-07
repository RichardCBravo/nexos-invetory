package com.nexos.inventory.domain.service;

import com.nexos.inventory.domain.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
}
