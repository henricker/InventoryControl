package view;

import controller.MovementController;
import controller.ProductController;
import model.Movement;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MovementFrame extends JFrame {
    public static final Long serialVersionUID = 1L;
    private JLabel labelProductId, labelQuantityMovement, labelTypeMoviment, label, labelProductList;
    private JLabel labelListId, labelListProductId, labelListQuantityMoviment, labelListType, labelListPrice, labelListDate ;
    private JTextField fieldQuantityMoviment;
    private JComboBox<Product> comboProduct;
    private JComboBox<String> comboType;
    private JButton buttonSave, buttonUpdate, buttonClear, buttonDelete;
    private JTable table;
    private DefaultTableModel tableModel;

    private MovementController movementController;
    private ProductController productController;

    public MovementFrame(MovementController movementController, ProductController productController) {
        super("Movement management");

        //get Controller
        this.movementController = movementController;
        this.productController = productController;
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

        this.label = new JLabel("Movement management");
        this.label.setFont(label.getFont().deriveFont(20.0F));
        this.label.setForeground(Color.pink);
        this.label.setBounds(300, 0, 400, 50);

        this.labelProductId = new JLabel("Product id");
        this.labelProductId.setFont(label.getFont().deriveFont(12.0F));
        this.labelProductId.setForeground(Color.pink);
        this.labelProductId.setBounds(2, 30, 400, 50);

        this.comboProduct = new JComboBox<Product>();
        this.comboProduct.addItem(new Product("Select", "", 0.0, 0.0, 0, 0, 0));
        List<Product> products = this.getListProducts();
        products.stream().forEach(product -> this.comboProduct.addItem(product));
        this.comboProduct.setBounds(2, 70, 265, 30);

        this.labelQuantityMovement = new JLabel("Quantity movement");
        this.labelQuantityMovement.setFont(label.getFont().deriveFont(12.0F));
        this.labelQuantityMovement.setForeground(Color.pink);
        this.labelQuantityMovement.setBounds(2, 110, 400, 50);

        this.fieldQuantityMoviment = new JTextField();
        this.fieldQuantityMoviment.setBounds(2, 150, 265, 30);

        this.labelTypeMoviment = new JLabel("Type moviment");
        this.labelTypeMoviment.setFont(label.getFont().deriveFont(12.0F));
        this.labelTypeMoviment.setForeground(Color.pink);
        this.labelTypeMoviment.setBounds(2, 180, 400, 50);

        this.comboType = new JComboBox<String>();
        this.comboType.addItem("Select");
        this.comboType.addItem("IN");
        this.comboType.addItem("OUT");
        this.comboType.setBounds(2, 220, 265, 30);

        //Create buttons form
        this.buttonSave = new JButton("Save");
        this.buttonClear = new JButton("Clean");

        this.buttonSave.setBounds(42, 270, 80, 20);
        this.buttonClear.setBounds(142, 270, 80, 20);

        //Product list
        this.labelProductList = new JLabel("Movement List");
        this.labelProductList.setFont(label.getFont().deriveFont(15.0F));
        this.labelProductList.setForeground(Color.pink);
        this.labelProductList.setBounds(490, 30, 320, 50);

        this.labelListId = new JLabel("ID");
        this.labelListId.setFont(label.getFont().deriveFont(13.0F));
        this.labelListId.setForeground(Color.pink);
        this.labelListId.setBounds(310, 95, 100, 50);

        this.labelListProductId = new JLabel("product_id");
        this.labelListProductId.setFont(label.getFont().deriveFont(13.0F));
        this.labelListProductId.setForeground(Color.pink);
        this.labelListProductId.setBounds(400, 95, 100, 50);

        this.labelListQuantityMoviment = new JLabel("quantity");
        this.labelListQuantityMoviment.setFont(label.getFont().deriveFont(13.0F));
        this.labelListQuantityMoviment.setForeground(Color.pink);
        this.labelListQuantityMoviment.setBounds(520, 95, 100, 50);

        this.labelListType = new JLabel("type");
        this.labelListType.setFont(label.getFont().deriveFont(13.0F));
        this.labelListType.setForeground(Color.pink);
        this.labelListType.setBounds(620, 95, 100, 50);

        this.labelListPrice = new JLabel("price");
        this.labelListPrice.setFont(label.getFont().deriveFont(13.0F));
        this.labelListPrice.setForeground(Color.pink);
        this.labelListPrice.setBounds(740, 95, 100, 50);

        this.labelListDate = new JLabel("date");
        this.labelListDate.setFont(label.getFont().deriveFont(13.0F));
        this.labelListDate.setForeground(Color.pink);
        this.labelListDate.setBounds(840, 95, 100, 50);

        //Create table
        this.table = new JTable();
        this.tableModel = (DefaultTableModel) this.table.getModel();

        this.tableModel.addColumn("ID");
        this.tableModel.addColumn("product_id");
        this.tableModel.addColumn("quantity_moviment");
        this.tableModel.addColumn("type");
        this.tableModel.addColumn("sale_price");
        this.tableModel.addColumn("price");
        this.tableModel.addColumn("date");

        this.table.setBounds(280, 130, 750, 620);

        this.fillTable();

        this.buttonDelete = new JButton("delete");
        this.buttonUpdate = new JButton("update");

        this.buttonDelete.setBounds(340, 760, 120, 40);
        this.buttonUpdate.setBounds(790, 760, 120, 40);

        container.add(label);
        container.add(labelProductId);
        container.add(comboProduct);
        container.add(labelQuantityMovement);
        container.add(fieldQuantityMoviment);
        container.add(labelTypeMoviment);
        container.add(comboType);
        container.add(buttonSave);
        container.add(buttonClear);
        container.add(labelProductList);
        container.add(table);
        container.add(labelListId);
        container.add(labelListProductId);
        container.add(labelListQuantityMoviment);
        container.add(labelListType);
        container.add(labelListPrice);
        container.add(labelListDate);


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
    }

    public void save () {
        if(this.fieldQuantityMoviment.getText().equals("") || ((Product) this.comboProduct.getSelectedItem()).getName().equals("Select") || ((String) this.comboType.getSelectedItem()).equals("Select")) {
            JOptionPane.showMessageDialog(this, "No fields can be null");
            return;
        }

        if(((Product)this.comboProduct.getSelectedItem()).getQuantity() < Integer.valueOf(this.fieldQuantityMoviment.getText()) && ((String) this.comboType.getSelectedItem()).equals("OUT")) {
            JOptionPane.showMessageDialog(this, "QuantityMoviment is less than quantity of product", "Error", 2);
            return;
        }

        if(((Product) this.comboProduct.getSelectedItem()).getInventory_min() >= (Integer.valueOf(this.fieldQuantityMoviment.getText())) && ((String) this.comboType.getSelectedItem()).equals("OUT")) {
            JOptionPane.showMessageDialog(this, "Minimum stock alert", "Error", 2);
        }

        Integer productId = ((Product) this.comboProduct.getSelectedItem()).getId();
        Integer quantityMoviment = (Integer.valueOf(this.fieldQuantityMoviment.getText()));
        Movement.TypesMovement type = Movement.TypesMovement.valueOf(((String) this.comboType.getSelectedItem()));
        this.movementController.save(new Movement(productId, quantityMoviment, type));
    }

    private void clearFieldsForm () {
        comboProduct.setSelectedIndex(0);
        comboType.setSelectedIndex(0);
        fieldQuantityMoviment.setText("");
    }

    private void fillTable () {
        this.tableModel.setRowCount(0);
        List<Movement> movements = this.getListMovement();
        for(Movement movement : movements) {
            this.tableModel.addRow(new Object[] { movement.getId(), movement.getProductId(), movement.getQuantityMovement(), movement.getType(), movement.getPrice(), movement.getDateMovement()  });
        }
    }

    private List<Product> getListProducts () {
        return this.productController.list();
    }
    private List<Movement> getListMovement () { return this.movementController.list(); }
}
