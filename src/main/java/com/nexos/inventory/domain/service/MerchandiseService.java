package com.nexos.inventory.domain.service;

import com.nexos.inventory.domain.dto.merchandise.MerchandiseCreateDto;
import com.nexos.inventory.domain.dto.merchandise.MerchandiseDto;
import com.nexos.inventory.domain.dto.merchandise.MerchandiseUpdateDto;

import java.util.List;

public interface MerchandiseService {
    MerchandiseDto createMerchandise(MerchandiseCreateDto createDto);
    MerchandiseDto updateMerchandise(Long id, MerchandiseUpdateDto updateDto);

    void deleteMerchandise(Long id, Long userId);

    MerchandiseDto getMerchandiseById(Long id);

    List<MerchandiseDto> getAllMerchandise();

}
