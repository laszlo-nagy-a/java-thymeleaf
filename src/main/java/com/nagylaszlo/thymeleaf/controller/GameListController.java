package com.nagylaszlo.thymeleaf.controller;

import com.nagylaszlo.thymeleaf.model.Game;
import com.nagylaszlo.thymeleaf.service.GameService;
import com.nagylaszlo.thymeleaf.util.GameFactory;
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

    @GetMapping("/games")
    public String getAllGames(@RequestParam(required = false) String page, Model model) {

        Integer pageNumber = null;
        int size = 10;
        List<Game> gamesToPage = new ArrayList<>();

        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }

        int numberOfPages = gameService.numberOfPages(size);

        if(pageNumber == null) {
            pageNumber = 1;
        }

        if(pageNumber <= 0) {
            pageNumber = 1;
        }

        if(pageNumber > numberOfPages) {
            pageNumber = numberOfPages;
        }

        gamesToPage = gameService.getGames(pageNumber, size);

        model.addAttribute("games", gamesToPage);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", pageNumber);

        return "gamelist";
    }

    @GetMapping("/games/{id}")
    public String getOneGame(@PathVariable("id") Integer id, Model model) {
        System.out.println(id);
        Game game = gameService.getGame(id);
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
