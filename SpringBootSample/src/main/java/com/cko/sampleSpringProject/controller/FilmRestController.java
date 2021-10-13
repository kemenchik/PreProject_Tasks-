package com.cko.sampleSpringProject.controller;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.model.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/film")
public class FilmRestController {

    @Autowired
    FilmDAO filmDAO;

@GetMapping("/get")
    public Films getFilmById(@RequestParam Long id) {
        return filmDAO.findFilmById(id);
    }

}