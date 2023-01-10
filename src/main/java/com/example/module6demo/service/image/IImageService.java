package com.example.module6demo.service.image;

import com.example.module6demo.model.Image;
import com.example.module6demo.service.IGeneralService;

import java.util.Optional;

public interface IImageService extends IGeneralService<Image> {
    Optional<Image> findByName(String name);

    Iterable<Image> findByHouseId(Long id);
}
