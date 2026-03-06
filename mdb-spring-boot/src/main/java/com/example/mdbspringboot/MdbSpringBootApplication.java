package com.example.mdbspringboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.model.GroceryItem;
import com.example.mdbspringboot.model.NutritionalInfo;
import com.example.mdbspringboot.model.Supplier;
import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.repository.ItemRepository;
import com.example.mdbspringboot.repository.SupplierRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner {

    @Autowired
    ItemRepository groceryItemRepo;

    @Autowired
    CustomItemRepository customRepo;

    @Autowired
    SupplierRepository supplierRepo;

    List<GroceryItem> itemList = new ArrayList<GroceryItem>();

    public static void main(String[] args) {
        SpringApplication.run(MdbSpringBootApplication.class, args);
    }

    public void run(String... args) {

        // Limpiar datos previos
        groceryItemRepo.deleteAll();
        supplierRepo.deleteAll();

        System.out.println("-------------CREATE SUPPLIERS-------------------------------\n");

        createSuppliers();

        System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");

        createGroceryItems();

        System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");

        showAllGroceryItems();

        System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");

        getGroceryItemByName("Whole Wheat Biscuit");

        System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");

        getItemsByCategory("millets");

        System.out.println("\n-----------UPDATE CATEGORY NAME OF ALL GROCERY ITEMS----------------\n");

        updateCategoryName("snacks");

        System.out.println("\n-----------UPDATE QUANTITY OF A GROCERY ITEM------------------------\n");

        updateItemQuantity("Bonny Cheese Crackers Plain", 10);

        System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");

        deleteGroceryItem("Kodo Millet");

        System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");

        findCountOfGroceryItems();

        System.out.println("\n-------------------THANK YOU---------------------------");
    }

    // CREATE SUPPLIERS
    void createSuppliers() {
        System.out.println("Creating suppliers...");

        supplierRepo.save(new Supplier("supplier1", "HealthyFoods S.L.", "contacto@healthyfoods.com", "600111222"));
        supplierRepo.save(new Supplier("supplier2", "SpiceWorld S.A.", "info@spiceworld.com", "600333444"));

        System.out.println("Suppliers created.");
    }

    // CREATE GROCERY ITEMS
    void createGroceryItems() {
        System.out.println("Data creation started...");

        // Recuperar proveedores creados antes
        Supplier healthyFoods = supplierRepo.findByCompanyName("HealthyFoods S.L.");
        Supplier spiceWorld = supplierRepo.findByCompanyName("SpiceWorld S.A.");

        // Artículos con documento incrustado (NutritionalInfo) y referenciado (Supplier)
        groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks",
                new NutritionalInfo(150, 5.0, 3.0), healthyFoods));

        groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets",
                new NutritionalInfo(200, 2.5, 7.0), healthyFoods));

        groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices",
                new NutritionalInfo(50, 0.5, 1.5), spiceWorld));

        groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets",
                new NutritionalInfo(180, 2.0, 6.0), healthyFoods));

        groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks",
                new NutritionalInfo(220, 10.0, 4.0), spiceWorld));

        System.out.println("Data creation complete...");
    }

    // READ
    // 1. Mostrar todos los artículos
    public void showAllGroceryItems() {
        itemList = groceryItemRepo.findAll();
        itemList.forEach(item -> System.out.println(getItemDetails(item)));
    }

    // 2. Buscar por nombre
    public void getGroceryItemByName(String name) {
        System.out.println("Getting item by name: " + name);
        GroceryItem item = groceryItemRepo.findItemByName(name);
        System.out.println(getItemDetails(item));
    }

    // 3. Buscar por categoría
    public void getItemsByCategory(String category) {
        System.out.println("Getting items for the category " + category);
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
    }

    // 4. Contar documentos
    public void findCountOfGroceryItems() {
        long count = groceryItemRepo.count();
        System.out.println("Number of documents in the collection = " + count);
    }

    // UPDATE approach 1: usando MongoRepository
    public void updateCategoryName(String category) {
        String newCategory = "munchies";
        List<GroceryItem> list = groceryItemRepo.findAll(category);
        list.forEach(item -> item.setCategory(newCategory));
        List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);
        if (itemsUpdated != null)
            System.out.println("Successfully updated " + itemsUpdated.size() + " items.");
    }

    // UPDATE approach 2: usando MongoTemplate
    public void updateItemQuantity(String name, float newQuantity) {
        System.out.println("Updating quantity for " + name);
        customRepo.updateItemQuantity(name, newQuantity);
    }

    // DELETE
    public void deleteGroceryItem(String id) {
        groceryItemRepo.deleteById(id);
        System.out.println("Item with id " + id + " deleted...");
    }

    // Imprimir detalles de un artículo
    public String getItemDetails(GroceryItem item) {
        System.out.println(
                "Item Name: " + item.getName() +
                        ", \nItem Quantity: " + item.getItemQuantity() +
                        ", \nItem Category: " + item.getCategory() +
                        ", \nNutritional Info: " + (item.getNutritionalInfo() != null ? item.getNutritionalInfo() : "N/A") +
                        ", \nSupplier: " + (item.getSupplier() != null ? item.getSupplier().getCompanyName() : "N/A")
        );
        return "";
    }
}