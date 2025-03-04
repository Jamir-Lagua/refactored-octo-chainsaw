package com.inventory.controller;

import com.inventory.alert.AlertManager;
import com.inventory.model.DatabaseManager;
import com.inventory.model.PerishableProduct;

import java.util.List;

public class InventoryController {
    private DatabaseManager dbManager;

    public InventoryController() {
        dbManager = new DatabaseManager();
    }

    public void addProduct(PerishableProduct product) {
        dbManager.addProduct(product);
        AlertManager.checkForExpiringProducts(getProducts());
    }

    public List<PerishableProduct> getProducts() {
        return dbManager.getProducts();
    }
}