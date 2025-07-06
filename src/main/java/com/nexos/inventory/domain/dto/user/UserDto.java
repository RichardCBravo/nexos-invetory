package com.nexos.inventory.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexos.inventory.domain.dto.role.RoleDto;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String name;
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entryDate;

    private RoleDto role;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}
