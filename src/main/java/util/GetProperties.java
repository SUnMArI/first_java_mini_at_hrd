package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class GetProperties {
    public static Properties read(){
        Properties properties = new Properties();
        Path path = Path.of("src/main/java/util/connectionDriver.properties");
        try(BufferedReader bf = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            properties.load(bf);
            return properties;
        }catch (IOException ex){
            System.out.println("Failed to load the properties file!!!!!");
            return null;
        }
    }
}
