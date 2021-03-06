package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        for (int i = 1; i <= 10 ; i++) {
            Item item = new Item();
            item.setItemNm("테스트상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100 + i);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNm("테스트상품3");
        for (Item item: itemList) {
            System.out.println( item.toString() );
        }
    }

    @Test
    @DisplayName("상품명, 상품상세 or 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트상품3", "테스트 상품 상세5");
        for (Item item: itemList) {
            System.out.println( item.toString() );
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item: itemList) {
            System.out.println( item.toString() );
        }
    }

    @Test
    @DisplayName("가격 LessThan Desc 테스트")
    public void findByPriceLessThanOrdOrderByPriceDescTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item: itemList) {
            System.out.println( item.toString() );
        }
    }

    @Test
    @DisplayName("Qeury Test")
    public void findByItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품");
        for (Item item: itemList) {
            System.out.println( item.toString() );
        }
    }

    @Test
    @DisplayName("Qeury 네이티브 Test")
    public void findByItemDetailByNativeTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품");
        for (Item item: itemList) {
            System.out.println( item.toString() );
        }
    }
}