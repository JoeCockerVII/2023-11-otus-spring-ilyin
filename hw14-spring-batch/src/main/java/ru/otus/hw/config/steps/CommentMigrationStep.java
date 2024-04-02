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
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.models.mongo.CommentMongo;
import ru.otus.hw.services.ConversionService;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class CommentMigrationStep {

    private static final int CHUNK_SIZE = 5;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public MongoPagingItemReader<CommentMongo> commentReader(MongoTemplate mongoTemplate) {
        return new MongoPagingItemReaderBuilder<CommentMongo>()
                .name("commentReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(CommentMongo.class)
                .sorts(new HashMap<>())
                .build();
    }


    @Bean
    public JdbcBatchItemWriter<CommentDto> commentWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CommentDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO comments (comment_id, text, book_id) VALUES (:id, :text, :bookId)")
                .beanMapped()
                .build();
    }


    @Bean
    public ItemProcessor<CommentMongo, CommentDto> commentProcessor(ConversionService conversionService) {
        return conversionService::convert;
    }

    @Bean
    public Step commentStep(ItemReader<CommentMongo> commentMongoItemReader,
                                   JdbcBatchItemWriter<CommentDto> commentJdbcBatchItemWriter,
                                   ItemProcessor<CommentMongo, CommentDto> processor) {
        return new StepBuilder("commentStep", jobRepository)
                .<CommentMongo, CommentDto>chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(commentMongoItemReader)
                .processor(processor)
                .writer(commentJdbcBatchItemWriter)
                .build();
    }
}
