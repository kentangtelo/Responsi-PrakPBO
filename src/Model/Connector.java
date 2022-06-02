/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author TUF
 */
public class Connector {
    String DBurl = "jdbc:mysql://localhost/movie_db";
    String DBusername = "root";
    String DBpassword = "";
    public Connection koneksi;
    Statement statement;
    public Connector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("koneksi gagal");
        }
    }
}
