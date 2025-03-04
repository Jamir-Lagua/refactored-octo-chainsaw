package com.inventory.view;

import com.inventory.controller.InventoryController;
import com.inventory.model.PerishableProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class InventoryView extends JFrame {
    private InventoryController controller;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField, quantityField, priceField, expirationField;

    public InventoryView(InventoryController controller) {
        this.controller = controller;
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadProducts();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Quantity", "Price", "Expiration Date"}, 0);
        table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Expiration Date (YYYY-MM-DD):"));
        expirationField = new JTextField();
        inputPanel.add(expirationField);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        add(panel);
    }

    private void loadProducts() {
        List<PerishableProduct> products = controller.getProducts();
        for (PerishableProduct product : products) {
            tableModel.addRow(new Object[]{product.getId(), product.getName(), product.getQuantity(), product.getPrice(), product.getExpirationDate()});
        }
    }

    private void addProduct() {
        String name = nameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        LocalDate expirationDate = LocalDate.parse(expirationField.getText());

        PerishableProduct product = new PerishableProduct(name, quantity, price, expirationDate);
        controller.addProduct(product);

        tableModel.addRow(new Object[]{product.getId(), product.getName(), product.getQuantity(), product.getPrice(), product.getExpirationDate()});
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
        expirationField.setText("");
    }
}