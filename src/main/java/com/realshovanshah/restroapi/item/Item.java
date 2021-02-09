package com.realshovanshah.restroapi.item;

import com.realshovanshah.restroapi.category.Category;
import io.leangen.graphql.annotations.GraphQLQuery;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    @Id
    private String id;

    @GraphQLQuery(name = "name", description = "the food's name")
    private String name;
    @GraphQLQuery(name = "name", description = "the food's description")
    private String description;
    @GraphQLQuery(name = "name", description = "the food's price")
    private double price;

    @ManyToOne
    private Category category;

    public Item() {
    }

    public Item(String id, String name, String description, double price, String categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = new Category(categoryId, "", "");
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
