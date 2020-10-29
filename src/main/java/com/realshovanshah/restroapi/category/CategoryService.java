package com.realshovanshah.restroapi.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    private List<Item> items =  new ArrayList<>(Arrays.asList(
//            new Item("1", "Rumpum", "Tasty tasty", 100.0),
//            new Item("2", "Pani puri", "dhoti ko mitho hunxa", 40),
//            new Item("3", "Pasta", "Imported and contains pork", 300)
//    ));

    public List<Category> getAllItems(){
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Category getItem(String id) {
//        return items.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        return categoryRepository.findById(id).get();
    }

    public void addItem(Category category) {
        categoryRepository.save(category);
    }

    public void updateItem(String id, Category category) {
//        for (int i=0; i<items.size(); i++){
//            Item temp = items.get(i);
//            if (temp.getId().equals(id)){
//                items.set(i,item);
//                return;
//            }
//        }
        categoryRepository.save(category);
    }

    public void deleteItem(String id) {
//        items.removeIf(i -> i.getId().equals(id));
        categoryRepository.deleteById(id);
    }
}
