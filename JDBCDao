
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDao {
    public static void main(String args[]) {
        ModelDao  dao = new ModelDao();
        Model m = dao.getModel(1050);
        System.out.println(m.name);
    }
}

class ModelDao{
    public Model getModel(int id){
        try{
            Model m = new Model();
            m.id= id;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306:/70sql", "root", "0valEdge!" );
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select name from dcr_model where id = "+ id);
            rs.next();
            m.name = rs.getString(1);

            return m;

        } catch (Exception e) {
            System.out.println("Exception occurred while getting model"+ e);
        }
        return null;
    }
}

class Model{
    int id ;
    String name;
}
