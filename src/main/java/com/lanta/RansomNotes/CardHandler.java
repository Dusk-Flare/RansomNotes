package com.lanta.RansomNotes;

import java.util.*;

public class CardHandler {
    private HashMap<String, PlayerDTO> Players;
    private HashMap<String, Integer> Wins;
    private final String[] PROMPTS = new String[]{};
    private final String[] WORDS = new String[]{};
    private String winner;
    private List<String> PromptsCache;
    Random random = new Random();


    public CardHandler() {}

    public CardHandler(HashMap<String, PlayerDTO> players) {
        this.Players = players;
    }

    public void SetPlayers(HashMap<String, PlayerDTO> players) {
        Players = players;
    }

    public void AddPlayer(String playerID, PlayerDTO player) {
        this.Players.put(playerID, player);
    }

    public void RemovePlayer(String playerID) {
        this.Players.remove(playerID);
    }

    private void GeneratePrompts(){
        this.PromptsCache = new ArrayList<>(Arrays.asList(PROMPTS));
    }

    public void DistributePrompts(){
        for(Map.Entry<String, PlayerDTO> player : Players.entrySet()){
            Collections.shuffle(this.PromptsCache);
            PlayerDTO data = player.getValue();
            for(int i = 0; i <= 3; i++){
                String prompt = this.PromptsCache.getLast();
                data.Prompt().add(prompt);
                PromptsCache.remove(prompt);
            }
            player.setValue(data);
        }
    }

    public void DistributeWords(){
        for(Map.Entry<String, PlayerDTO> player : Players.entrySet()) {
            PlayerDTO data = player.getValue();
            for(int i = 0; i < 125; i++){
                String word = this.WORDS[random.nextInt(this.WORDS.length)];
                data.Words().add(word);
            }
            player.setValue(data);
        }
    }

    public void DrawPrompt(String playerID){
        if (this.PromptsCache.isEmpty()) GeneratePrompts();
        Collections.shuffle(this.PromptsCache);
        PlayerDTO data = Players.get(playerID);
        String prompt = this.PromptsCache.getLast();
        data.Prompt().add(prompt);
        PromptsCache.remove(prompt);
    }

    public void DrawWords(){
        for(Map.Entry<String, PlayerDTO> player : Players.entrySet()) {
            PlayerDTO data = player.getValue();
            for(int i = 0; i < 10; i++){
                String word = this.WORDS[random.nextInt(this.WORDS.length)];
                data.Words().add(word);
            }
            player.setValue(data);
        }
    }

    private void RemovePrompt(String playerID, String prompt){
        PlayerDTO data = Players.get(playerID);
        data.Prompt().remove(prompt);
        this.Players.put(playerID, data);
    }

    private void RemoveWords(String playerID, List<String> words){
        PlayerDTO data = Players.get(playerID);
        for(String word : words){
            data.Words().remove(word);
        }
        this.Players.put(playerID, data);
    }

    private String EndGame(){
        String winner = "Draw";
        int wincount = -1;
        for(Map.Entry<String, Integer> WinCandidate : this.Wins.entrySet()){
            if(WinCandidate.getValue() > wincount){
                winner = WinCandidate.getKey();
                wincount = WinCandidate.getValue();
            }
        }
        return winner;
    }
}
