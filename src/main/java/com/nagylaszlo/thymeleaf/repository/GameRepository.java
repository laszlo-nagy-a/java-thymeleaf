package com.nagylaszlo.thymeleaf.repository;

import com.nagylaszlo.thymeleaf.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends JpaRepository<Game, Integer>, PagingAndSortingRepository<Game, Integer> {
}
