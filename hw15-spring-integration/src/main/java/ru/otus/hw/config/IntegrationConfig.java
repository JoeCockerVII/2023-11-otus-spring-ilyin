package ru.otus.hw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

import ru.otus.hw.domain.Burger;
import ru.otus.hw.services.BurgerService;

@Configuration
public class IntegrationConfig {

	@Bean
	public MessageChannelSpec<?, ?> cutletChannel() {
		return MessageChannels.queue(10);
	}

	@Bean
	public MessageChannelSpec<?, ?> burgerChannel() {
		return MessageChannels.publishSubscribe();
	}

	
	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerSpec poller() {
		return Pollers.fixedRate(100).maxMessagesPerPoll(2);
	}

	@Bean
	public IntegrationFlow burgerFlow(BurgerService burgerService) {
		return IntegrationFlow.from(cutletChannel())
				.split()
				.handle(burgerService, "cook")
				.<Burger, Burger>transform(f -> new Burger(f.getName().toUpperCase()))
				.aggregate()
				.channel(burgerChannel())
				.get();
	}
}