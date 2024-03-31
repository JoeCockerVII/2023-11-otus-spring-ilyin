package ru.otus.hw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

@SuppressWarnings("unused")
@Configuration
public class JobConfig {

    public static final String MONGO_TO_H2_MIGRATE_JOB_NAME = "migrateMongoToH2Job";

    private static final int CHUNK_SIZE = 5;

    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job migrateDbJob(Step authorStep, Step genreStep, Step bookStep, Step commentStep) {

        return new JobBuilder(MONGO_TO_H2_MIGRATE_JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(authorStep)
                .next(genreStep)
                .next(bookStep)
                .next(commentStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Start job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("End job");
                    }
                })
                .build();
    }

}