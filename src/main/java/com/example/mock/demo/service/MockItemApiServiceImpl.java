package com.example.mock.demo.service;

import com.example.mock.demo.model.Item;
import com.example.mock.demo.model.MockItem;
import com.example.mock.demo.repo.MockItemRepository;
import java.util.Optional;
import javassist.NotFoundException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
@Primary
public class MockItemApiServiceImpl implements ItemApiService {
    private Logger log = LoggerFactory.getLogger(getClass());
    private MockItemRepository mockItemRepository;
    private ItemApiService itemApiService; // 실제 객체

    public MockItemApiServiceImpl(MockItemRepository mockItemRepository, @Qualifier("itemApiServiceImpl") ItemApiService itemApiService) {
        this.mockItemRepository = mockItemRepository;
        this.itemApiService = itemApiService;
    }

    @PostConstruct
    public void notice(){
        log.info("MockItemApiServiceImpl이 활성화 되었습니다. ");
    }

    @Override
    public Item getItem(Long id) throws NotFoundException {
        if (mockItemRepository.existsById(id)) {
            Optional<MockItem> optionalMockItem = mockItemRepository.findById(id);
            if(optionalMockItem.isPresent()) {
                return optionalMockItem.get().toItem();
            } else {
                return itemApiService.getItem(id);
            }
        }
        return itemApiService.getItem(id);
    }
}
