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
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.mongo.BookMongo;
import ru.otus.hw.services.ConversionService;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class BookMigrationStep {

    private static final int CHUNK_SIZE = 5;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public MongoPagingItemReader<BookMongo> bookReader(MongoTemplate mongoTemplate) {
        return new MongoPagingItemReaderBuilder<BookMongo>()
                .name("bookReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(BookMongo.class)
                .sorts(new HashMap<>())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<BookDto> bookWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BookDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO books (book_id, title, author_id, genre_id) VALUES (:id, :title, :authorId, :genreId)")
                .beanMapped()
                .build();
    }

    @Bean
    public ItemProcessor<BookMongo, BookDto> bookProcessor(ConversionService conversionService) {
        return conversionService::convert;
    }

    @Bean
    public Step bookStep(ItemReader<BookMongo> bookMongoItemReader,
                         JdbcBatchItemWriter<BookDto> bookJdbcBatchItemWriter,
                         ItemProcessor<BookMongo, BookDto> processor) {
        return new StepBuilder("bookStep", jobRepository)
                .<BookMongo, BookDto>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(bookMongoItemReader)
                .processor(processor)
                .writer(bookJdbcBatchItemWriter)
                .build();
    }
}