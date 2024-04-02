package ru.otus.hw.services;

import ru.otus.hw.domain.Burger;
import ru.otus.hw.domain.Cutlet;

public interface BurgerService {

    Burger cook (Cutlet cutlet);

}