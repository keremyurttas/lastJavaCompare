
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
    String searchText;

    public Menu() {
        //initialize of components
        initComponents();
        //populate laptop list from file
        populateLaptopList();
        //create table with laptoplist
        createTable();
        designTable();
        //table event listener for table active row changes
        tableEventListener();

        //      appending table to scrollPane
        scrollPane.setViewportView(laptopTable);
    }

    public void search() {

        searchText = searchField.getText();
        System.out.println(searchText.trim());
//        if (searchText.trim().isEmpty()) {
//            System.out.println("oo");
//            laptopTable.setRowSelectionInterval(1, 1);
//
//        }

        TableModel model = laptopTable.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < model.getColumnCount(); col++) {
                if (model.getValueAt(row, col).toString().contains(searchText)) {
                    // If a match is found, select the row and break
                    laptopTable.setRowSelectionInterval(row, row);
                    break;
                }
            }
        }
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
                String GPUScore = parts[12].trim();
                String CPUScore = parts[13].trim();
                String img = parts[14].trim();
                //assign laptop with constructor from txt file values.
                Laptop l = new Laptop(Integer.parseInt(id), brand, model, GPU, proccesor, Integer.parseInt(ram), Integer.parseInt(storage), Double.parseDouble(screen), Double.parseDouble(weight), Integer.parseInt(year), operatingSystem, Integer.parseInt(price), Integer.parseInt(GPUScore), Integer.parseInt(CPUScore), img);
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
        model.addColumn("BRAND");
        model.addColumn("MODEL");
        model.addColumn("PRICE");

        //add row for each element of array
        for (Laptop laptop : laptopList) {
            model.addRow(new Object[]{laptop.getId(), laptop.getBrand(), laptop.getModel(), laptop.getPrice() + " ₺"});
        }
        TableRowSorter myTableRowSorter = new TableRowSorter(model);
        laptopTable.setRowSorter(myTableRowSorter);
        laptopTable.setModel(model);

        searchField.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                searchText = searchField.getText().toUpperCase();
                System.out.println(searchText);
                myTableRowSorter.setRowFilter(new MyRowFilter(searchText));
//                search();

            }

            public void removeUpdate(DocumentEvent e) {
                searchText = searchField.getText();
                myTableRowSorter.setRowFilter(new MyRowFilter(searchText));
//                search();

            }

            public void insertUpdate(DocumentEvent e) {
                searchText = searchField.getText();

// Split the string into an array of words
                String[] words = searchText.split(" ");

// Loop through the array of words
                for (int i = 0; i < words.length; i++) {
                    // Get the first character of the word
                    char firstChar = words[i].charAt(0);
                    // Convert the first character to uppercase
                    firstChar = Character.toUpperCase(firstChar);
                    // Replace the first character of the word with the uppercase version
                    words[i] = firstChar + words[i].substring(1);
                }
                String output = String.join(" ", words);

                System.out.println(output);
                myTableRowSorter.setRowFilter(new MyRowFilter(output));
//                search();

            }

        });

    }

    public class nonEditableTableModel extends DefaultTableModel {

        //extends from defaultTableModel and set isCellEditable to false for each cell
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }

    public void designTable() {
        //set maximum 1 selection at the same time
        laptopTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //disable header edit and drag
//        laptopTable.getTableHeader().setEnabled(false);
        laptopTable.getTableHeader().setBackground(Color.WHITE);

//        column.setPreferredWidth(50);
        //assign model to the table
        laptopTable.setShowHorizontalLines(false);
        laptopTable.setShowVerticalLines(false);
        laptopTable.getColumnModel().getColumn(0).setMaxWidth(58);
        laptopTable.getColumnModel().getColumn(1).setMaxWidth(300);
        laptopTable.getColumnModel().getColumn(2).setMaxWidth(300);
        laptopTable.getColumnModel().getColumn(3).setMaxWidth(150);
        Color tableHeaderColor = new Color(122, 71, 221);
        laptopTable.getTableHeader().setBackground(tableHeaderColor);
        laptopTable.getTableHeader().setForeground(Color.WHITE);
        laptopTable.getTableHeader().setFont(new java.awt.Font("Ubuntu", 1, 14));

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
        jPanel1 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        productHeaderLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        clearButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        clearButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1050, 660));
        setMinimumSize(new java.awt.Dimension(1050, 660));
        setUndecorated(true);
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
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-vs-64.png"))); // NOI18N
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
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-laptop-64.png"))); // NOI18N
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

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Lohit Telugu", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LaptopVersus");

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(122, 71, 221));

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-laptop-100.png"))); // NOI18N
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
        priceLabel.setText("******* TL");
        priceLabel.setToolTipText("");

        jButton1.setBackground(new java.awt.Color(122, 71, 221));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-plus-key-24.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.white));
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setMaximumSize(new java.awt.Dimension(120, 45));
        jButton1.setMinimumSize(new java.awt.Dimension(120, 45));
        jButton1.setPreferredSize(new java.awt.Dimension(120, 45));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        clearButton1.setBackground(new java.awt.Color(122, 71, 221));
        clearButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-trash-can-24.png"))); // NOI18N
        clearButton1.setBorder(null);
        clearButton1.setFocusable(false);
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
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-plus-key-24.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, java.awt.Color.white));
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setMaximumSize(new java.awt.Dimension(120, 45));
        jButton2.setMinimumSize(new java.awt.Dimension(120, 45));
        jButton2.setPreferredSize(new java.awt.Dimension(120, 45));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        clearButton2.setBackground(new java.awt.Color(122, 71, 221));
        clearButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-trash-can-24.png"))); // NOI18N
        clearButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        clearButton2.setBorderPainted(false);
        clearButton2.setFocusable(false);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(productHeaderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(clearButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(productHeaderLabel)))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(clearButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(priceLabel)
                        .addGap(11, 11, 11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(252, 194, 76));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 33, 89)));
        jPanel3.setPreferredSize(new java.awt.Dimension(267, 25));

        searchField.setBackground(new java.awt.Color(252, 194, 76));
        searchField.setBorder(null);
        searchField.setMinimumSize(new java.awt.Dimension(64, 23));
        searchField.setPreferredSize(new java.awt.Dimension(64, 23));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-magnifying-glass-20.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(240, 240, 240));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pics/icons8-close-window-64.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(97, 97, 97))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
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
        jButton1.setText(null);
        id1 = -1;
        product1 = null;

    }//GEN-LAST:event_clearButton1ActionPerformed

    private void clearButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton2ActionPerformed
        jButton2.setText(null);
        id2 = -1;
        product2 = null;
    }//GEN-LAST:event_clearButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (product1 == null || product2 == null) {
            JOptionPane.showMessageDialog(null, "You have to select 2 products to compare!");
        } else {
            Compare compare = new Compare();
            try {
                //send product 1 and 2 to compare class for comprasion with setData method
                compare.setData(product1, product2);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            compare.setVisible(true);
            id1 = -1;
            product1 = null;
            id2 = -1;
            product2 = null;
            jButton1.setText(null);
            jButton2.setText(null);
        }

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

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel productHeaderLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField searchField;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
