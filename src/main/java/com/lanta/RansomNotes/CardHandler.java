package com.lanta.RansomNotes;

import java.util.*;

public class CardHandler {
    private HashMap<String, PlayerDTO> Players;
    private HashMap<String, PlayerDTO> Winners;
    private final String[] PROMPTS = new String[]{"Smile", "Happy", "Wonderful"};
    private final String[] WORDS = new String[]{"and", "to", "for"};
    private final List<String> ReadyPlayers = new ArrayList<>();
    private final List<String> VotersEndGame = new ArrayList<>();
    private List<String> RoundCandidates = new ArrayList<>();
    private List<String> PromptsCache = new ArrayList<>();
    private String RoundMaster;
    private boolean Running;

    Random random = new Random();

    public CardHandler() {
        this.Players = new HashMap<>();
        this.Running = false;
    }

    public CardHandler(HashMap<String, PlayerDTO> players) {
        this.Players = players;
        this.Running = false;
    }

    public boolean IsRunning(){
        return this.Running;
    }

    public String GetRoundMaster(){
        return this.RoundMaster;
    }

    public int GetPlayerCount(){
        return this.Players.size();
    }

    public int GetEndVote(){
        return this.VotersEndGame.size();
    }

    public List<String> GetEndVoters(){
        return this.VotersEndGame;
    }

    public void SetPlayers(HashMap<String, PlayerDTO> players) {
        this.Players = players;
    }

    public void ReadyPlayer(String playerID) {
        if(!this.ReadyPlayers.contains(playerID)) this.ReadyPlayers.add(playerID);
    }

    public void VoteEndGame(String playerID) {
        if(!this.VotersEndGame.contains(playerID)) this.VotersEndGame.add(playerID);
    }

    public void UnReadyPlayer(String playerID) {
        this.ReadyPlayers.remove(playerID);
    }

    public void UnVoteEndGame(String playerID) {
        this.VotersEndGame.remove(playerID);
    }

    public void UnReadyPlayers() {
        this.ReadyPlayers.clear();
    }

    public List<String> GetPlayerNames(){
        List<String> players = new ArrayList<>();
        for(PlayerDTO playerData : this.Players.values()){
            players.add(playerData.Name());
        }
        return players;
    }

    public List<String> GetPlayerIDs(){
        return List.of(Players.keySet().toArray(new String[0]));
    }

    public PlayerDTO GetPlayer(String playerId){
        return this.Players.get(playerId);
    }

    public List<String> GetWinnersList(){
        List<String> winners = new ArrayList<>();
        for(PlayerDTO playerData : this.Winners.values()){
            winners.add(playerData.Name());
        }
        return winners;
    }

    public HashMap<String, PlayerDTO> GetWinners(){
        return this.Winners;
    }

    public void AddPlayer(String playerID, PlayerDTO playerData) {
        this.Players.put(playerID, playerData);
    }

    public void RemovePlayer(String playerID) {
        this.Players.remove(playerID);
    }

    private void GeneratePrompts(){
        this.PromptsCache = new ArrayList<>(Arrays.asList(PROMPTS));
        Collections.shuffle(this.PromptsCache);
    }

    private void GenerateCandidates(){
        this.RoundCandidates = new ArrayList<>(Players.keySet());
        Collections.shuffle(this.RoundCandidates);
    }

    public void SelectRoundMaster(){
        if (this.RoundCandidates.isEmpty()) GenerateCandidates();
        this.RoundMaster = this.RoundCandidates.getLast();
        this.RoundCandidates.remove(this.RoundMaster);
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

    public void RemovePrompt(String playerID, String prompt){
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

    public void StartGame(){
        if(this.Running) return;
        this.Running = true;
        SelectRoundMaster();
        DistributePrompts();
        DistributeWords();
    }

    public Boolean NextRound(){
        for(String player : Players.keySet()){
            if(!this.ReadyPlayers.contains(player)) return false;
        }
        UnReadyPlayers();
        DrawPrompt(this.RoundMaster);
        DrawWords();
        SelectRoundMaster();
        return true;
    }

    public void AttemptEndGame(){
        for(String player : Players.keySet()){
            if(!this.VotersEndGame.contains(player)) return;
        }
        EndGame();
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
        this.Running = false;
    }
}
