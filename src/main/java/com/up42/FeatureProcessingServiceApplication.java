package com.up42;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.dto.SourceData;
import com.up42.service.FeatureManagerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author jalajchawla
 */
@SpringBootApplication
public class FeatureProcessingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeatureProcessingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(FeatureManagerService featureManagerService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<SourceData>> typeReference = new TypeReference<List<SourceData>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/SourceData.json");
            try {
                List<SourceData> users = mapper.readValue(inputStream,typeReference);
                featureManagerService.save(users);
            } catch (IOException e){
                System.out.println("Unable to save features: " + e.getMessage());
            }
        };
    }


}
