package ru.otus.hw.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.hw.domain.Burger;
import ru.otus.hw.domain.Cutlet;
import ru.otus.hw.services.BurgerGateway;
import ru.otus.hw.services.CutletService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CutletServiceImpl implements CutletService {

    private static final String[] MENU = {"Beef", "Turkey", "Chicken", "Duck", "Pork", "Horse", "Mutton", "Rabbit",
            "Venison", "Shrimps", "Trout", "Salmon"
    };

    private final BurgerGateway burgerGateway;

    public CutletServiceImpl(BurgerGateway burgerGateway) {
        this.burgerGateway = burgerGateway;
    }

    @Override
    public void startGenerateCutletLoop() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            pool.execute(() -> {
                Collection<Cutlet> cutlets = generateCutlets();
                log.info("{}, New cutlets: {}", num,
                        cutlets.stream().map(Cutlet::getName)
                                .collect(Collectors.joining(",")));

                Collection<Burger> burgers = burgerGateway.process(cutlets);
                log.info("{}, Ready Burgers: {}", num, burgers.stream()
                        .map(Burger::getName)
                        .collect(Collectors.joining(",")));
            });
            delay();
        }
    }


    private static Cutlet generateCutlet() {
        return new Cutlet(MENU[RandomUtils.nextInt(0, MENU.length)]);
    }

    private static Collection<Cutlet> generateCutlets() {
        List<Cutlet> cutlets = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            cutlets.add(generateCutlet());
        }
        return cutlets;
    }

    private void delay() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}