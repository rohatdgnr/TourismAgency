package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Layout extends JFrame {

    public void createTableStringLines(JTextArea textArea, Object[] columns, int hotelId, List<String[]> properties) {
        // JTextArea içeriğini temizle
        textArea.setText("");

        // Tablo başlıklarını yazdır
        textArea.append(columns[0].toString() + "\t" + columns[1].toString() + "\n");

        // Özellikleri alt alta yazdır
        for (String[] property : properties) {
            for (int i = 0; i < property.length; i++) {
                textArea.append(hotelId + "\t"); // Otel ID'sini yazdır
                textArea.append(property[i]);
                if (i < property.length - 1) {
                    textArea.append("\n");

                }
            }

            textArea.append("\n"); // Bir sonraki özellik için yeni satır ekle


        }

        // JTextArea'nın düzenlenemez hale getirilmesi (opsiyonel)
        textArea.setEditable(false);

        // JTextArea'nın boyutunu ayarla
        //textArea.setPreferredSize(new Dimension(200, 100));
    }


    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows == null) {
            rows = new ArrayList<>();
        }
        for (Object[] row : rows) {
            model.addRow(row);
        }

    }

    public void guiInitilaze(int width, int height) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Turizim Acente");
        setSize(width, height);
        setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setVisible(true);
    }


    // JTable'dan belirli bir satırdaki veriyi elde etmek için metod
    public static int getTableSelectedRow(JTable table, int hotelIdColumnIndex) {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) { // Eğer bir satır seçilmişse
            // Seçili satırdaki belirli sütunun değerini al
            Object hotelIdObj = table.getValueAt(selectedRow, hotelIdColumnIndex);

            if (hotelIdObj instanceof Integer) {
                return (Integer) hotelIdObj; // Hotel ID'sini integer olarak dön
            }
        }

        return -1; // Seçili bir satır yoksa veya hotel ID'si alınamazsa -1 dön
    }

    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
}
