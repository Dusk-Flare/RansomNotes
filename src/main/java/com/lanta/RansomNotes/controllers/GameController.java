package com.lanta.RansomNotes.controllers;

import com.lanta.RansomNotes.CardHandler;
import com.lanta.RansomNotes.PlayerDTO;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class GameController {
    private int Counter = 0;
    CardHandler GAME = new CardHandler();

    @GetMapping("/Login")
    public String startSession(HttpServletResponse response, @CookieValue(name = "Client", required = false) String client){
        if (client == null) {
            String PlayerID = String.valueOf(Counter);
            Cookie cookie = new Cookie("Client", PlayerID);
            GAME.AddPlayer(PlayerID, new PlayerDTO("Random Name", 0, new ArrayList<>(), new ArrayList<>()));
            cookie.setPath("/");
            response.addCookie(cookie);
            Counter++;
        }
        return "redirect:/GameLobby";
    }

    @GetMapping("/LogOff")
    public String logout(HttpServletResponse response, @CookieValue(name = "Client", required = false) String client){
        if(client != null){
            if(GAME.GetPlayerIDs().contains(client)){
                GAME.RemovePlayer(client);
            }
            Cookie cookie = new Cookie("Client", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "LogOff";
    }

    @GetMapping("/GameLobby")
    public String gameLobby(Model model, @CookieValue(name="Client", required = false) String client){
        if (client == null || GAME == null) return "redirect:/Login";
        List<String> players = GAME.GetPlayerNames();
        List<String> playerText = new ArrayList<>(List.of("Lobby list:"));
        for (String player : players) {
            playerText.add("Player " + player + " is in the Lobby");
        }
        model.addAttribute("ClientNames", playerText);
        return "LobbyScreen";
    }

    @GetMapping("/GameHome")
    public String showGameHome(Model model, @CookieValue(name="Client", required = false) String client) {
        if (client == null) return "redirect:/Login";
        PlayerDTO playerData = GAME.GetPlayer(client);
        model.addAttribute("ClientName", "Your name is: <br>" + playerData.Name());
        model.addAttribute("ClientWords", "These are your words: <br>" + playerData.Words());
        model.addAttribute("ClientPrompts", "These are your prompts: <br>" + playerData.Prompt());
        return "GameHomePage";
    }

    @PostMapping("/GameHome")
    public String submitResponse(@RequestParam("response") String response,
                               @CookieValue(name="Client", required = false)  String client,
                               @RequestParam(name = "nextPrompt", required = false) String nextPrompt){
        if (client == null) return "redirect:/Login";
        String words = response.replaceAll("[^a-zA-Z0-9\\s]", "");
        List<String> wordsUsed = new ArrayList<>(Arrays.asList(words.split("\\s+")));
        if(nextPrompt != null) GAME.RemovePrompt(client, nextPrompt);
        GAME.RemoveWords(client, wordsUsed);
        return "GameHomePage";
    }

    @GetMapping("/isStarted")
    public ResponseEntity<Boolean> hasGameStarted(){
        return ResponseEntity.ok(GAME.IsRunning());
    }

    @GetMapping("/start")
    public void startGame(){
        GAME.StartGame();
    }
}
