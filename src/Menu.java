
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
                String img = parts[12].trim();
                //assign laptop with constructor from txt file values.
                Laptop l = new Laptop(Integer.parseInt(id), brand, model, GPU, proccesor, Integer.parseInt(ram), storage, Double.parseDouble(screen), Double.parseDouble(weight), Integer.parseInt(year), operatingSystem, Integer.parseInt(price), img);
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
                    laptopTable.setSelectionBackground(Color.MAGENTA);

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
        priceLabel.setText(laptop.getPrice() + " TL");

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
        jButton1 = new javax.swing.JButton();
        productHeaderLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(962, 601));

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

        jButton1.setText("Product #1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        productHeaderLabel.setText("Product Brand and Model");

        priceLabel.setText("jLabel13");

        jButton2.setText("Product #2");
        jButton2.setMaximumSize(new java.awt.Dimension(97, 22));
        jButton2.setMinimumSize(new java.awt.Dimension(97, 22));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        imageLabel.setText("Image");
        imageLabel.setMaximumSize(new java.awt.Dimension(480, 360));
        imageLabel.setMinimumSize(new java.awt.Dimension(480, 360));
        imageLabel.setPreferredSize(new java.awt.Dimension(480, 360));

        jMenu1.setText("Menu");

        jMenuItem2.setText("exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        jMenu2.setText("Compare");

        jMenuItem5.setText("Compare");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(productHeaderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(priceLabel)))
                .addGap(401, 401, 401))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(productHeaderLabel)
                        .addGap(18, 18, 18)
                        .addComponent(priceLabel)
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Compare compare = new Compare();
        //send product 1 and 2 to compare class for comprasion with setData method
        compare.setData(product1, product2);
        compare.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel productHeaderLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
