/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author kerem
 */
public class MainClass {

    public static void main(String[] args) {
        Laptop mac = new Laptop("sa", "as", 10.5, 100.5, 10.6, 100, "asd", "asdsa", 54, "saaaa", 20, 20);
        try {
            FileOutputStream file = new FileOutputStream("products.txt");
            ObjectOutputStream outputProducts = new ObjectOutputStream(file);
            outputProducts.writeObject(mac);
            outputProducts.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
