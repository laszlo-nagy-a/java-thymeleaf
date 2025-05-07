package com.nagylaszlo.thymeleaf.util;

import com.nagylaszlo.thymeleaf.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagerValidator {

    @Autowired
    private GameService gameService;

    public int getCurrentPage(String number, int size, int numberOfPages) {

        Integer currentPage = null;

        if(number == null) {
            currentPage = 1;
        } else {
            try {
                currentPage = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        if(currentPage <= 0) {
            currentPage = 1;
        }

        if(currentPage > numberOfPages) {
            currentPage = numberOfPages;
        }

        return currentPage;
    }
}
