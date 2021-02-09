package com.realshovanshah.restroapi.item;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
@GraphQLApi
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

//    private List<Item> items =  new ArrayList<>(Arrays.asList(
//            new Item("1", "Rumpum", "Tasty tasty", 100.0),
//            new Item("2", "Pani puri", "dhoti ko mitho hunxa", 40),
//            new Item("3", "Pasta", "Imported and contains pork", 300)
//    ));

    @GraphQLQuery(name = "items")
    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    @GraphQLQuery(name = "item")
    public List<Item> getItemsByName(@GraphQLArgument(name = "name") String name){
        List<Item> items = new ArrayList<>();
        itemRepository.findByName(name)
                .forEach(items::add);
        return items;
    }

    public List<Item> getItemsByCategory(String categoryId){
        List<Item> items = new ArrayList<>();
        itemRepository.findByCategoryId(categoryId)
                .forEach(items::add);
        return items;
    }

    public Item getItem(String id) {
//        return items.stream().filter(i -> i.getId().equals(id)).findFirst().get();
//        return itemRepository.findById(id).orElseGet(() -> new Item());
        return itemRepository.findById(id).get();

    }

    public void addItem(Item item) {
        itemRepository.save(item);
    }

    public void updateItem(Item item) {
//        for (int i=0; i<items.size(); i++){
//            Item temp = items.get(i);
//            if (temp.getId().equals(id)){
//                items.set(i,item);
//                return;
//            }
//        }
        itemRepository.save(item);
    }

    public void deleteItem(String id) {
//        items.removeIf(i -> i.getId().equals(id));
        itemRepository.deleteById(id);
    }
}
