/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import View.MovieView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author TUF
 */
public class Controller {
    Model model;
    MovieView view;
    String data;

    public Controller(Model model,MovieView view) {
        this.model = model;
        this.view = view;
        
        if (model.getBanyakData()!=0) {
            String dataMovie[][] = model.readMovie();
            view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
        }
        else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        
        view.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = view.getJudul();
                double alur = Double.parseDouble(view.getAlur());
                double penokohan = Double.parseDouble(view.getPenokohan());
                double akting = Double.parseDouble(view.getAkting());
                if (alur > 5.0 || penokohan > 5.0 || akting > 5.0){
                    JOptionPane.showMessageDialog(null, "Maksimal inputan adalah 5");
                }else if(alur < 0 || penokohan < 0 || akting < 0){
                    JOptionPane.showMessageDialog(null, "Minimal inputan adalah 0");
                } else {
                    model.createMovie(judul,alur,penokohan,akting);
                    String dataMovie[][] = model.readMovie();
                    view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
                }
            }
        });
        
        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String judul = view.getJudul();
                double alur = Double.parseDouble(view.getAlur());
                double penokohan = Double.parseDouble(view.getPenokohan());
                double akting = Double.parseDouble(view.getAkting());
                if (alur > 5.0 || penokohan > 5.0 || akting > 5.0){
                    JOptionPane.showMessageDialog(null, "Maksimal inputan adalah 5");
                }else if(alur < 0 || penokohan < 0 || akting < 0){
                    JOptionPane.showMessageDialog(null, "Minimal inputan adalah 0");
                } else {
                    model.updateMovie(judul,alur,penokohan,akting);
                    String dataMovie[][] = model.readMovie();
                    view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
                }
            }
        });
        
        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               int input = JOptionPane.showConfirmDialog(null,
                "Apa anda ingin menghapus film ?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    model.deleteMovie(data);
                    String dataMovie[][] = model.readMovie();
                    view.tabel.setModel((new JTable(dataMovie, view.namaKolom)).getModel());
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                }
            }
        });
        
        view.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.tfJudul.setText("");
                view.tfAlur.setText("");
                view.tfPenokohan.setText("");
                view.tfAkting.setText("");
            }
        });
        
        view.tabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int row = view.tabel.getSelectedRow();
                    data = view.tabel.getValueAt(row, 0).toString();
                    String dataUpdate[] = new String[4];
                    dataUpdate[0] = view.tabel.getValueAt(row, 0).toString();
                    dataUpdate[1] = view.tabel.getValueAt(row, 1).toString();
                    dataUpdate[2] = view.tabel.getValueAt(row, 2).toString();
                    dataUpdate[3] = view.tabel.getValueAt(row, 3).toString();
                    view.setTfJudul(dataUpdate[0]);
                    view.setTfAlur(dataUpdate[1]);
                    view.setTfPenokohan(dataUpdate[2]);
                    view.setTfAkting(dataUpdate[3]);
                    System.out.println(data);
                }
        });
    }
}
