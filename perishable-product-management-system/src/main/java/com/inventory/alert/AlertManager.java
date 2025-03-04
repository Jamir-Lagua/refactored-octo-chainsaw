package com.inventory.alert;

import com.inventory.model.PerishableProduct;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class AlertManager {
    public static void checkForExpiringProducts(List<PerishableProduct> products) {
        LocalDate currentDate = LocalDate.now();
        for (PerishableProduct product : products) {
            if (product.getExpirationDate().isBefore(currentDate) || product.getExpirationDate().isEqual(currentDate)) {
                JOptionPane.showMessageDialog(null,
                        "Product " + product.getName() + " has expired!",
                        "Expiration Alert",
                        JOptionPane.WARNING_MESSAGE);
            } else if (product.getExpirationDate().isBefore(currentDate.plusDays(3))) {
                JOptionPane.showMessageDialog(null,
                        "Product " + product.getName() + " is about to expire in less than 3 days!",
                        "Expiration Alert",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}