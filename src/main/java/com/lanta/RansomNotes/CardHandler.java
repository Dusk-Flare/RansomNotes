package com.lanta.RansomNotes;

import java.util.*;

public class CardHandler {
    private HashMap<String, PlayerDTO> Players;
    private HashMap<String, PlayerDTO> Winners;
    private final String[] PROMPTS = new String[]{"Smile", "Happy", "Wonderful"};
    private final String[] WORDS = new String[]{"and", "to", "for"};
    private List<String> PromptsCache;
    Random random = new Random();

    public CardHandler() {
        this.Players = new HashMap<>();
    }

    public CardHandler(HashMap<String, PlayerDTO> players) {
        this.Players = players;
    }

    public void SetPlayers(HashMap<String, PlayerDTO> players) {
        this.Players = players;
    }

    public List<String> GetPlayerList(){
        Set<String> playerSet = this.Players.keySet();
        return new ArrayList<>(playerSet);
    }

    public PlayerDTO GetPlayer(String playerName){
        return this.Players.get(playerName);
    }

    public List<String> GetWinnersList(){
        return new ArrayList<>(this.Winners.keySet());
    }

    public HashMap<String, PlayerDTO> GetWinners(){
        return this.Winners;
    }

    public void AddPlayer(String playerID, PlayerDTO player) {
        this.Players.put(playerID, player);
    }

    public void RemovePlayer(String playerID) {
        this.Players.remove(playerID);
    }

    private void GeneratePrompts(){
        this.PromptsCache = new ArrayList<>(Arrays.asList(PROMPTS));
        Collections.shuffle(this.PromptsCache);
    }

    public void DistributePrompts(){
        for(Map.Entry<String, PlayerDTO> player : Players.entrySet()){
            PlayerDTO data = player.getValue();
            for(int i = 0; i <= 3; i++){
                if(this.PromptsCache.isEmpty()) GeneratePrompts();
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

    public void RemoveWords(String playerID, List<String> words){
        PlayerDTO data = Players.get(playerID);
        for(String word : words){
            data.Words().remove(word);
        }
        this.Players.put(playerID, data);
    }

    public void EndGame(){
        HashMap<String, PlayerDTO> winners = new HashMap<>();
        int winCount = 0;
        for(String player : Players.keySet()){
            PlayerDTO data = Players.get(player);
            if (data.Wins() == winCount) winners.put(player, data);
            if (data.Wins() > winCount){
                winCount = data.Wins();
                winners = new HashMap<>();
                winners.put(player, data);
            }
        }
        this.Winners = winners;
    }
}
