/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Constantes {
    private final String user = "root";
    private final String password = "root";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/bd_producto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected Connection miConexion;

    public Constantes() {
        conexion();
    }
    
    public void conexion() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Constantes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El driver a fallado o esta desactualzado");
        }
            
            
        try {
            miConexion = DriverManager.getConnection(url, user, password);
            System.out.println("Se ha conectado a la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(Constantes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se pudo conectar a la base de datos");
        }
    }

    public Connection getMiConexion() {
        return miConexion;
    }
}
