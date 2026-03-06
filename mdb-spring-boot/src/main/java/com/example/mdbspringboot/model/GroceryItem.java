package com.example.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "GroceryItem")
public class GroceryItem {

    @Id
    private String id;

    @Indexed
    @Field("name")
    private String name;

    @Field("quantity")
    private int quantity;

    @Field("category")
    private String category;

    @Field("nutritional_info")
    private NutritionalInfo nutritionalInfo;

    @DBRef
    private Supplier supplier;

    public GroceryItem() {}

    public GroceryItem(String id, String name, int quantity, String category) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public GroceryItem(String id, String name, int quantity, String category,
                       NutritionalInfo nutritionalInfo, Supplier supplier) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.nutritionalInfo = nutritionalInfo;
        this.supplier = supplier;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getItemQuantity() { return quantity; }
    public void setItemQuantity(int quantity) { this.quantity = quantity; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public NutritionalInfo getNutritionalInfo() { return nutritionalInfo; }
    public void setNutritionalInfo(NutritionalInfo nutritionalInfo) { this.nutritionalInfo = nutritionalInfo; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}