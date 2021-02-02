package ps.ds.luz.util;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class MySQLConexion {

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/bdluz";
            String usr = "root";
            String psw = "";
            con = DriverManager.getConnection(url, usr, psw);
            System.out.println("conexion ok");

        } catch (ClassNotFoundException ex) {
            System.out.println("no hay driver");
        } catch (SQLException ex) {
            System.out.println("error con la BD");
        }
        return con;
    }

}
