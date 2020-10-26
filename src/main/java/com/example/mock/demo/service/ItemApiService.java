package com.example.mock.demo.service;

import com.example.mock.demo.model.Item;
import javassist.NotFoundException;

public interface ItemApiService {
    Item getItem(Long id) throws NotFoundException;
}
