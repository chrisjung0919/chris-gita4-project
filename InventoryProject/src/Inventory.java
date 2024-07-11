import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Inventory extends JFrame {
    private Inventory2 inventory;
    private JTextArea outputTextArea;

    public Inventory() {
        setTitle("Inventory System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inventory = new Inventory2();

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewProduct();
            }
        });

        JButton displayButton = new JButton("Display Products");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayProducts();
            }
        });

        JButton orderButton = new JButton("Identify Items to Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                identifyItemsToOrder();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(orderButton);

        getContentPane().add(scrollPane);
        getContentPane().add(buttonPanel, "South");

        setVisible(true);
    }

    private void addNewProduct() {
        String name = JOptionPane.showInputDialog("Enter product name:");
        if (name != null) {
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter product price:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter product quantity:"));
            int satisfaction = Integer.parseInt(JOptionPane.showInputDialog("Enter customer satisfaction (1-10):"));
            int quality = Integer.parseInt(JOptionPane.showInputDialog("Enter product quality (1-10):"));
            inventory.addNewProduct(name, price, quantity, satisfaction, quality);
            outputTextArea.append("Product added: " + name + ", Price: $" + price + ", Quantity: " + quantity +
                    ", Satisfaction: " + satisfaction + ", Quality: " + quality + "\n");
        }
    }

    private void displayProducts() {
        outputTextArea.setText("List of all products:\n");
        ArrayList<QualityControl> products = inventory.getProducts();
        for (QualityControl product : products) {
            outputTextArea.append(product.getItem() + ": $" + product.getPrice() + ", Quantity: " + product.getQuantity() +
                    ", Satisfaction: " + product.getSatisfaction() + ", Quality: " + product.getQuality() + "\n");
        }
    }

    private void identifyItemsToOrder() {
        ArrayList<String> itemsToOrder = inventory.mustOrder();
        if (itemsToOrder.isEmpty()) {
            outputTextArea.append("No items need to be ordered.\n");
        } else {
            outputTextArea.append("Items that need to be ordered:\n");
            for (String item : itemsToOrder) {
                outputTextArea.append(item + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inventory();
            }
        });
    }
}

class Inventory2 {
    private ArrayList<QualityControl> products;

    public Inventory2() {
        products = new ArrayList<>();
    }

    public void addNewProduct(String name, double price, int quantity, int satisfaction, int quality) {
        QualityControl newProduct = new QualityControl(name, price, quantity, satisfaction, quality);
        int index = 0;
        while (index < products.size() && products.get(index).getItem().compareTo(name) < 0) {
            index++;
        }
        products.add(index, newProduct);
    }

    public ArrayList<String> mustOrder() {
        ArrayList<String> itemsToOrder = new ArrayList<>();
        for (QualityControl product : products) {
            if (product.getSatisfaction() < 5 || product.getQuality() < 5) {
                itemsToOrder.add(product.getItem());
            }
        }
        return itemsToOrder;
    }

    public ArrayList<QualityControl> getProducts() {
        return products;
    }
}

class Product {
    private String item;
    private double price;
    private int quantity;

    public Product(String item, double price, int quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class QualityControl extends Product {
    private int satisfaction;
    private int quality;

    public QualityControl(String item, double price, int quantity, int satisfaction, int quality) {
        super(item, price, quantity);
        this.satisfaction = satisfaction;
        this.quality = quality;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public int getQuality() {
        return quality;
    }
}
