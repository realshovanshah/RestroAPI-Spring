package com.realshovanshah.restroapi.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {
    public List<Item> findByCategoryId(String categoryId);
    public List<Item> findByName(String itemId);
//    public List<Item> findByDescription(String itemId);

}

