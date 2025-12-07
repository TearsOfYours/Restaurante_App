package ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos;


import ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes.DependienteDao;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente;
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes.DependienteDao;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDao implements IDao<Producto> {
    private DataBaseConnection conn;
    private final String table_name = "PRODUCTO";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id=?";
    private final String findbyname = "select * from " + table_name + " where nombre=?";

    private final String deletebyid = "delete from " + table_name + " where id=?";
    private final String insert = "INSERT INTO " + table_name + " (id, nombre, precio, idCategoria, image_path, descripcion, enabled) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String update =
            "UPDATE " + table_name + " SET nombre = ?, precio = ?, idCategoria = ?, image_path = ?, descripcion = ?, enabled = ? " +
                    "WHERE id = ?";
    public ProductoDao() {
    }

    public DataBaseConnection getConn() {
        return this.conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public Producto getById(final String id) {
        Producto pt = null;// = new Dependiente();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyid);
            pst.setString(1, id);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pt = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(ProductoDao.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id=" + id + "]");
            return pt;
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return pt;
    }

    public Producto findByName(final String name) {
        Producto pt = null;// = new Dependiente();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(findbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pt = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(ProductoDao.class.getName());
            logger.info("Ejecutando SQL: " + findbyname + " | Parametros: [name=" + name + "]");

            return pt;
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return pt;
    }
    @Override
    public List<Producto> getAll() {
        final ArrayList<Producto> scl = new ArrayList<>();
        Producto tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = conn.getConnection().prepareStatement(selectall);
            } catch (final SQLException ex) {
                Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = registerToObject(rs);
                scl.add(tempo);
            }

            pst.close();
            Logger logger = Logger.getLogger(ProductoDao.class.getName());
            logger.info("Ejecutando SQL: " + selectall+ " | Parametros: ");

        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return scl;
    }


    @Override
    public void update(final Producto item) {

        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(update);
            pst.setString(1, item.getName());
            pst.setDouble(2, item.getPrecio());
            pst.setString(3, item.getIdCategoria());
            pst.setString(4, item.getImagePath());
            pst.setString(5, item.getDescripcion());
            pst.setBoolean(6, item.getEnabled());
            pst.setString(7, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(ProductoDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: [1]=" + item.getName() +
                            ", [2]=" + item.getPrecio() +
                            ", [3]=" + item.getIdCategoria() +
                            ", [4]=" + item.getImagePath() +
                            ", [5]=" + item.getDescripcion() +
                            ", [6]=" + item.getEnabled() +
                            ", [7]=" + item.getId()
            );
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    @Override
    public void delete(final Producto item) {
        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(deletebyid);
            pst.setString(1, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(ProductoDao.class.getName());
            logger.info("Ejecutando SQL: " + deletebyid + " | Parametros: [id=" + item.getId() + "]");

        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void insert(final Producto item) {

        final PreparedStatement pst;
        try {
            pst = conn.getConnection().prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getId());
            pst.setString(2, item.getName());
            pst.setDouble(3, item.getPrecio());
            pst.setString(4, item.getIdCategoria());
            pst.setString(5, item.getImagePath());
            pst.setString(6, item.getDescripcion());
            pst.setBoolean(7, item.getEnabled());

            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(ProductoDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: [1]=" + item.getId() +
                            ", [2]=" + item.getName() +
                            ", [3]=" + item.getPrecio() +
                            ", [4]=" + item.getIdCategoria() +
                            ", [5]=" + item.getImagePath() +
                            ", [6]=" + item.getDescripcion() +
                            ", [7]=" + item.getEnabled()
            );

        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    //pasar de registro a objeeto

    private Producto registerToObject(final ResultSet r) {
        Producto pt =null;
        try {

            pt = new Producto(
                    r.getString("nombre"),
                    r.getString("idCategoria"),
                    r.getString("id"),
                    r.getDouble("precio"),
                    r.getString("image_path"),
                    r.getString("descripcion"),
                    r.getBoolean("enabled")
            );
            return pt;
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return pt;
    }


}