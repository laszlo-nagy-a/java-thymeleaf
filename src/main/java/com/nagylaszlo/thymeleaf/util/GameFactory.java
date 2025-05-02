package com.nagylaszlo.thymeleaf.util;

import com.nagylaszlo.thymeleaf.model.Game;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    private static final List<Game> games = new ArrayList<>();

    public static List<Game> createGames(int numberOfGames) {
        for(int i = 0; i < numberOfGames; i++) {
            Game game = new Game();

            game.setId(i+1);
            game.setName("Game - " + (i + 1));
            game.setGenre("Genre - " + (i + 1));
            game.setScore(i+10);
            game.setPlatform("Platform - " + (i + 1));

            games.add(game);
        }

        return games;
    }

    public static List<Game> fakeGames(int NumberOfGames) {
        Faker faker = new Faker();

        for(int i = 0; i < NumberOfGames; i++) {
            Game game = new Game();
            game.setName(faker.videoGame().title());
            game.setGenre(faker.videoGame().genre());
            game.setPlatform(faker.videoGame().platform());
            game.setScore(faker.number().positive());

            games.add(game);
        }

        return games;
    }
}
