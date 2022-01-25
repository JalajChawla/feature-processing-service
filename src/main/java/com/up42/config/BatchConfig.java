package com.up42.config;

import com.up42.dto.SourceData;
import com.up42.entity.Features;
import com.up42.listener.JobMonitoringListener;
import com.up42.processor.FeatureItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.util.Properties;

/**
 * @author jalajchawla
 */
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobFactory;
    @Autowired
    private StepBuilderFactory stepFactory;


    @Value("${database.driver}")
    private String databaseDriver;
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUsername;
    @Value("${database.password}")
    private String databasePassword;

    //listener

    @Bean
    public JobExecutionListener createListener() {
        return new JobMonitoringListener();
    }

    //Processor

    @Bean
    public FeatureItemProcessor createProcessor() {
        return  new FeatureItemProcessor();
    }

    @Bean
    public JsonItemReader featureJsonItemReader() throws MalformedURLException {
        return new JsonItemReaderBuilder()
                .jsonObjectReader(new JacksonJsonObjectReader(SourceData.class))
                .resource(new UrlResource(
                        "https://storage.googleapis.com/interstellar-backend-challenge/source-data.json"))
                .name("featureJsonItemReader")
                .build();
    }

    //writer
    @Bean
    public ItemWriter writer() {
        JpaItemWriter writer = new JpaItemWriter();
        writer.setEntityManagerFactory(entityManagerFactory().getObject());

        return writer;
    }

    //step

    @Bean(name="step1")
    public Step createStep1() throws MalformedURLException {
        return   stepFactory.get("step1")
                .<Features, Features>chunk(1000)
                .reader(featureJsonItemReader())
                .writer(writer())
                .processor(createProcessor())
                .build();
    }

    @Bean(name="job1")
    public Job createJob1() throws MalformedURLException {
        return jobFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(createListener())
                .start(createStep1())
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setPackagesToScan("ch.javaee.springBootBatch");
        lef.setDataSource(dataSource());
        lef.setJpaProperties(new Properties());
        return lef;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }

}
