package com.rahul.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

    public static String properties(String prop) throws IOException {
        String filePath = "C:\\Our Documents\\rahul - docs\\Code_Repositories\\SDET_JSON\\src\\main\\resources\\prop.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
//        return properties.getProperty(prop);
        return "C:\\Our Documents\\rahul - docs\\Code_Repositories\\SDET_JSON\\src\\main\\java\\json\\jsonfile_4.json";
    }
}
