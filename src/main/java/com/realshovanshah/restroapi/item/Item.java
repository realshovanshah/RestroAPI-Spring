package com.realshovanshah.restroapi.item;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Item {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    public Item() {
    }

    public Item(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
