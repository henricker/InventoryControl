package view;

import controller.CategoryController;
import controller.ProductController;
import controller.ProviderController;
import model.Category;
import model.Product;
import model.Provider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ProductFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelName, labelDescription, labelCategory, label, labelProductList, labelCostPrice, labelSalePrice, labelIntentoryMin, labelProvider;
    private JLabel labelListId, labelListName, labelListDescription, labelListCostPrice, labelListSalePrice, labelListInventoryMin, labelListQuantity, labelListCategoryId ;
    private JTextField fieldName, fieldDescription, fieldCostPrice, fieldSalePrice, fieldInventoryMin;
    private JComboBox<Category> comboCategory;
    private JComboBox<Provider> comboProvider;
    private JButton buttonSave, buttonUpdate, buttonClear, buttonDelete;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductController productController;
    private CategoryController categoryController;
    private ProviderController providerController;

    public ProductFrame(CategoryController categoryController, ProductController productController, ProviderController providerController) {
        super("Product management");

        //get Controller
        this.categoryController = categoryController;
        this.productController = productController;
        this.providerController = providerController;

        //Background image
        Image img = Toolkit.getDefaultToolkit().getImage("src/main/resources/assets/InventoryControl.jpg");

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(img, 0, 0, null);
            }
        });
        Container container = getContentPane();
        setLayout(null);

        this.label = new JLabel("Product management");
        this.label.setFont(label.getFont().deriveFont(20.0F));
        this.label.setForeground(Color.pink);
        this.label.setBounds(300, 0, 400, 50);

        this.labelName = new JLabel("Name");
        this.labelName.setFont(label.getFont().deriveFont(12.0F));
        this.labelName.setForeground(Color.pink);
        this.labelName.setBounds(2, 30, 400, 50);

        this.fieldName = new JTextField();
        this.fieldName.setBounds(2, 70, 265, 30);

        this.labelDescription = new JLabel("Description");
        this.labelDescription.setFont(label.getFont().deriveFont(12.0F));
        this.labelDescription.setForeground(Color.pink);
        this.labelDescription.setBounds(2, 110, 400, 50);

        this.fieldDescription = new JTextField();
        this.fieldDescription.setBounds(2, 150, 265, 30);

        this.labelCostPrice = new JLabel("Cost_price");
        this.labelCostPrice.setFont(label.getFont().deriveFont(12.0F));
        this.labelCostPrice.setForeground(Color.pink);
        this.labelCostPrice.setBounds(2, 180, 400, 50);

        this.fieldCostPrice = new JTextField();
        this.fieldCostPrice.setBounds(2, 220, 265, 30);

        this.labelSalePrice = new JLabel("Sale_price");
        this.labelSalePrice.setFont(label.getFont().deriveFont(12.0F));
        this.labelSalePrice.setForeground(Color.pink);
        this.labelSalePrice.setBounds(2, 250, 400, 50);

        this.fieldSalePrice = new JTextField();
        this.fieldSalePrice.setBounds(2, 290, 265, 30);

        this.labelIntentoryMin = new JLabel("inventory_min");
        this.labelIntentoryMin.setFont(label.getFont().deriveFont(12.0F));
        this.labelIntentoryMin.setForeground(Color.pink);
        this.labelIntentoryMin.setBounds(2, 310, 400, 50);

        this.fieldInventoryMin = new JTextField();
        this.fieldInventoryMin.setBounds(2, 350, 265, 30);

        this.labelCategory = new JLabel("Category");
        this.labelCategory.setFont(label.getFont().deriveFont(12.0F));
        this.labelCategory.setForeground(Color.pink);
        this.labelCategory.setBounds(2, 380, 400, 50);

        this.comboCategory = new JComboBox<Category>();
        this.comboCategory.addItem(new Category(0, "Select"));
        List<Category> categories = this.getListCategories();
        categories.stream().forEach(category -> this.comboCategory.addItem(category));
        this.comboCategory.setBounds(2, 420, 265, 20);

        this.labelProvider = new JLabel("Providers");
        this.labelProvider.setFont(label.getFont().deriveFont(12.0F));
        this.labelProvider.setForeground(Color.pink);
        this.labelProvider.setBounds(2, 440, 400, 50);

        this.comboProvider = new JComboBox<Provider>();
        this.comboProvider.addItem(new Provider("Select", "Select"));
        List<Provider> providers = this.getListProviders();
        providers.stream().forEach(provider -> this.comboProvider.addItem(provider));
        this.comboProvider.setBounds(2, 480, 265, 20);

        //Create buttons form
        this.buttonSave = new JButton("Save");
        this.buttonClear = new JButton("Clean");

        this.buttonSave.setBounds(42, 520, 80, 20);
        this.buttonClear.setBounds(142, 520, 80, 20);

        //Product list
        this.labelProductList = new JLabel("Product List");
        this.labelProductList.setFont(label.getFont().deriveFont(15.0F));
        this.labelProductList.setForeground(Color.pink);
        this.labelProductList.setBounds(490, 30, 320, 50);

        this.labelListId = new JLabel("ID");
        this.labelListId.setFont(label.getFont().deriveFont(13.0F));
        this.labelListId.setForeground(Color.black);
        this.labelListId.setBounds(310, 95, 100, 50);

        this.labelListName = new JLabel("Name");
        this.labelListName.setFont(label.getFont().deriveFont(13.0F));
        this.labelListName.setForeground(Color.black);
        this.labelListName.setBounds(400, 95, 100, 50);

        this.labelListDescription = new JLabel("Description");
        this.labelListDescription.setFont(label.getFont().deriveFont(13.0F));
        this.labelListDescription.setForeground(Color.black);
        this.labelListDescription.setBounds(480, 95, 100, 50);

        this.labelListCostPrice = new JLabel("Cost Price");
        this.labelListCostPrice.setFont(label.getFont().deriveFont(13.0F));
        this.labelListCostPrice.setForeground(Color.black);
        this.labelListCostPrice.setBounds(580, 95, 100, 50);

        this.labelListSalePrice = new JLabel("Sale price");
        this.labelListSalePrice.setFont(label.getFont().deriveFont(13.0F));
        this.labelListSalePrice.setForeground(Color.black);
        this.labelListSalePrice.setBounds(670, 95, 100, 50);

        this.labelListCategoryId = new JLabel("Category ID");
        this.labelListCategoryId.setFont(label.getFont().deriveFont(13.0F));
        this.labelListCategoryId.setForeground(Color.black);
        this.labelListCategoryId.setBounds(940, 95, 100, 50);

        this.labelListInventoryMin = new JLabel("Inventory Min");
        this.labelListInventoryMin.setFont(label.getFont().deriveFont(13.0F));
        this.labelListInventoryMin.setForeground(Color.black);
        this.labelListInventoryMin.setBounds(750, 95, 100, 50);

        this.labelListQuantity = new JLabel("Quantity");
        this.labelListQuantity.setFont(label.getFont().deriveFont(13.0F));
        this.labelListQuantity.setForeground(Color.black);
        this.labelListQuantity.setBounds(850, 95, 100, 50);

        //Create table
        this.table = new JTable();
        this.tableModel = (DefaultTableModel) this.table.getModel();

        this.tableModel.addColumn("ID");
        this.tableModel.addColumn("name");
        this.tableModel.addColumn("description");
        this.tableModel.addColumn("cost_price");
        this.tableModel.addColumn("sale_price");
        this.tableModel.addColumn("inventory_min");
        this.tableModel.addColumn("quantity");
        this.tableModel.addColumn("category_id");

        this.table.setBounds(280, 130, 750, 620);

        this.fillTable();

        this.buttonDelete = new JButton("delete");
        this.buttonUpdate = new JButton("update");

        this.buttonDelete.setBounds(340, 760, 120, 40);
        this.buttonUpdate.setBounds(790, 760, 120, 40);

        container.add(label);
        container.add(labelName);
        container.add(fieldName);
        container.add(labelDescription);
        container.add(fieldDescription);
        container.add(labelCostPrice);
        container.add(fieldCostPrice);
        container.add(labelSalePrice);
        container.add(fieldSalePrice);
        container.add(labelIntentoryMin);
        container.add(fieldInventoryMin);
        container.add(labelCategory);
        container.add(comboCategory);
        container.add(labelProvider);
        container.add(comboProvider);
        container.add(buttonSave);
        container.add(buttonClear);
        container.add(labelProductList);
        container.add(table);
        container.add(labelListId);
        container.add(labelListName);
        container.add(labelListDescription);
        container.add(labelListCostPrice);
        container.add(labelListSalePrice);
        container.add(labelListCategoryId);
        container.add(labelListInventoryMin);
        container.add(labelListQuantity);
        container.add(buttonDelete);
        container.add(buttonUpdate);

        setSize(1100, 900);
        setVisible(true);
        setLocationRelativeTo(null);

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                clearFieldsForm();
                fillTable();
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFieldsForm();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
                fillTable();
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                fillTable();
            }
        });

    }

    private void fillTable () {
        this.tableModel.setRowCount(0);
        List<Product> categories = this.getListProducts();
        for(Product product : categories) {
            this.tableModel.addRow(new Object[] { product.getId(), product.getName(), product.getDescription(), product.getCost_price(), product.getSale_price(), product.getInventory_min(), product.getQuantity(), product.getCategoryId() });
        }
    }

    private void clearFieldsForm () {
        this.fieldName.setText("");
        this.fieldCostPrice.setText("");
        this.fieldCostPrice.setText("");
        this.fieldInventoryMin.setText("");
        this.fieldDescription.setText("");
        this.comboCategory.setSelectedIndex(0);
    }

    private List<Product> getListProducts () {
        return this.productController.list();
    }

    private List<Provider> getListProviders () {
        return this.providerController.list();
    }

    private List<Category> getListCategories () {
        return this.categoryController.list();
    }

    private void save () {
        if(fieldName.getText().equals("") || fieldDescription.getText().equals("") || fieldCostPrice.getText().equals("") || fieldSalePrice.getText().equals("") || fieldInventoryMin.getText().equals("") || ((Category) comboCategory.getSelectedItem()).getName().equals("Select")) {
            JOptionPane.showMessageDialog(this, "No fields can be null");
            return;
        }
        Category category = (Category) comboCategory.getSelectedItem();
        Product product = new Product(fieldName.getText(), fieldDescription.getText(), Double.valueOf(fieldSalePrice.getText()), Double.valueOf(fieldCostPrice.getText()), category.getId(), ((Provider) comboProvider.getSelectedItem()).getId(), Integer.valueOf(fieldInventoryMin.getText()));
        this.productController.save(product);
    }

    private void delete () {
        Object objectRow = (Object) tableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        if(!(objectRow instanceof Integer)) {
            JOptionPane.showMessageDialog(this, "Please select id");
            return;
        }

        Integer id = (Integer) objectRow;
        tableModel.removeRow(table.getSelectedRow());
        this.productController.delete(id);
        JOptionPane.showMessageDialog(this, "Item deleted successfully!");
    }

    private void update () {
        Object objectRow = (Object) tableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        if(!(objectRow instanceof Integer)) {
            JOptionPane.showMessageDialog(this, "Please select id");
            return;
        }

        Integer id = (Integer) objectRow;
        String name = String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 1));
        String description = String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 2));
        Double costPrice = Double.valueOf(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 3)));
        Double salePrice = Double.valueOf(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 4)));
        Integer inventoryMin = Integer.valueOf(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 5)));
        Integer categoryId = Integer.valueOf(String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 7)));

        this.productController.update(id, name, description, costPrice, salePrice, inventoryMin, categoryId);
        JOptionPane.showMessageDialog(this, "Item updated successfully!");
    }

}
