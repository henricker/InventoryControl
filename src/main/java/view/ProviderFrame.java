package view;

import controller.ProviderController;
import model.Provider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProviderFrame extends JFrame {
    public static final Long serialVersionUID = 1L;
    private JButton saveButton, clearButton, deleteButton, updateButton;
    private JLabel label, labelName, labelCNPJ, labelProviderList, labelListId, labelListName, labelListCNPJ;
    private JTextField fieldName, fieldCNPJ;
    private JTable table;
    private DefaultTableModel tableModel;
    private ProviderController providerController;

    public ProviderFrame(ProviderController providerController) {
        super("Provider management");

        //get Controller
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

        //Label title
        this.label = new JLabel("Provider management");
        this.label.setFont(label.getFont().deriveFont(20.0F));
        this.label.setForeground(Color.pink);
        this.label.setBounds(300, 50, 400, 50);

        //Form to create one category
        this.labelName = new JLabel("name");
        this.labelName.setFont(label.getFont().deriveFont(15.0F));
        this.labelName.setForeground(Color.pink);
        this.labelName.setBounds(300, 100, 400, 50);

        this.fieldName = new JTextField();
        this.fieldName.setBounds(300, 135, 265, 30);

        this.labelCNPJ = new JLabel("cnpj");
        this.labelCNPJ.setFont(label.getFont().deriveFont(15.0F));
        this.labelCNPJ.setForeground(Color.pink);
        this.labelCNPJ.setBounds(300, 170, 400, 50);

        this.fieldCNPJ = new JTextField();
        this.fieldCNPJ.setBounds(300, 205, 265, 30);

        //Create buttons form
        this.saveButton = new JButton("Save");
        this.clearButton = new JButton("Clean");

        saveButton.setBounds(340, 240, 80, 20);
        clearButton.setBounds(440, 240, 80, 20);

        //Category list
        this.labelProviderList = new JLabel("Provider List");
        this.labelProviderList.setFont(label.getFont().deriveFont(15.0F));
        this.labelProviderList.setForeground(Color.pink);
        this.labelProviderList.setBounds(375, 280, 320, 50);

        this.labelListId = new JLabel("ID");
        this.labelListId.setFont(label.getFont().deriveFont(13.0F));
        this.labelListId.setForeground(Color.pink);
        this.labelListId.setBounds(255, 310, 320, 50);

        this.labelListName = new JLabel("Name");
        this.labelListName.setFont(label.getFont().deriveFont(13.0F));
        this.labelListName.setForeground(Color.pink);
        this.labelListName.setBounds(400, 310, 320, 50);

        this.labelListCNPJ = new JLabel("CNPJ");
        this.labelListCNPJ.setFont(label.getFont().deriveFont(13.0F));
        this.labelListCNPJ.setForeground(Color.pink);
        this.labelListCNPJ.setBounds(570, 310, 320, 50);

        //Create table
        String[] columnNames = { "ID", "Name", "CNPJ" };
        this.tableModel = new DefaultTableModel(columnNames, 0);
        this.table = new JTable(tableModel);

        this.fillTable();
        this.table.setBounds(180, 350, 510, 300);

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
        container.add(labelCNPJ);
        container.add(fieldCNPJ);
        container.add(labelProviderList);
        container.add(labelListId);
        container.add(labelListName);
        container.add(labelListCNPJ);
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
        this.fieldCNPJ.setText("");
    }

    private List<Provider> listProviders () {
        return this.providerController.list();
    }

    private void fillTable() {
        this.tableModel.setRowCount(0);
        List<Provider> providers = this.listProviders();
        for(Provider provider : providers) {
            this.tableModel.addRow(new Object[] { provider.getId(), provider.getName(), provider.getCnpj() });
        }
    }

    private void save () {
        if(this.fieldName.getText().equals("") || this.fieldCNPJ.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "No fields can be null");
            return;
        }
        Provider provider = new Provider(this.fieldName.getText(), this.fieldCNPJ.getText());
        this.providerController.save(provider);
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
        this.providerController.delete(id);
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
        String cnpj = (String) tableModel.getValueAt(table.getSelectedRow(), 2);
        this.providerController.update(id, name, cnpj);
        JOptionPane.showMessageDialog(this, "Item updated successfully!");
    }

}
