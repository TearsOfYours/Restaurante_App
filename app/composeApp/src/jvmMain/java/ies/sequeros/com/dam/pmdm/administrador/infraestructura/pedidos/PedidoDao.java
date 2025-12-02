package ies.sequeros.com.dam.pmdm.administrador.infraestructura.pedidos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class PedidoDao implements IDao<Pedido> {
    private DataBaseConnection conn;
    private final String table_name = "PEDIDO";

    public void setConn(DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pedido pedido) {
        String sql = "INSERT INTO PEDIDO (nombre, fecha, precio) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, pedido.getName());
            pst.setDate(2, Date.valueOf(pedido.getFecha()));
            pst.setDouble(3, pedido.getPrecio());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pedido pedido) {
        String sql = "UPDATE PEDIDO SET nombre = ?, fecha = ?, precio = ? WHERE id = ?";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setString(1, pedido.getName());
            pst.setDate(2, Date.valueOf(pedido.getFecha()));
            pst.setDouble(3, pedido.getPrecio());
            pst.setInt(4, Integer.parseInt(pedido.getId()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Pedido pedido) {
        String sql = "DELETE FROM PEDIDO WHERE id = ?";
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(pedido.getId()));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pedido getById(String id) {
        String sql = "SELECT * FROM PEDIDO WHERE id = ?";
        Pedido pedido = null;
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(id));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pedido = new Pedido(
                        rs.getString("nombre"),
                        String.valueOf(rs.getInt("id")),
                        rs.getDate("fecha").toString(),
                        rs.getDouble("precio"),
                        "PENDIENTE" // estado por defecto
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM PEDIDO";
        try (Statement st = conn.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getString("nombre"),
                        String.valueOf(rs.getInt("id")),
                        rs.getDate("fecha").toString(),
                        rs.getDouble("precio"),
                        "PENDIENTE"
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }


    public Pedido findByName(String name) {
        String sql = "SELECT * FROM PEDIDO WHERE nombre = ?";
        Pedido pedido = null;
        try (PreparedStatement pst = conn.getConnection().prepareStatement(sql)) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pedido = new Pedido(
                        rs.getString("nombre"),
                        String.valueOf(rs.getInt("id")),
                        rs.getDate("fecha").toString(),
                        rs.getDouble("precio"),
                        "PENDIENTE"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }
}