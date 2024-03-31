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
import ru.otus.hw.models.h2.Author;
import ru.otus.hw.models.mongo.AuthorMongo;
import ru.otus.hw.services.ConversionService;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class AuthorMigrationStep {

    private static final int CHUNK_SIZE = 5;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public MongoPagingItemReader<AuthorMongo> authorReader(MongoTemplate mongoTemplate) {
        return new MongoPagingItemReaderBuilder<AuthorMongo>()
                .name("authorReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(AuthorMongo.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public ItemProcessor<AuthorMongo, Author> authorProcessor(ConversionService conversionService) {
        return conversionService::convert;
    }

    @Bean
    public JdbcBatchItemWriter<Author> authorWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Author>()
                .dataSource(dataSource)
                .sql("INSERT INTO authors (author_id, full_name) VALUES (:id, :fullName)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step authorStep(ItemReader<AuthorMongo> reader,
                                    JdbcBatchItemWriter<Author>  writer,
                                    ItemProcessor<AuthorMongo, Author> processor) {
        return new StepBuilder("authorStep", jobRepository)
                .<AuthorMongo, Author>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
