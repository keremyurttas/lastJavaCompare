
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public final class Menu extends javax.swing.JFrame {

    List<Laptop> laptopList = new ArrayList<>();
    JTable laptopTable = new JTable();
    Object value;
    Laptop product1, product2, activeLaptop;
    int selectedRow = -1;
    int activeID;
    int id1, id2;
    Color defaultColor = new Color(54, 33, 89);
    Color activeColor = new Color(85, 65, 118);
    Color tableColor = new Color(177, 156, 217);

    public Menu() {
        //initialize of components
        initComponents();
        //populate laptop list from file
        populateLaptopList();
        //create table with laptoplist
        createTable();
        //table event listener for table active row changes
        tableEventListener();

        //      appending table to scrollPane
        scrollPane.setViewportView(laptopTable);
    }

    //populate arraylist from file 
    public void populateLaptopList() {

        FileReader data = null;
        try {
            data = new FileReader("data.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader reader = new BufferedReader(data);
        String line;

        try {
            //reads all parts with seperated ',' 
            while ((line = reader.readLine()) != null) {
                //assing all the values of parts
                String[] parts = line.split(",");
                String id = parts[0].trim();
                String brand = parts[1].trim();
                String model = parts[2].trim();
                String price = parts[3].trim();
                String GPU = parts[4].trim();
                String proccesor = parts[5].trim();
                String ram = parts[6].trim();
                String storage = parts[7].trim();
                String screen = parts[8].trim();
                String weight = parts[9].trim();
                String year = parts[10].trim();
                String operatingSystem = parts[11].trim();
                String GPUscore = parts[12].trim();
                String img = parts[13].trim();
                //assign laptop with constructor from txt file values.
                Laptop l = new Laptop(Integer.parseInt(id), brand, model, GPU, proccesor, Integer.parseInt(ram), storage, Double.parseDouble(screen), Double.parseDouble(weight), Integer.parseInt(year), operatingSystem, Integer.parseInt(price), Integer.parseInt(GPUscore), img);
                //add new laptop object to laptoplist
                laptopList.add(l);

            }
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable() {
        //create a non editable model
        nonEditableTableModel model = new nonEditableTableModel();
        //add columns to the model
        model.addColumn("ID");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Price");

        //add row for each element of array
        for (Laptop laptop : laptopList) {
            model.addRow(new Object[]{laptop.getId(), laptop.getBrand(), laptop.getModel(), laptop.getPrice()});
        }

        //set maximum 1 selection at the same time
        laptopTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //disable header edit and drag
        laptopTable.getTableHeader().setEnabled(false);
        //assign model to the table
        laptopTable.setShowHorizontalLines(false);
        laptopTable.setShowVerticalLines(false);
        laptopTable.setModel(model);
    }

    public class nonEditableTableModel extends DefaultTableModel {

        //extends from defaultTableModel and set isCellEditable to false for each cell
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }

    public void tableEventListener() {
        //add list selection event to laptopTable
        ListSelectionModel laptopModel = laptopTable.getSelectionModel();
        laptopModel.addListSelectionListener(new ListSelectionListener() {
            // function to trigger when list selection index changed  

            public void valueChanged(ListSelectionEvent e) {
                //checking if there is a selection
                if (!laptopModel.isSelectionEmpty()) {
                    //customized selection background color
                    laptopTable.setSelectionBackground(tableColor);

                    //get selected row's first column value whic is id
                    selectedRow = laptopModel.getMinSelectionIndex();
                    value = laptopTable.getValueAt(selectedRow, 0);

                    //set active ID and activeLaptop
                    activeID = Integer.parseInt(value.toString());
                    activeLaptop = laptopList.get(findProductIndex(activeID));

                    //displays active product on the right side in menu
                    displayActiveProduct(activeLaptop);
                }
            }

        });
    }

    public void displayActiveProduct(Laptop laptop) {
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(laptop.getImg()));
        imageLabel.setIcon(icon);
        productHeaderLabel.setText(laptop.getBrand() + " " + laptop.getModel());
        priceLabel.setText(laptop.getPrice() + " ₺");

    }

    //find the index of given id in laptopList arraylist
    public int findProductIndex(int id) {

        int index = -1;
        for (int i = 0; i < laptopList.size(); i++) {
            if (laptopList.get(i).getId() == id) {
                index = i;
                break;

            }
        }
        return index;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        productHeaderLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        clearButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        clearButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1050, 660));
        setMinimumSize(new java.awt.Dimension(1050, 660));
        setPreferredSize(new java.awt.Dimension(1050, 660));
        setResizable(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Model", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPane.setViewportView(table);

        jPanel1.setBackground(new java.awt.Color(54, 33, 89));

        jButton5.setBackground(new java.awt.Color(54, 33, 89));
        jButton5.setFont(new java.awt.Font("URW Gothic", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon("/home/kerem/Desktop/icons8-vs-64.png")); // NOI18N
        jButton5.setText("Compare");
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton5.setMaximumSize(new java.awt.Dimension(128, 30));
        jButton5.setMinimumSize(new java.awt.Dimension(128, 30));
        jButton5.setPreferredSize(new java.awt.Dimension(128, 30));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(85, 65, 118));
        jButton6.setFont(new java.awt.Font("URW Gothic", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon("/home/kerem/Desktop/icons8-laptop-64.png")); // NOI18N
        jButton6.setText("All Products");
        jButton6.setBorderPainted(false);
        jButton6.setFocusable(false);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton6.setMaximumSize(new java.awt.Dimension(128, 30));
        jButton6.setMinimumSize(new java.awt.Dimension(128, 30));
        jButton6.setPreferredSize(new java.awt.Dimension(128, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(122, 71, 221));

        imageLabel.setText("Image");
        imageLabel.setMaximumSize(new java.awt.Dimension(480, 360));
        imageLabel.setMinimumSize(new java.awt.Dimension(480, 360));
        imageLabel.setPreferredSize(new java.awt.Dimension(480, 360));

        productHeaderLabel.setFont(new java.awt.Font("URW Gothic", 0, 24)); // NOI18N
        productHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        productHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productHeaderLabel.setText("Product Brand and Model");

        priceLabel.setFont(new java.awt.Font("Waree", 3, 18)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        priceLabel.setText("jLabel13");
        priceLabel.setToolTipText("");

        jButton1.setBackground(new java.awt.Color(122, 71, 221));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Product #1");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.white));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        clearButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-close-16.png"))); // NOI18N
        clearButton1.setMaximumSize(new java.awt.Dimension(16, 23));
        clearButton1.setMinimumSize(new java.awt.Dimension(16, 23));
        clearButton1.setPreferredSize(new java.awt.Dimension(16, 23));
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(122, 71, 221));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Product #2");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.white));
        jButton2.setFocusable(false);
        jButton2.setMaximumSize(new java.awt.Dimension(101, 45));
        jButton2.setMinimumSize(new java.awt.Dimension(101, 45));
        jButton2.setPreferredSize(new java.awt.Dimension(101, 45));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        clearButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-close-16.png"))); // NOI18N
        clearButton2.setMaximumSize(new java.awt.Dimension(16, 23));
        clearButton2.setMinimumSize(new java.awt.Dimension(16, 23));
        clearButton2.setPreferredSize(new java.awt.Dimension(16, 23));
        clearButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(productHeaderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productHeaderLabel)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clearButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(priceLabel)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //check if there is selected row
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "You haven't choose any product");
        } //check if both of the selected items same 
        else if (activeID == id2) {
            JOptionPane.showMessageDialog(null, "Same products can't compare");
        } else {
            //set selection bg to null for remove to active rows bg
            laptopTable.setSelectionBackground(null);
            // setting product infos.
            id1 = activeID;
            //assign selected row -1 for new selection
            selectedRow = -1;
            //assign product2 to active laptop
            product1 = activeLaptop;
            //set button's label
            jButton1.setText(product1.getBrand() + " " + product1.getModel());

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //check if there is selected row
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "You haven't choose any product");
        } //check if both of the selected items same
        else if (activeID == id1) {

            JOptionPane.showMessageDialog(null, "Same products can't compare");
        } else {

            //set selection bg to null for remove to active rows bg
            laptopTable.setSelectionBackground(null);
            // setting product infos.
            id2 = activeID;
            //assign selected row -1 for new selection
            selectedRow = -1;
            //assign product2 to active laptop
            product2 = activeLaptop;
            //set button's label
            jButton2.setText(product2.getBrand() + " " + product2.getModel());
    }//GEN-LAST:event_jButton2ActionPerformed
    }

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        jButton1.setText("Product #1");
        product1 = null;

    }//GEN-LAST:event_clearButton1ActionPerformed

    private void clearButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton2ActionPerformed
        jButton2.setText("Product #2");
        product2 = null;
    }//GEN-LAST:event_clearButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Compare compare = new Compare();
        //send product 1 and 2 to compare class for comprasion with setData method
        compare.setData(product1, product2);
        compare.setVisible(true);
        jButton1.setText("Product #1");
        jButton2.setText("Procuct #2");

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "You are already in All Products page!");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        jButton6.setBackground(defaultColor);
        jButton5.setBackground(activeColor);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton6.setBackground(activeColor);
        jButton5.setBackground(defaultColor);
    }//GEN-LAST:event_jButton5MouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton1;
    private javax.swing.JButton clearButton2;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel productHeaderLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
