package com.realshovanshah.restroapi.category;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(
        name="Menu Category API",
        description = "Provides a list of methods for managing the category database",
        stage = ApiStage.RC
    )
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getAllCategories(){
        return categoryService.getAllItems();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable String id){
        return categoryService.getItem(id);
    }

    @PostMapping("/category")
    public void postCategory(@RequestBody Category category){
        categoryService.addItem(category);
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@PathVariable String id, @RequestBody Category category){
        categoryService.updateItem(id, category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteItem(id);
    }

}
