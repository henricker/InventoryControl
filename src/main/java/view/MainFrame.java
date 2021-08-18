package view;

import controller.CategoryController;
import controller.MovementController;
import controller.ProductController;
import controller.ProviderController;
import database.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Connection;

public class MainFrame extends JFrame {
    public static final Long serialVersionUID = 1L;
    private JButton categoryButton, productButton, movimentProductButton, providerButton;
    private CategoryController categoryController;
    private ProductController productController;
    private ProviderController providerController;
    private MovementController movementController;
    private Connection connection;
    private CategoryFrame categoryFrame;
    private ProductFrame productFrame;
    private ProviderFrame providerFrame;
    private MovementFrame movementFrame;

    public MainFrame() {
        super("Inventory control");
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

        //Buttons
        this.providerButton = new JButton("Provider management");
        this.categoryButton = new JButton("Category management");
        this.productButton = new JButton("Product management");
        this.movimentProductButton = new JButton("Movement management");

        providerButton.setBounds(305, 240, 200, 50);
        categoryButton.setBounds(305, 320, 200, 50);
        productButton.setBounds(305, 400, 200, 50);
        movimentProductButton.setBounds(305, 480, 200, 50);

        //JLable
        JLabel label = new JLabel("   Inventory control system");
        label.setFont(label.getFont().deriveFont(30.0F));
        label.setForeground(Color.pink);
        label.setBounds(200, 50, 400, 50);

        //Add in container
        container.add(label);
        container.add(providerButton);
        container.add(categoryButton);
        container.add(productButton);
        container.add(movimentProductButton);

        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);

        //init connection and init controllers
        this.connection = ConnectionFactory.getConnection();
        this.categoryController = new CategoryController(connection);
        this.productController = new ProductController(connection);
        this.providerController = new ProviderController(connection);
        this.movementController = new MovementController(connection);

        //Events on buttons
        providerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(providerFrame == null) {
                    providerFrame = new ProviderFrame(providerController);
                    providerFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    providerFrame.addComponentListener(new ComponentListener() {
                        @Override
                        public void componentResized(ComponentEvent e) {

                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {

                        }

                        @Override
                        public void componentShown(ComponentEvent e) {

                        }

                        @Override
                        public void componentHidden(ComponentEvent e) { providerFrame = null; }
                    });
                }
            }
        });
        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(categoryFrame == null) {
                    categoryFrame = new CategoryFrame(categoryController);
                    categoryFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    categoryFrame.addComponentListener(new ComponentListener() {
                        @Override
                        public void componentResized(ComponentEvent e) {

                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {

                        }

                        @Override
                        public void componentShown(ComponentEvent e) {

                        }

                        @Override
                        public void componentHidden(ComponentEvent e) {
                            categoryFrame = null;
                        }
                    });
                }
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productFrame == null) {
                    productFrame = new ProductFrame(categoryController, productController, providerController);
                    productFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    productFrame.addComponentListener(new ComponentListener() {
                        @Override
                        public void componentResized(ComponentEvent e) {

                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {

                        }

                        @Override
                        public void componentShown(ComponentEvent e) {

                        }

                        @Override
                        public void componentHidden(ComponentEvent e) {
                            productFrame = null;
                        }
                    });
                }
            }
        });

        movimentProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(movementFrame == null) {
                    movementFrame = new MovementFrame(movementController, productController);
                    movementFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    movementFrame.addComponentListener(new ComponentListener() {
                        @Override
                        public void componentResized(ComponentEvent e) {

                        }

                        @Override
                        public void componentMoved(ComponentEvent e) {

                        }

                        @Override
                        public void componentShown(ComponentEvent e) {

                        }

                        @Override
                        public void componentHidden(ComponentEvent e) { movementFrame = null; }
                    });
                }
            }
        });


    }
}
