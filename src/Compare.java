
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Compare extends javax.swing.JFrame {

    /**
     * Creates new form Compare
     */
    Laptop firstProduct, secondProduct;

    public Compare() {
        initComponents();

    }

    public void setData(Laptop firstProduct, Laptop secondProduct) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        this.firstProduct = firstProduct;
        this.secondProduct = secondProduct;
        displayProducts();

    }

    public void displayProducts() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {

        // First product labels
        ImageIcon icon1 = new javax.swing.ImageIcon(getClass().getResource(firstProduct.getImg()));
        firstProductImageLabel.setIcon(icon1);
        firstProductHeaderLabel.setText(firstProduct.getBrand() + " " + firstProduct.getModel());
        firstProductPriceLabel.setText(Integer.toString(firstProduct.getPrice()) + " ₺");
        secondProductHeaderLabel.setText(secondProduct.getBrand() + " " + secondProduct.getModel());
        secondProductPriceLabel.setText(Integer.toString(secondProduct.getPrice()) + " ₺");
        firstGPU.setText(firstProduct.getGPU());
        firstCPU.setText(firstProduct.getProcessor());
        firstRAM.setText(Integer.toString(firstProduct.getRam()) + " GB");
        firstStorage.setText(Integer.toString(firstProduct.getStorage()));
        firstScreen.setText(Double.toString(firstProduct.getScreen()) + '"');
        firstWeight.setText(Double.toString(firstProduct.getWeight()) + " KG");
        firstYear.setText(Integer.toString(firstProduct.getYear()));
        firstOS.setText(firstProduct.getOperatingSystem());

        //Second Product Labels
        ImageIcon icon2 = new javax.swing.ImageIcon(getClass().getResource(secondProduct.getImg()));
        secondProductImageLabel.setIcon(icon2);
        secondProductHeaderLabel.setText(secondProduct.getBrand() + " " + secondProduct.getModel());
        secondProductPriceLabel.setText(Integer.toString(secondProduct.getPrice()) + " ₺");
        secondGPU.setText(secondProduct.getGPU());
        secondCPU.setText(secondProduct.getProcessor());
        secondRAM.setText(Integer.toString(secondProduct.getRam()) + " GB");
        secondStorage.setText(Integer.toString(secondProduct.getStorage()));
        secondScreen.setText(Double.toString(secondProduct.getScreen()) + '"');
        secondWeight.setText(Double.toString(secondProduct.getWeight()) + " KG");
        secondYear.setText(Integer.toString(secondProduct.getYear()));
        secondOS.setText(secondProduct.getOperatingSystem());

        //Ranking system
//        if (firstProduct.getGPUscore() > secondProduct.getGPUscore()) {
//            firstGPU.setFont(new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
//        } else if (secondProduct.getGPUscore() > firstProduct.getGPUscore()) {
//            secondGPU.setFont(new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
//        }
        compareForHigh("GPUScore", firstGPU, secondGPU);
        compareForHigh("Ram", firstRAM, secondRAM);
        compareForHigh("Storage", firstStorage, secondStorage);
        compareForHigh("Screen", firstScreen, secondScreen, "double");
        compareForHigh("Year", firstYear, secondYear);

    }

    public void compareForHigh(String spec, JLabel first, JLabel second, String dataType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {

        //accesing getSpec method
        Laptop l = new Laptop();
        Class c = l.getClass();
        Method getSpec = c.getMethod("get" + spec);
        //accesing label
        Class lc = firstGPU.getClass();
        Method setFont = lc.getMethod("setFont", Font.class);
        if (dataType.equals("double")) {

            double score1 = ((double) getSpec.invoke(firstProduct));
            double score2 = ((double) getSpec.invoke(secondProduct));

            if (score1 > score2) {
                //change font size
                setFont.invoke(first, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));

            } else if (score2 > score1) {
                //change font size
                setFont.invoke(second, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
            } else {
                //change font size if equal or null
                setFont.invoke(first, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
                setFont.invoke(second, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));

            }

        } else {
            int score1 = ((int) getSpec.invoke(firstProduct));
            int score2 = ((int) getSpec.invoke(secondProduct));

            if (score1 > score2) {
                //change font size
                setFont.invoke(first, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));

            } else if (score2 > score1) {
                //change font size
                setFont.invoke(second, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
            } else {
                //change font size if equal or null
                setFont.invoke(first, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
                setFont.invoke(second, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));

            }

        }
        //spec scores

        //accesing label for font adjustments
    }

    public void compareForHigh(String spec, JLabel first, JLabel second) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {

        //accesing getSpec method
        Laptop l = new Laptop();
        Class c = l.getClass();
        Method m = c.getMethod("get" + spec);

        //spec scores
        int score1 = ((Integer) m.invoke(firstProduct));
        int score2 = ((Integer) m.invoke(secondProduct));
        //accesing label for font adjustments
        Class lc = firstGPU.getClass();
        Method lm = lc.getMethod("setFont", Font.class);
        if (score1 > score2) {
            //change font size
            lm.invoke(first, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));

        } else if (score2 > score1) {
            //change font size
            lm.invoke(second, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
        } else {
            //change font size if equal or null
            lm.invoke(first, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));
            lm.invoke(second, new Font("Segoe UI", Font.BOLD, 14).deriveFont(Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)));

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        secondProductImageLabel = new javax.swing.JLabel();
        firstProductImageLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        firstProductHeaderLabel = new javax.swing.JLabel();
        secondProductHeaderLabel = new javax.swing.JLabel();
        firstProductPriceLabel = new javax.swing.JLabel();
        secondProductPriceLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        firstGPU = new javax.swing.JLabel();
        secondGPU = new javax.swing.JLabel();
        firstCPU = new javax.swing.JLabel();
        firstRAM = new javax.swing.JLabel();
        firstStorage = new javax.swing.JLabel();
        firstScreen = new javax.swing.JLabel();
        firstWeight = new javax.swing.JLabel();
        firstYear = new javax.swing.JLabel();
        firstOS = new javax.swing.JLabel();
        secondCPU = new javax.swing.JLabel();
        secondRAM = new javax.swing.JLabel();
        secondStorage = new javax.swing.JLabel();
        secondScreen = new javax.swing.JLabel();
        secondWeight = new javax.swing.JLabel();
        secondYear = new javax.swing.JLabel();
        secondOS = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        secondProductImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        firstProductImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setText("GPU :");

        jLabel6.setText("CPU :");

        jLabel7.setText("Ram :");

        jLabel8.setText("Storage :");

        jLabel9.setText("Screen :");

        jLabel10.setText("Weight :");

        firstProductHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstProductHeaderLabel.setText("brand+model(1)");

        secondProductHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondProductHeaderLabel.setText("brand+model (2)");

        firstProductPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstProductPriceLabel.setText("Price1");

        secondProductPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondProductPriceLabel.setText("Price2");

        jLabel1.setText("Year :");

        jLabel2.setText("Operation Sys. :");

        firstGPU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstGPU.setText("GPU1");

        secondGPU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondGPU.setText("GPU2");

        firstCPU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstCPU.setText("CPU1");

        firstRAM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstRAM.setText("RAM1");

        firstStorage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstStorage.setText("Storage1");

        firstScreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstScreen.setText("Screen1");

        firstWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstWeight.setText("Weight1");

        firstYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstYear.setText("Year1");

        firstOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstOS.setText("OS1");

        secondCPU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondCPU.setText("CPU2");

        secondRAM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondRAM.setText("RAM2");

        secondStorage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondStorage.setText("Storage2");

        secondScreen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondScreen.setText("Screen2");

        secondWeight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondWeight.setText("Weight2");

        secondYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondYear.setText("Year2");

        secondOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondOS.setText("OS2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(firstProductImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(firstProductHeaderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstProductPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstGPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(secondProductImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(secondProductHeaderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(secondProductPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(secondGPU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(firstCPU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(firstStorage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(firstRAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(firstYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(firstScreen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(firstOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(firstWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(secondRAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(secondStorage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(secondScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(secondWeight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(secondYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(secondOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(141, 141, 141))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(secondCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstProductImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(secondProductImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstProductHeaderLabel)
                    .addComponent(secondProductHeaderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstProductPriceLabel)
                    .addComponent(secondProductPriceLabel))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(firstGPU)
                    .addComponent(secondGPU))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(firstCPU)
                    .addComponent(secondCPU))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(firstRAM)
                    .addComponent(secondRAM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(firstStorage)
                    .addComponent(secondStorage))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(firstScreen)
                    .addComponent(secondScreen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(firstWeight)
                    .addComponent(secondWeight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(firstYear)
                    .addComponent(secondYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(firstOS)
                    .addComponent(secondOS))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Compare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compare.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compare().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel firstCPU;
    private javax.swing.JLabel firstGPU;
    private javax.swing.JLabel firstOS;
    private javax.swing.JLabel firstProductHeaderLabel;
    private javax.swing.JLabel firstProductImageLabel;
    private javax.swing.JLabel firstProductPriceLabel;
    private javax.swing.JLabel firstRAM;
    private javax.swing.JLabel firstScreen;
    private javax.swing.JLabel firstStorage;
    private javax.swing.JLabel firstWeight;
    private javax.swing.JLabel firstYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel secondCPU;
    private javax.swing.JLabel secondGPU;
    private javax.swing.JLabel secondOS;
    private javax.swing.JLabel secondProductHeaderLabel;
    private javax.swing.JLabel secondProductImageLabel;
    private javax.swing.JLabel secondProductPriceLabel;
    private javax.swing.JLabel secondRAM;
    private javax.swing.JLabel secondScreen;
    private javax.swing.JLabel secondStorage;
    private javax.swing.JLabel secondWeight;
    private javax.swing.JLabel secondYear;
    // End of variables declaration//GEN-END:variables
}
