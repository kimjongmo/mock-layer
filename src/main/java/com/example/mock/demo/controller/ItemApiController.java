package com.example.mock.demo.controller;

import com.example.mock.demo.model.Item;
import com.example.mock.demo.service.ItemApiService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile({"dev","product"})
@RestController
@RequestMapping("/item")
public class ItemApiController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private ItemApiService itemApiService;

    public ItemApiController(ItemApiService itemApiService) {
        this.itemApiService = itemApiService;
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundHandler(NotFoundException e) {
        log.warn("아이템을 찾을 수 없습니다. {}",e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemByName(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(itemApiService.getItem(id));
    }


}
