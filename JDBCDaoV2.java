
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class JDBCDaoV2 {
    public static void main(String args[]) {
        //Fetch
        ModelDao  dao = new ModelDao();
        dao.connect();
        Model m = dao.getModel(1051);
        System.out.println(m.name);

        //INSERT
        Model m2 = new Model();
        m2.id= 1051;
        m2.name = "Model_ADD";
        dao.connect();
        dao.addModel(m2);
    }
}

class ModelDao{
    Connection con = null;

    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306:/70sql", "root", "0valEdge!" );
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Model getModel(int id){
        try{
            Model m = new Model();
            m.id= id;
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

    public void addModel(Model m) {
        String query = "INSERT INTO dcr_model values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst ;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, m.id);
            pst.setString(2, m.name);
            pst.setString(3, "New");
            pst.setTimestamp(4, Timestamp.valueOf("2001-12-12 12:12:12"));
            pst.setTimestamp(5, Timestamp.valueOf("2001-12-12 12:12:12"));
            pst.setString(6, "admin");
            pst.setTimestamp(7, Timestamp.valueOf("2001-12-12 12:12:12"));
            pst.setString(8, "admin");
            pst.setTimestamp(9, Timestamp.valueOf("2001-12-12 12:12:12"));
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

class Model{
    int id ;
    String name;
}
