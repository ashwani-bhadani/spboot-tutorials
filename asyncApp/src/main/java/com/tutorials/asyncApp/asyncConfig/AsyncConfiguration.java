package com.tutorial.asyncApp.asyncConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
    //creating custom thread pull task executor and not using default asyncTaskExecutor with default configs
    //max pool size, duration to live, cores to use etc. queue size can be configured
    @Bean("AsyncOrderExecutor")
    public Executor asyncTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setQueueCapacity(150);
        taskExecutor.setMaxPoolSize(4);
        taskExecutor.setThreadNamePrefix("Async-order-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
