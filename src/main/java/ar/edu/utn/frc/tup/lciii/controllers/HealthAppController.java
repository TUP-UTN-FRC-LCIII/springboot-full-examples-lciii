package ar.edu.utn.frc.tup.lciii.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthAppController {

    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }
}
