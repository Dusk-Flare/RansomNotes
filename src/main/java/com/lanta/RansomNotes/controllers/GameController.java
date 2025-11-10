package com.lanta.RansomNotes.controllers;

import com.lanta.RansomNotes.CardHandler;
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
    CardHandler GAME = new CardHandler();

    @GetMapping("/Login")
    public String startSession(HttpServletResponse response, @CookieValue(name = "Client", required = false) String client){
        if (client == null) {
            String PlayerID = String.valueOf(Counter);
            Cookie cookie = new Cookie("Client", PlayerID);
            GAME.AddPlayer(PlayerID, new PlayerDTO(null, null));
            cookie.setPath("/");
            response.addCookie(cookie);
            Counter++;
        }
        return "redirect:/GameLobby";
    }

    @GetMapping("/GameLobby")
    public String gameLobby(Model model, @CookieValue(name="Client", required = false) String client){
        if (client == null) return "redirect:/Login";
        List<String> players = GAME.GetPlayerList();
        List<String> playerText = new ArrayList<>(List.of("Lobby list:"));
        for(String player : players){
            playerText.add("Player " + player + " is in the Lobby");
        }
        model.addAttribute("ClientNames", playerText);
        return "LobbyScreen";
    }

    @GetMapping("/GameHome")
    public String showGameHome(Model model, @CookieValue(name="Client", required = false) String client) {
        if (client == null) return "redirect:/Login";
        PlayerDTO playerData = GAME.GetPlayer(client);
        model.addAttribute("ClientName", "You are client: " + client);
        model.addAttribute("ClientWords", "These are your words: <br>" + playerData.Words());
        model.addAttribute("ClientPrompts", "These are your prompts: <br>" + playerData.Prompt());
        return "GameHomePage";
    }

    @PostMapping("/GameHome")
    public String submitResponse(@RequestParam("response") String response,
                               @CookieValue(name="Client", required = false)  String client,
                               @RequestParam(name = "nextPrompt", required = false) String nPromt){
        if (client == null) return "redirect:/Login";
        String words = response.replaceAll("[^a-zA-Z0-9\\s]", "");
        List<String> wordsUsed = new ArrayList<>(Arrays.asList(words.split("\\s+")));
        GAME.RemoveWords(client, wordsUsed);

        return "GameHomePage";
    }
}
