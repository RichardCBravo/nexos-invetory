package com.nexos.inventory.domain.dto.merchandise;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MerchandiseCreateDto {

    @NotBlank(message = "El nombre del producto en requerido")
    private String productName;

    @NotNull(message = "La cantidad del producto es requerida")
    private Integer quantity;

    @NotNull(message = "El dia en que el producto se registro es requerido")
    private LocalDate entryDate;

    @NotNull(message = "El usuario que lo creo es requerido")
    private Long createdByUserId;

    public MerchandiseCreateDto() {
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }
}
