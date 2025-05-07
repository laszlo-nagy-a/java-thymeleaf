package com.nagylaszlo.thymeleaf.service;

import com.nagylaszlo.thymeleaf.model.Game;
import com.nagylaszlo.thymeleaf.repository.GameRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;


    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return gameRepository.findAll(pageable).getContent();
    }

    public Game getGame(Integer id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);
    }

    public void save(List<Game> game) {
        gameRepository.saveAll(game);
    }

    public int numberOfPages(int gamePerPage) {
        List<Game> games = gameRepository.findAll();
        int numberOfGames = games.size();
        int pageNumber = numberOfGames / gamePerPage;

        if(numberOfGames == 0) {
            return 1;
        }

        return numberOfGames % gamePerPage == 0 ? pageNumber : pageNumber + 1;
    }
}
