package ies.sequeros.com.dam.pmdm.administrador.infraestructura.categorias;
import ies.sequeros.com.dam.pmdm.administrador.infraestructura.categorias.CategoriaDao;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria;
import java.sql.SQLException;
import java.util.List;
public class BBDDRepositorioCategoriasJava {
    private final DataBaseConnection db;
    private CategoriaDao dao;
    public BBDDRepositorioCategoriasJava(DataBaseConnection connection) throws Exception {
        super();
        this.db = connection;
        dao= new CategoriaDao();
        dao.setConn(this.db);

    }
    public void add(Categoria item){
        this.dao.insert(item);
    }
    public boolean remove(Categoria item){
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
    public boolean  update(Categoria item){
        this.dao.update(item);
        return true;
    }
    public List<Categoria> getAll() {
        return this.dao.getAll();
    }
    public Categoria findByName(String name){

        return null;
    }
    public Categoria  getById(String id){
        return this.dao.getById(id);

    }

    public List<Categoria> findByIds(List<String> ids){
        return null;
    }
    public void close(){
        try {
            this.db.close();
            //no hace caso de esta excepción
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



