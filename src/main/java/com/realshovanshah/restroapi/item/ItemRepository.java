package com.realshovanshah.restroapi.item;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {
    public List<Item> findByCategoryId(String categoryId);
//    public List<Item> findByName(String itemId);
//    public List<Item> findByDescription(String itemId);

}

