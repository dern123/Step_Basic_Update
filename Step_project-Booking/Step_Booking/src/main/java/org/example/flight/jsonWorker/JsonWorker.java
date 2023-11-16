package org.example.flight.jsonWorker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonWorker {
    public static <E> void loadDataToFile(List<E> data, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(filePath), data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <E> List<E> getDataFromFile (Class<E> dataType, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<E> dataFromFile = new ArrayList<>();
        try {
            dataFromFile = objectMapper.readValue(new File(filePath), typeFactory.constructCollectionType(ArrayList.class, dataType));
            return dataFromFile;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return dataFromFile;
    }
}
