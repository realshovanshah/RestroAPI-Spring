package com.realshovanshah.restroapi.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getAllItems(){
        return categoryService.getAllItems();
    }

    @GetMapping("/category/{id}")
    public Category getItem(@PathVariable String id){
        return categoryService.getItem(id);
    }

    @PostMapping("/category")
    public void postItem(@RequestBody Category category){
        categoryService.addItem(category);
    }

    @PutMapping("/category/{id}")
    public void updateItem(@PathVariable String id, @RequestBody Category category){
        categoryService.updateItem(id, category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteItem(@PathVariable String id){
        categoryService.deleteItem(id);
    }

}
