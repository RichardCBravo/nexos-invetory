package com.nexos.inventory.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "El nombre en requerido")
    private String name;

    @Column(name="age", nullable = false)
    @NotBlank(message = "La edad es requerida")
    private Integer age;

    @Column(name = "entry_date", nullable = false)
    @NotNull(message = "La fecha en ingreso a la compañia en requerida")
    private LocalDate entryDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @OneToMany(mappedBy = "createdByUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Merchandise> createdMerchandise;

    @OneToMany(mappedBy = "updatedByUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Merchandise> updatedMerchandise;


    public User(String name, Integer age, LocalDate entryDate, Role role) {
        this.name = name;
        this.age = age;
        this.entryDate = entryDate;
        this.role = role;
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Merchandise> getCreatedMerchandise() {
        return createdMerchandise;
    }

    public void setCreatedMerchandise(List<Merchandise> createdMerchandise) {
        this.createdMerchandise = createdMerchandise;
    }

    public List<Merchandise> getUpdatedMerchandise() {
        return updatedMerchandise;
    }

    public void setUpdatedMerchandise(List<Merchandise> updatedMerchandise) {
        this.updatedMerchandise = updatedMerchandise;
    }
}
