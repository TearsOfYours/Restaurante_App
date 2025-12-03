package ies.sequeros.com.dam.pmdm.administrador.infraestructura.categorias;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class CategoriaDao implements IDao<Categoria> {
    private DataBaseConnection conn;
    private final String table_name = "CATEGORIA";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id=?";
    private final String findbyname = "select * from " + table_name + " where name=?";

    private final String deletebyid = "DELETE FROM " + table_name + " WHERE id=?";
    private final String insert = "INSERT INTO " + table_name + " (nombre, image_path) " +
            "VALUES (?, ?)";
    private final String update =
            "UPDATE " + table_name + " SET nombre = ? " +
                    "WHERE id = ?";
    public CategoriaDao() {
    }

    public DataBaseConnection getConn() {
        return this.conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public Categoria getById(final String id) {
        Categoria ct = null;// = new Categoria();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyid);
            pst.setString(1, id);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ct = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(CategoriaDao.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id=" + id + "]");
            return ct;
        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return ct;
    }

    public Categoria findByName(final String name) {
        Categoria ct = null;// = new Dependiente();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(findbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ct = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(CategoriaDao.class.getName());
            logger.info("Ejecutando SQL: " + findbyname + " | Parametros: [name=" + name + "]");

            return ct;
        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return ct;
    }
    @Override
    public List<Categoria> getAll() {
        final ArrayList<Categoria> scl = new ArrayList<>();
        Categoria tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = conn.getConnection().prepareStatement(selectall);
            } catch (final SQLException ex) {
                Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = registerToObject(rs);
                scl.add(tempo);
            }

            pst.close();
            Logger logger = Logger.getLogger(CategoriaDao.class.getName());
            logger.info("Ejecutando SQL: " + selectall+ " | Parametros: ");

        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return scl;
    }
    @Override
    public void update(final Categoria item) {

        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(update);
            pst.setString(1, item.getName());
            pst.setString(2, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(CategoriaDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: [1]=" + item.getName() +
                            ", [2]=" + item.getId() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    @Override
    public void delete(final Categoria item) {
        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(deletebyid);
            pst.setString(1, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(CategoriaDao.class.getName());
            logger.info("Ejecutando SQL: " + deletebyid + " | Parametros: [id=" + item.getId() + "]");

        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    @Override
    public void insert(final Categoria item) {

        final PreparedStatement pst;
        try {
            pst = conn.getConnection().prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getName());
            pst.setString(2, item.getImagePath());

            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(CategoriaDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: [1]=" + item.getId() +
                            ", [2]="+ item.getImagePath() +
                            ", [3]="+ item.getName() +
                            "]"
            );

        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    //pasar de registro a objeeto
    private Categoria registerToObject(final ResultSet r) {
        Categoria ct =null;

        try {
            ct=new Categoria(
                    r.getString("ID"),
                    r.getString("NOMBRE"),
                    r.getString("IMAGE_PATH")
                    //r.getBoolean("ENABLED")
            );
            return ct;
        } catch (final SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return ct;
    }
}