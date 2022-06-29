package com.example.batch.config;

import com.example.batch.model.AmazonProduct;
import com.example.batch.service.JsonFileReader;
import com.example.batch.service.ProductProcessor;
import com.example.batch.service.ProductWriter;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class PartitionConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job partitioningJob() throws Exception {
        return jobBuilderFactory.get("partitioningJob").incrementer(new RunIdIncrementer()).flow(masterStep()).end()
                .build();
    }

    @Bean
    public Step masterStep() throws Exception {
        return stepBuilderFactory.get("masterStep").partitioner(slaveStep()).partitioner("partition", partitioner())
                .gridSize(10).taskExecutor(new SimpleAsyncTaskExecutor()).build();
    }

    @Bean
    public Partitioner partitioner() throws Exception {
        MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        partitioner.setResources(resolver.getResources("file:/home/saul/aw06-saul-worseman/src/main/resources/data/SplitData/x*"));
        return partitioner;
    }

    @Bean
    public Step slaveStep() throws Exception {
        return stepBuilderFactory.get("slaveStep").<JsonNode, AmazonProduct>chunk(10)
                .reader(itemReader(null)).processor(itemProcessor()).writer(itemWriter()).build();
    }

    @Bean
    @StepScope
    public ItemReader<JsonNode> itemReader(@Value("#{stepExecutionContext['fileName']}") String file) {
        return new JsonFileReader(file);
    }

    @Bean
    public ItemProcessor<JsonNode, AmazonProduct> itemProcessor() {
        return new ProductProcessor();
    }

    @Bean
    public ItemWriter<AmazonProduct> itemWriter() {
        return new ProductWriter();
    }

}
