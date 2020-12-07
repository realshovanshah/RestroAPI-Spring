package com.realshovanshah.restroapi.item;

import com.realshovanshah.restroapi.category.Category;
import com.realshovanshah.restroapi.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
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
    public Item postItem(@RequestBody Item item, @PathVariable String categoryId){
        item.setCategory(new Category(categoryId, "", ""));
        itemService.addItem(item);
        return itemService.getItem(item.getId());
    }

    @PutMapping("/category/{categoryId}/items/{id}")
    public Item updateItem(@PathVariable String id, @PathVariable String categoryId, @RequestBody Item item){
        item.setCategory(new Category(categoryId, "", ""));
        itemService.updateItem(item);
        return itemService.getItem(id);
    }

    @DeleteMapping("/category/{categoryId}/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public Item deleteItem(@PathVariable String id){
        itemService.deleteItem(id);
        return itemService.getItem(id);
    }

}
