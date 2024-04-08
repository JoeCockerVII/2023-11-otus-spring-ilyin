package ru.otus.hw.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.hw.domain.Burger;
import ru.otus.hw.domain.Cutlet;
import ru.otus.hw.services.BurgerService;

@Service
@Slf4j
public class BurgerServiceImpl implements BurgerService {

	@Override
	public Burger cook(Cutlet cutlet) {
		log.info("Cooking {} cutlet", cutlet.getName());
		delay();
		log.info("Cooking {} cutlet done", cutlet.getName());
		return new Burger(cutlet.getName());
	}

	private static void delay() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
