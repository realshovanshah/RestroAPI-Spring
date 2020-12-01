package com.realshovanshah.restroapi.item;

import com.realshovanshah.restroapi.category.Category;
import com.realshovanshah.restroapi.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/category/{id}/items")
    public List<Item> getItemsByCategory(@PathVariable String id){
        return itemService.getItemsByCategory(id);
    }

    @GetMapping("/category/{categoryId}/items/{id}")
    public Item getItem(@PathVariable String id){
        return itemService.getItem(id);
    }

    @PostMapping("/category/{categoryId}/items")
    public void postItem(@RequestBody Item item, @PathVariable String categoryId){
        item.setCategory(new Category(categoryId, "", ""));
        itemService.addItem(item);
    }

    @PutMapping("/category/{id}/items/{id}")
    public void updateItem(@PathVariable String id, @PathVariable String categoryId, @RequestBody Item item){
        item.setCategory(new Category(categoryId, "", ""));
        itemService.updateItem(item);
    }

    @DeleteMapping("/category/{id}/items/{id}")
    public void deleteItem(@PathVariable String id){
        itemService.deleteItem(id);
    }

}
