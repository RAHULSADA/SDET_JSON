package com.rahul;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONObject;
import com.rahul.resources.JsonOperations;
import com.rahul.resources.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String dirPath = "C:\\Our Documents\\rahul - docs\\Code_Repositories\\SDET_JSON\\src\\main\\java\\json\\";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from business.BookSales;");
        SalesDetails salesDetails = new SalesDetails();
        ObjectMapper objectMapper = new ObjectMapper();
        //Next three  line are to create a consolidated JSON file
        Gson gson = new Gson();
        JsonArray jsonArray = new JsonArray();
        JSONObject jsonObject = new JSONObject();

        int count = 1;
        while (resultSet.next()) {
            salesDetails.setBookName(resultSet.getString("BookName"));
            salesDetails.setTopic(resultSet.getString("Topic"));
            salesDetails.setPurchasedDate(resultSet.getString("PurchasedDate"));
            salesDetails.setAmount(resultSet.getInt("Amount"));
            salesDetails.setLocation(resultSet.getString("Location"));
            //Write data to JSON file. This Class needs three Jackson maven dependencies
            objectMapper.writeValue(new File(dirPath+ "jsonfile_" + count++ + ".json"), salesDetails);
            //Next two  line are to create a consolidated JSON file
            String jsonString = gson.toJson(salesDetails);
            jsonArray.add(jsonString);
        }
        connection.close();
        //Create consolidated file
        jsonObject.put("data", jsonArray);
        String temp = StringEscapeUtils.unescapeJava(jsonObject.toString());
        temp = temp.replace("\"{", "{");
        temp = temp.replace("}\"", "}");
        FileWriter fileWriter = new FileWriter(dirPath + "consolidated_json.json");
        fileWriter.write(temp);
        fileWriter.close();

        JsonOperations jsonOperations = new JsonOperations();
        jsonOperations.jsonToJava();
    }
}
