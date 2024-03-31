package ru.otus.hw.config.steps;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoPagingItemReader;
import org.springframework.batch.item.data.builder.MongoPagingItemReaderBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.hw.models.h2.Genre;
import ru.otus.hw.models.mongo.GenreMongo;
import ru.otus.hw.services.ConversionService;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class GenreMigrationStep {

    private static final int CHUNK_SIZE = 5;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public MongoPagingItemReader<GenreMongo> genreReader(MongoTemplate mongoTemplate) {
        return new MongoPagingItemReaderBuilder<GenreMongo>()
                .name("genreReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(GenreMongo.class)
                .sorts(new HashMap<>())
                .build();
    }


    @Bean
    public JdbcBatchItemWriter<Genre> genreWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Genre>()
                .dataSource(dataSource)
                .sql("INSERT INTO genres (genre_id, name) VALUES (:id, :name)")
                .beanMapped()
                .build();
    }


    @Bean
    public ItemProcessor<GenreMongo, Genre> genreProcessor(ConversionService conversionService) {
        return conversionService::convert;
    }

    @Bean
    public Step genreStep(ItemReader<GenreMongo> genreMongoItemReader,
                                 JdbcBatchItemWriter<Genre>  genreJdbcBatchItemWriter,
                                 ItemProcessor<GenreMongo, Genre> processor) {
        return new StepBuilder("genreStep", jobRepository)
                .<GenreMongo, Genre>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(genreMongoItemReader)
                .processor(processor)
                .writer(genreJdbcBatchItemWriter)
                .build();
    }

}
