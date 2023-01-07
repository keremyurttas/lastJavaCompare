
import javax.swing.RowFilter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kerem
 */
public class MyRowFilter extends RowFilter {

    private final String searchText;

    MyRowFilter(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public boolean include(Entry entry) {

        return entry.getStringValue(1).contains(searchText) || entry.getStringValue(2).contains(searchText);

    }

}
