package ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos.ProductoDao;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
public class BBDDRepositorioProductosJava {
    private final DataBaseConnection db;
    private ProductoDao dao;
    public BBDDRepositorioProductosJava(DataBaseConnection connection) throws Exception {
        super();
        this.db = connection;
        dao= new ProductoDao();
        dao.setConn(this.db);

    }
    public void add(Producto item){
        this.dao.insert(item);
    }
    public boolean remove(Producto item){
        this.dao.delete(item);
        return true;
    }
    public boolean remove(String id){
        var item=this.dao.getById(id);
        if(item!=null){
            this.remove(item);
            return true;
        }
        return false;
    }
    public boolean  update(Producto item){
        this.dao.update(item);
        return true;
    }
    public List<Producto> getAll() {
        return this.dao.getAll();
    }
    public Producto findByName(String name){

        return null;
    }
    public Producto getById(String id){
        return this.dao.getById(id);

    }

    public List<Producto> getByCategoria(String idCategoria) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTO WHERE idCategoria = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, idCategoria);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(
                        rs.getString("nombre"),
                        rs.getString("idCategoria"),
                        rs.getString("id"),
                        rs.getDouble("precio"),
                        rs.getString("image_path"),
                        rs.getBoolean("enabled")
                );
                productos.add(p);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

}

