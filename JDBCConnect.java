// Java Database connectivity
/**
 * 1. import ---> java.sql
 * 2. load and register the driver ---> com.mysql.jdbc.Driver
 * 3. Create connection ---> Connection
 * 4. Create a statement ---> Statement
 * 5. Execute the query
 * 6. Process the results
 * 7. Close
 */
// Step 1

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

//DAO - Data Access Object
public class JDBCConnect {
    public static void main(String args[]) throws Exception {

        String url = "jdbc:mysql://localhost:3306:/70sql";
        String username = "root";
        String password = "0valEdge!";

        // Step 2
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Loads the Driver class in com.mysql.cj.jdbc that is - /Users/alekyaathaluri/.m2/repository/com/mysql/mysql-connector-j/8.2.0/mysql-connector-j-8.2.0.jar!/com/mysql/cj/jdbc/Driver.class
        // At the time of loading the class static block in the class will be called and the driver register code is called in that block
        // Class.forName is equivalent below code
        // DriverManager.registerDriver(new Driver());
        //i.e.
        /*
        static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
         */
        // Class.forName("XYZ").newInstance()
        //This line of code creates the object of particular class XYZ

        // Step 3
        Connection con = DriverManager.getConnection(url, username, password);

        // Step 4
        Statement st = con.createStatement();

        // Step 5
        // for DQL executeQuery
        ResultSet rs = st.executeQuery("select * from dcr_model;");

        // Step 6
        while (rs.next())  // shifts the pointer and checks whether next row is there
        {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }

        //for DML executeupdate
       //DML- Inserting /Updating
        int count = st.executeUpdate("UPDATE dcr_model set name = 'alekya' where id =1005 ");
        System.out.println(count +" row(s) affected." );

        String modelName ="Model_1";
        int count1 = st.executeUpdate("UPDATE dcr_model set name = '"+modelName+"' where id =1005 ");
        System.out.println(count1 +" row(s) affected." );


        String query = "INSERT INTO dcr_model values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, 1050);
        pst.setString(2, "Model_NAME");
        pst.setString(3, "New");
        pst.setTimestamp(4, Timestamp.valueOf("2001-12-12 12:12:12"));
        pst.setTimestamp(5, Timestamp.valueOf("2001-12-12 12:12:12"));
        pst.setString(6, "admin");
        pst.setTimestamp(7, Timestamp.valueOf("2001-12-12 12:12:12"));
        pst.setString(8, "admin");
        pst.setTimestamp(9, Timestamp.valueOf("2001-12-12 12:12:12"));

        int count2 = pst.executeUpdate();
        System.out.println(count2 +" row(s) affected." );

        pst.close();
        //DDL- Changing the structure of database/tables


        // Step 7
        st.close();
        con.close();
    }
}
