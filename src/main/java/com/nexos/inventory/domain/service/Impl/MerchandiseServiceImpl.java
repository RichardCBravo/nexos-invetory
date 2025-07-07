package com.nexos.inventory.domain.service.Impl;

import com.nexos.inventory.domain.dto.merchandise.MerchandiseCreateDto;
import com.nexos.inventory.domain.dto.merchandise.MerchandiseDto;
import com.nexos.inventory.domain.dto.merchandise.MerchandiseUpdateDto;
import com.nexos.inventory.domain.dto.role.RoleDto;
import com.nexos.inventory.domain.dto.user.UserDto;
import com.nexos.inventory.domain.service.MerchandiseService;
import com.nexos.inventory.persistence.entity.Merchandise;
import com.nexos.inventory.persistence.entity.User;
import com.nexos.inventory.persistence.repository.MerchandiseRepository;
import com.nexos.inventory.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public MerchandiseDto createMerchandise(MerchandiseCreateDto createDto) {
        if (createDto.getEntryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha debe ser menos o igual a la actual");
        }

        if (merchandiseRepository.existsByProductName(createDto.getProductName())) {
            throw new IllegalArgumentException("El nombre de este producto ya existe");
        }

        User user = userRepository.findById(createDto.getCreatedByUserId()).orElseThrow(
                () -> new IllegalArgumentException("El usuario no existe"));

        Merchandise merchandise = new Merchandise(
                createDto.getProductName(),
                createDto.getQuantity(),
                createDto.getEntryDate(),
                user);

        System.out.println(merchandise);

        Merchandise createMerchandise = merchandiseRepository.save(merchandise);
        return convertMerchandiseToDto(createMerchandise);
    }

    @Override
    public MerchandiseDto updateMerchandise(Long id, MerchandiseUpdateDto updateDto) {
        Merchandise merchandise = merchandiseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto no existe"));
        if (updateDto.getEntryDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha debe ser menos o igual a la actual");
        }

        if (merchandiseRepository.existsByProductNameAndIdNot(updateDto.getProductName(),
                id)) {
            throw new IllegalArgumentException("El nombre de este producto ya existe");
        }

        User user = userRepository.findById(updateDto.getUpdatedByUserId()).orElseThrow(
                () -> new IllegalArgumentException("El usuario no existe"));

        merchandise.setProductName(updateDto.getProductName());
        merchandise.setQuantity(updateDto.getQuantity());
        merchandise.setEntryDate(updateDto.getEntryDate());
        merchandise.setUpdatedDate(LocalDateTime.now());
        merchandise.setUpdatedByUser(user);

        Merchandise updateMerchandise = merchandiseRepository.save(merchandise);
        return convertMerchandiseToDto(updateMerchandise);
    }

    @Override
    public void deleteMerchandise(Long id, Long userId) {
        Merchandise merchandise = merchandiseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La mercancia no existe"));
        if (!merchandise.getCreatedByUser().getId().equals(userId)) {
            throw new IllegalArgumentException("No tiene permisos para eliminar esta mercancia");
        }
        merchandiseRepository.delete(merchandise);
    }

    @Override
    @Transactional(readOnly = true)
    public MerchandiseDto getMerchandiseById(Long id) {
        Merchandise merchandise = merchandiseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El Producto no existe"));
        return convertMerchandiseToDto(merchandise);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MerchandiseDto> getAllMerchandise() {
        return merchandiseRepository.findAll().stream()
                .map(this::convertMerchandiseToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MerchandiseDto> getMerchandiseSearch(String productName, Long userId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate entryDate) {

        if (productName == null && userId == null && entryDate == null) {
            throw new IllegalArgumentException("Tiene que enviar por lo menos un filtro");
        }

        return merchandiseRepository.findBySearch(productName, userId)
                .stream().map(this::convertMerchandiseToDto).collect(Collectors.toList());
    }

    private MerchandiseDto convertMerchandiseToDto(Merchandise merchandise) {
        MerchandiseDto merchandiseDto = new MerchandiseDto();

        merchandiseDto.setId(merchandise.getId());
        merchandiseDto.setProductName(merchandise.getProductName());
        merchandiseDto.setQuantity(merchandise.getQuantity());
        merchandiseDto.setEntryDate(merchandise.getEntryDate());
        merchandiseDto.setCreatedByUser(convertUserToDto(merchandise.getCreatedByUser()));
        if (merchandise.getUpdatedDate() != null) {
            merchandiseDto.setUpdateDate(merchandise.getUpdatedDate());
        }
        if (merchandise.getUpdatedByUser() != null) {
            merchandiseDto.setUpdatedByUser(convertUserToDto(merchandise.getUpdatedByUser()));
        }

        return merchandiseDto;
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
