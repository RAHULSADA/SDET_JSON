import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from business.BookSales;");
        ObjectMapper objectMapper = new ObjectMapper();
        int count = 1;
        while (resultSet.next()) {
            SalesDetails salesDetails = new SalesDetails();
            salesDetails.setBookName(resultSet.getString("BookName"));
            salesDetails.setTopic(resultSet.getString("Topic"));
            salesDetails.setPurchasedDate(resultSet.getString("PurchasedDate"));
            salesDetails.setAmount(resultSet.getInt("Amount"));
            salesDetails.setLocation(resultSet.getString("Location"));
            objectMapper.writeValue(new File("C:\\Our Documents\\rahul - docs\\Code_Repositories\\SDET_JSON\\src\\main\\java\\json\\json_" + count++ + ".json"), salesDetails);
        }
        connection.close();

    }
}
