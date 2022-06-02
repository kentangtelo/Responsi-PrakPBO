/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author TUF
 */
public class Model {
    protected String moviename;
    protected double alur,penokohan,akting,nilai;
    
    Connector connection;
    Statement statement;
    
    public Model(){
        connection = new Connector();
    }
    
    public void createMovie(String nama, double alur, double penokohan,double akting){
        try{
           nilai = (float) ((alur + penokohan + akting)/3.0);
           statement = (Statement) connection.koneksi.createStatement();
           String sql = "INSERT INTO movie VALUES('" +nama+ "',"+alur+","+penokohan+","+akting+","+ nilai +")";     
           statement.executeUpdate(sql);
           JOptionPane.showMessageDialog(null, "input berhasil");
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Film sudah ada di database");
       }
    }
    public String[][] readMovie(){   
        try{
            int jmlData = 0;
            statement = (Statement) connection.koneksi.createStatement();
            String data[][] = new String[getBanyakData()][5];
            String query = "select * from movie"; 
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                data[jmlData][0] = resultSet.getString("judul");
                data[jmlData][1] = String.valueOf(resultSet.getDouble("alur"));                
                data[jmlData][2] = String.valueOf(resultSet.getDouble("penokohan"));
                data[jmlData][3] = String.valueOf(resultSet.getDouble("akting"));
                data[jmlData][4] = String.valueOf(resultSet.getDouble("nilai"));
                jmlData++;
            }
            return data;
       }
       catch(SQLException e){
           JOptionPane.showMessageDialog(null, "baca gagal");
           return null;
       }
    }
    public void updateMovie(String nama, double alur, double penokohan,double akting){
        try{
            int jmlData = 0;
            nilai = (float) ((alur + penokohan + akting)/3.0);
            statement = (Statement) connection.koneksi.createStatement();
            String query1 = "select * from movie where judul = '" + nama + "'";
            ResultSet res = statement.executeQuery(query1);
            
            while(res.next()){
                jmlData++;
            }
            if (jmlData == 0) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            }else{
                String query = "UPDATE movie SET alur='" + alur + "', penokohan='" + penokohan + "',akting = '" + akting + "',nilai='"+ nilai+"' WHERE judul= '" + nama + "'" ;  
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "update berhasil");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
    }
    public int getBanyakData(){
        int jmlData = 0;
        try{
            statement = connection.koneksi.createStatement();
            String query = "Select * from movie";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){ 
                jmlData++;
            }
            return jmlData;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }
    public void deleteMovie (String nama) {
        try{
            String query = "DELETE FROM movie WHERE judul = '"+nama+"'";
            statement = connection.koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
            
        }catch(SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
}
