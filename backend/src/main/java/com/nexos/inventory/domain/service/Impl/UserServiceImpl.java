package com.nexos.inventory.domain.service.Impl;

import com.nexos.inventory.domain.dto.role.RoleDto;
import com.nexos.inventory.domain.dto.user.UserDto;
import com.nexos.inventory.domain.service.UserService;
import com.nexos.inventory.persistence.entity.User;
import com.nexos.inventory.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setEntryDate(user.getEntryDate());

        RoleDto roleDto = new RoleDto();
        roleDto.setId(user.getRole().getId());
        roleDto.setName(user.getRole().getName());

        userDto.setRole(roleDto);

        return userDto;
    }
}
