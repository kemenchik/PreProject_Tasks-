package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping ("/cars")
public class CarsController {


    @GetMapping
    public String getCars(@RequestParam(required = false, defaultValue = "5") int count, Model model) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cars.add(new Car("BMW", i, 4));
        }
        model.addAttribute("cars", cars);
        return "cars";
    }
}
