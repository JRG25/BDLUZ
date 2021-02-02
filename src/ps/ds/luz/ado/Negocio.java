package ps.ds.luz.ado;

import java.util.*;
import ps.ds.luz.modelo.Luz;
import java.sql.*;
import ps.ds.luz.util.*;

public class Negocio {

    public int adicion(Luz l) {
        int resp = 0;
        Connection conn = null;
        try {
            String sql = "insert into luz values (?,?,?,?)";
            conn = MySQLConexion.getConexion(); //abrir la conexion
            PreparedStatement st = conn.prepareStatement(sql); //prepara una instruccion para ejecutarla
            //relacionar cada ? con su atributo correspondiente:
            st.setInt(1, genera());
            st.setString(2, l.getCliente());
            st.setInt(3, l.getLecPre());
            st.setInt(4, l.getLecAnt());
            st.setInt(5, l.getTipo());
            resp = st.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return resp;
    }

    public int modifica(Luz l) {
        int resp = 0;
        Connection conn = null;
        try {
            String sql = "update luz set cliente =?, lecpre=?, lecant=?, tipo=? where medidor=? ";
            conn = MySQLConexion.getConexion(); //abrir la conexion
            PreparedStatement st = conn.prepareStatement(sql); //prepara una instruccion para ejecutarla
            //relacionar cada ? con su atributo correspondiente:
            st.setInt(5, l.getNumeroMed());
            st.setString(1, l.getCliente());
            st.setInt(2, l.getLecPre());
            st.setInt(3, l.getLecAnt());
            st.setInt(4, l.getTipo());
            resp = st.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return resp;
    }

    public int anula(int cod) {
        int resp = 0;
        Connection conn = null;
        try {
            String sql = "delete from luz where medidor=?";
            conn = MySQLConexion.getConexion(); //abrir la conexion
            PreparedStatement st = conn.prepareStatement(sql); //prepara una instruccion para ejecutarla
            //relacionar cada ? con su atributo correspondiente:
            st.setInt(1, cod);
            resp = st.executeUpdate();//si es correcto devuelve 1, sino -1.
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return resp;
    }

    public List<Luz> Listado() {
        List<Luz> lis = new ArrayList<Luz>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion(); //abrir la conexion
            String sql = "select * from luz";
            PreparedStatement st = conn.prepareStatement(sql); //prepara una instruccion para ejecutarla
            ResultSet rs = st.executeQuery(); //lleva a la memoria la ejecucuion de lo datos.
            //llenar el arraylist con la entidad
            while (rs.next()) {
                Luz luz = new Luz();
                luz.setNumeroMed(rs.getInt(1));
                luz.setCliente(rs.getString(2));
                luz.setLecPre(rs.getInt(3));
                luz.setLecAnt(rs.getInt(4));
                luz.setTipo(rs.getInt(5));
                lis.add(luz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return lis;
    }

    public Luz buscar(int cod) {
        Luz luz = null;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion(); //abrir la conexion
            String sql = "select cliente, lecpre, lecant, tipo from luz where medidor=?";
            PreparedStatement st = conn.prepareStatement(sql); //prepara una instruccion para ejecutarla
            st.setInt(1, cod);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la entidad
            if (rs.next()) {
                luz = new Luz();
                luz.setNumeroMed(cod);
                luz.setCliente(rs.getString(1));
                luz.setLecPre(rs.getInt(2));
                luz.setLecAnt(rs.getInt(3));
                luz.setTipo(rs.getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return luz;

    }

    //generara numero de medidor
    public int genera() {
        int numero = 0;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion(); //abrir la conexion
            String sql = "select ifnull (max(medidor),10000)+1 from luz";
            PreparedStatement st = conn.prepareStatement(sql); //prepara una instruccion para ejecutarla
            ResultSet rs = st.executeQuery(); //lleva a la memoria la ejecucuion de lo datos.

            if (rs.next()) {
                numero = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return numero;
    }
}
