package com.lanta.RansomNotes.controllers;

import com.lanta.RansomNotes.PlayerDTO;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class GameController {
    private int Counter = 0;
    private Dictionary<String, PlayerDTO> lobby;

    @GetMapping("/Login")
    public String startSession(HttpServletResponse response, @CookieValue(name = "Client", required = false) String client){
        if (client == null) {
            Cookie cookie = new Cookie("Client", String.valueOf(Counter));
            cookie.setPath("/");
            response.addCookie(cookie);
            Counter++;
        }
        return "redirect:/GameHome";
    }

    @GetMapping("/GameHome")
    public String showGameHome(Model model, @CookieValue(name="Client", defaultValue = "invalid") String client) {
        model.addAttribute("ClientName", "You are client " + client);
        model.addAttribute("ClientWords", "These are your words");
        model.addAttribute("ClientPrompts", "These are your prompts");
        return "GameHomePage";
    }

    @PostMapping("/GameHome")
    public void submitResponse(@RequestParam("response") String response,
                               @RequestParam(name = "nextPrompt", required = false) String nPromt){

    }
}
