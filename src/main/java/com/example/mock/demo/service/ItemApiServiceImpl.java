package com.example.mock.demo.service;

import com.example.mock.demo.model.Item;
import com.example.mock.demo.repo.ItemRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev","product"})
@Service
public class ItemApiServiceImpl implements ItemApiService {
    private Logger log = LoggerFactory.getLogger(getClass());
    private ItemRepository itemRepository;

    public ItemApiServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItem(Long id) throws NotFoundException {
        log.info("실제 로직을 실행합니다...");
        return itemRepository.findById(id).orElseThrow(() -> new NotFoundException("id="+id+" 아이템을 찾을 수 없습니다."));
    }
}
