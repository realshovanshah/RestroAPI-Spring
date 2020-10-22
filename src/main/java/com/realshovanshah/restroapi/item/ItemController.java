package com.realshovanshah.restroapi.item;

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

    @GetMapping("/items/{id}")
    public Item getItem(@PathVariable String id){
        return itemService.getItem(id);
    }

    @PostMapping("/items")
    public void postItem(@RequestBody Item item){
        itemService.addItem(item);
    }

    @PutMapping("/items/{id}")
    public void updateItem(@PathVariable String id, @RequestBody Item item){
        itemService.updateItem(id, item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable String id){
        itemService.deleteItem(id);
    }

}
