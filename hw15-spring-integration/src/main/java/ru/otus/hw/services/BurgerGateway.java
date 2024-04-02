package ru.otus.hw.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw.domain.Burger;
import ru.otus.hw.domain.Cutlet;

import java.util.Collection;

@MessagingGateway
public interface BurgerGateway {

	@Gateway(requestChannel = "cutletChannel", replyChannel = "burgerChannel")
	Collection<Burger> process(Collection<Cutlet> cutlets);

}