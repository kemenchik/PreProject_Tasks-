package com.cko.sampleSpringProject.controller;

import com.cko.sampleSpringProject.dao.FilmDAO;
import com.cko.sampleSpringProject.model.Films;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/loginPage")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String showMainPage() {
        return "mainPage";
    }

    @GetMapping("/game")
    public String showMainGame() {
        return "KrestN";
    }

    @GetMapping("/test")
//    public String testing(@RequestParam String name, @RequestParam String mood) {
//        System.out.println(name);
//        System.out.println(mood);
//        return "test";
    public String testing() {
        return "test";
    }

    @Autowired
    FilmDAO filmDAO;

    @GetMapping("/testT")
    public ModelAndView showEditFilm(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Films films = filmDAO.findFilmById(id);
        modelAndView.addObject("film", films);
        modelAndView.setViewName("test");

        return modelAndView;
    }

    @GetMapping("/test2")
    public String testing2() {
        return "test";
    }
}