package view;

import controller.CategoryController;
import model.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryFrame extends JFrame {
    public static final Long serialVersionUID = 1L;
    private JButton saveButton, clearButton, deleteButton, updateButton;
    private JLabel label, labelName, labelCategoryList;
    private JTextField fieldName;
    private JTable table;
    private DefaultTableModel tableModel;
    private CategoryController categoryController;

    public CategoryFrame(CategoryController categoryController) {
        super("Category management");

        //get Controller
        this.categoryController = categoryController;

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

        //Label title
        this.label = new JLabel("Category management");
        this.label.setFont(label.getFont().deriveFont(20.0F));
        this.label.setForeground(Color.pink);
        this.label.setBounds(300, 50, 400, 50);

        //Form to create one category
        this.labelName = new JLabel("Category name");
        this.labelName.setFont(label.getFont().deriveFont(15.0F));
        this.labelName.setForeground(Color.pink);
        this.labelName.setBounds(375, 150, 400, 50);

        this.fieldName = new JTextField();
        this.fieldName.setBounds(300, 210, 265, 30);

        //Create buttons form
        this.saveButton = new JButton("Save");
        this.clearButton = new JButton("Clean");

        saveButton.setBounds(340, 260, 80, 20);
        clearButton.setBounds(440, 260, 80, 20);

        //Category list
        this.labelCategoryList = new JLabel("Category List");
        this.labelCategoryList.setFont(label.getFont().deriveFont(15.0F));
        this.labelCategoryList.setForeground(Color.pink);
        this.labelCategoryList.setBounds(375, 300, 320, 50);

        //Create table
        String[] columnNames = { "ID", "Name" };
        this.tableModel = new DefaultTableModel(columnNames, 0);
        this.table = new JTable(tableModel);

        this.fillTable();
        this.table.setBounds(230, 350, 400, 300);

        //Create buttons form
        this.deleteButton = new JButton("delete");
        this.updateButton = new JButton("update");

        this.deleteButton.setBounds(340, 700, 80, 20);
        this.updateButton.setBounds(440, 700, 80, 20);

        container.add(saveButton);
        container.add(clearButton);

        container.add(label);
        container.add(labelName);
        container.add(fieldName);
        container.add(labelCategoryList);
        container.add(table);
        container.add(deleteButton);
        container.add(updateButton);

        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
                fillTable();
                clearFieldsForm();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFieldsForm();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
                fillTable();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                fillTable();
            }
        });
    }

    private void clearFieldsForm () {
        this.fieldName.setText("");
    }

    private List<Category> listCategories () {
        return this.categoryController.list();
    }

    private void fillTable() {
        this.tableModel.setRowCount(0);
        List<Category> categories = this.listCategories();
        for(Category category : categories) {
            this.tableModel.addRow(new Object[] { category.getId(), category.getName() });
        }
    }

    private void save () {
        if(this.fieldName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Category name cannot be null");
            return;
        }
        Category category = new Category(this.fieldName.getText());
        this.categoryController.save(category);
        JOptionPane.showMessageDialog(this, "Save with successfully!");
    }

    private void delete () {
        Object objectRow = (Object) tableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        if(!(objectRow instanceof Integer)) {
            JOptionPane.showMessageDialog(this, "Please select id");
            return;
        }

        Integer id = (Integer) objectRow;
        tableModel.removeRow(table.getSelectedRow());
        this.categoryController.delete(id);
        JOptionPane.showMessageDialog(this, "Item deleted successfully!");
    }

    private void update () {
        Object objectRow = (Object) tableModel.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        if(!(objectRow instanceof Integer)) {
            JOptionPane.showMessageDialog(this, "Please select id");
            return;
        }

        Integer id = (Integer) objectRow;
        String name = (String) tableModel.getValueAt(table.getSelectedRow(), 1);
        this.categoryController.update(id, name);
        JOptionPane.showMessageDialog(this, "Item updated successfully!");
    }

}
