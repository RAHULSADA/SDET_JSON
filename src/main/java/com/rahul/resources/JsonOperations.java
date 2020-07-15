package com.rahul.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import com.rahul.SalesDetails;

public class JsonOperations {

    public void jsonToJava() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SalesDetails salesDetails = objectMapper.readValue(new File(Utilities.properties("path")), SalesDetails.class);
        System.out.println(salesDetails.getAmount());
    }
}
