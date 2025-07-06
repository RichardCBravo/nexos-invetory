package com.nexos.inventory.domain.dto.merchandise;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexos.inventory.domain.dto.user.UserDto;

import java.time.LocalDateTime;

public class MerchandiseDto {
    private Long id;
    private String productName;
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entryDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateDate;

    private UserDto createdByUser;
    private UserDto updatedByUser;

    public MerchandiseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public UserDto getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(UserDto createdByUser) {
        this.createdByUser = createdByUser;
    }

    public UserDto getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(UserDto updatedByUser) {
        this.updatedByUser = updatedByUser;
    }
}
