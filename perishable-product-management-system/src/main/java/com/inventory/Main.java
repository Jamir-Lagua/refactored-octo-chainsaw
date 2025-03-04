package com.inventory;

import com.inventory.controller.InventoryController;
import com.inventory.view.InventoryView;

public class Main {
    public static void main(String[] args) {
        InventoryController controller = new InventoryController();
        InventoryView view = new InventoryView(controller);
        view.setVisible(true);
    }
}