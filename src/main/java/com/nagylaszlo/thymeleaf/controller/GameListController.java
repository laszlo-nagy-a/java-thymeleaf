package com.nagylaszlo.thymeleaf.controller;

import com.nagylaszlo.thymeleaf.model.Game;
import com.nagylaszlo.thymeleaf.service.GameService;
import com.nagylaszlo.thymeleaf.util.GameFactory;
import com.nagylaszlo.thymeleaf.util.PagerValidator;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class GameListController {

    @Autowired
    private GameService gameService;
    @Autowired
    private PagerValidator pagerValidator;

    @GetMapping("/games")
    public String getAllGames(@RequestParam(required = false) String page, Model model) {

        int size = 10;
        int numberOfPages = gameService.numberOfPages(size);
        int currentPage = pagerValidator.getCurrentPage(page, size, numberOfPages);
        List<Game> gamesToPage = gameService.getGames(currentPage, size);


        model.addAttribute("games", gamesToPage);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", currentPage);

        return "gamelist";
    }

    @GetMapping("/games/{id}")
    public String getOneGame(@PathVariable("id") String id, Model model) {

        Integer gameId = null;

        try {
            gameId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "redirect:/games";
        }

        Game game = gameService.getGame(gameId);

        if(game == null) {
            return "redirect:/games";
        }

        model.addAttribute("game", game);
        return("game/detail");
    }

    @GetMapping("/games/add")
    public String addGamePage() {
        return("game/add");
    }

    @PostMapping("/games")
    public String postGameFactoryNumber(@RequestParam(required = false) Integer number, Model model) {
        if(number != null) {
            List<Game> games = GameFactory.fakeGames(number);
            gameService.save(games);
        }

        return "redirect:/games";
    }

}
