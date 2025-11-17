package com.lanta.RansomNotes;

import java.util.*;

public class CardHandler {
    private HashMap<String, PlayerDTO> Players;
    private HashMap<String, PlayerDTO> Winners;
    private final String[] PROMPTS = new String[]{"Alert someone you're sinking in quicksand", "Announce that your local groundwater is polluted with E-coli", "Apologise to someone for accidentally dropping a nuke on their house", "Apologise to your best friend for fucking their partner", "As a teacher, tell your friend what it's like to teach soda", "Ask a barber to shave your balls", "Ask a child on the plane seat behind you to shut the fuck up", "Ask a friend to watch porn with you", "Ask a neighbour to borrow the 4ft tall blender from their kitchen", "Ask a stranger for drugs in a flirty way", "Ask someone to help you scratch your balls", "Ask the previous judge for marriage", "Ask your boss for a promotion in exchange for sexual favours", "Ask your mother to order you 52 chicken nuggets from McDonald's", "Ask your unc on his deathbed to include you in his will", "Break the news to the population that asteroid gonna cook them all", "Break up with someone because they pissed the bed", "Choose one superpower and explain your decision", "Choreograph a fight scene for an action movie", "Compliment a stripper on the quality of their performance", "Confess your darkest sins to a priest", "Convince a bank to give you a loan", "Convince a scared child to walk alone at night", "Create a Tinder profile for Rivulet", "Declare the terms of a swordfight duel", "Demand a refund for a faulty soft toy from a nervous customer service rep", "Describe a painting that soda might paint", "Describe an experiment your planning to conduct with your prize money", "Describe an insecurity about your appearance", "Describe how your body feels during public speaking", "Describe living through the year 2020", "Describe skin", "Describe something that scared you today", "Describe the Olympics, but for rats", "Describe the family tree", "Describe the most recent brainrot you learnt about", "Describe the progression of the 2 week Minecraft phase", "Describe the relationship between Lanta and Rose", "Describe the sequence of events that occurs when Rose's dog enters her room", "Describe what a hangover feels like", "Describe your favourite part of your morning routine", "Describe your favourite slugcat", "Describe your job as a twitch streamer during a speed dating event", "Describe your mental state", "Disinvite someone to your 6th birthday party", "Draft an instruction manual for toilet paper", "Exclaim loudly that your pants are filled with noodleflies", "Explain TikTok to your Grandfather", "Explain how babies are made", "Explain how condoms work", "Explain how the digestive system works", "Explain the American healthcare system", "Explain the female orgasm", "Explain the importance of foreplay", "Explain the joys of learning rainworld movement tech", "Explain to a child how giving birth works", "Explain to a child what rape is", "Explain to a child why racism is bad", "Explain to a cow you have to kill them to make Hamborigir", "Explain to a grocery store worker why you need your emotional support monkey", "Explain to the doctor that you have an STD because you tried to fuck raw chicken", "Explain what milking is", "Explain why you got fired from working on a cucumber farm", "Get someone who has been in a coma since 2020 up to speed", "Give a lecture about how Unfortunate turned out the way he is", "Give a presenation about drugs to a group of slugpups", "Give the most recent judge some advice about their next steps in life", "Give the most recent judge some relationship advice", "Go next door and tell your neighbours their music is too loud", "How did Jesus die?", "How do you feel about Vore?", "How do you keep food safe from bears?", "How do you rekindle your relationship with Lanta after scratching her balls", "How would your best friend describe your dating life?", "Introduce your mom to your 10 online dating candidates", "Make an excuse for trying to get away with going 150mph in a school zone", "Offer to apply sunscreen to every inch of a stranger's body", "Recommend acupuncture therapy to a sceptical friend", "Record the voicemail greeting for your mum's new phone", "Reveal to your best friend that you're trans", "Say something to soothe yourself while standing over the corpse of a child", "Send a text to a loved on saying you have been shanked", "Share your knowledge about the different types of weapons available for eradicating pests", "Summarize Rose in 5 words or less", "Summarize the Facebook privacy policy", "Tell a Hardware store employee youre looking for a BIG hammer", "Tell a child why they shouldn't talk to soda", "Tell a friend they have something stuck in their teeth", "Tell a mechanic why your car is 5x flatter than it used to be", "Tell a patient you accidentally amputated the wrong limb", "Tell a stranger they look sexy today", "Tell someone that you just pissed yourself", "Tell someone you clogged their toilet at a party", "Tell the interviewer why you haven't been employed for 14 years", "Tell your doctor your nipples simply wont stop leaking", "Tell your neighbour that you accidentally shot their cat 57 times", "Tell your parents you want to start an onlyfans", "Tell your spouse of 40 years you want to have a threesome", "The tagline for Rose's cooking show is:", "Tweet a political complaint", "Walk us through the masturbation process", "Welcome someone to Walmart", "What conspiracy theory does your estranged uncle always bring up at Chistmas dinner?", "What did Otter say to Soda on the night of their non-existant wedding", "What do you learn on day one of bus driving", "What happens in your favourite Disney movie?", "What happens when we die?", "What is the number one cause of divorce in Poland?", "What is your biggest regret?", "What was the best part about Primary school?", "What's that horrible smell?", "What's the biggest lie you have ever told?", "What's the hardest part about being a ghost?", "What's the importance of November?", "What's the most embarrassing thing that has ever happened to you?", "What's the most frustrating thing about living on earth?", "What's the most satisfying thing about eating GIRLL CHEEEESEEE", "What's the most significant moment in Dusk's life?", "What's the weirdest thing you have ever seen in a museum", "What's the worst part about living in the UK?", "Whisper something seductive to your crush in class", "Why is cocaine illegal?", "Write a chant protesting against contraception", "Write a compelling argument for why women are superior", "Write a craigslist advert for a sofa that smells like shit", "Write a description of a true crime podcast, where tima is the victim", "Write a devious fortune cookie", "Write a diary entry for the day you lost your virginity", "Write a discord announcement explaining why you have been offline for the past 9 years", "Write a eulogy for the death of Soda's 8942nd child", "Write a facebook post shamelessly promoting homophobia", "Write a flyer offering your massage services", "Write a happy birthday song for your left testicle", "Write a horrorscope for another player in the lobby", "Write a jingle about unfortunate complaining daily", "Write a letter of complaint to your most recent sexual partner", "Write a letter to parents explaining why the chemistry teacher was fired", "Write a letter to someone explaining why they didn't get into college", "Write a magic spell that turns someone into a soda can", "Write a mean telegram about Tima not being online enough", "Write a message to soda explaining you sent him the children by mistake, and you will be recalling them immediately", "Write a missing cat poster (Cat is ugly)", "Write a new bible verse", "Write a new motto for Brazil", "Write a personalised IOU card for someone who gave you a blowjob", "Write a presentation about your opinions on racism", "Write a review from the perspective of Epimethius", "Write a revoke notice for a batch of baby toys that combust", "Write a sign advertising you have children for sale", "Write a sign explaining your shop is closed due to an infestation of one angry Rose", "Write a song that teaches children to fear short orange cylindrical objects", "Write a theme song for a TV show about lobotomy", "Write a thesis statement for a PhD in brainrot", "Write a yelp review for your childhood", "Write an add for Fentanyl, made by an affection deprived 15 year old", "Write an advertisement for trendy adult diapers", "Write an application to work in a joke shop", "Write an argument postulating that Slugcats are better than humans", "Write an elaborate phrase you might say while stealing someone's kitchen appliances", "Write an email to your local council explaining why the street is flooded with baked beans", "Write an old-timey expression for an unattractive person", "Write instructions for how to use a potato masher", "Write reddit post that you got your dick stuck in a Soda can", "Write the eulogy for your boss who you FUCKING HATE", "You travel back in time to meet your 6 year old self. What to you say?", "write an inspirational quote Lanta is getting tattooed on her shoulder"};
    private final String[] WORDS = new String[]{"and", "to", "for"};
    private final List<String> ReadyPlayers = new ArrayList<>();
    private final List<String> VotersEndGame = new ArrayList<>();
    private final List<String> VotersStartGame = new ArrayList<>();
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

    public int GetEndVoteCount(){
        return this.VotersEndGame.size();
    }

    public List<String> GetEndVoters(){
        return this.VotersEndGame;
    }

    public int GetStartVoteCount(){
        return this.VotersStartGame.size();
    }

    public List<String> GetStartVoters(){
        return this.VotersStartGame;
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

    public void VoteStartGame(String playerID) {
        if(!this.VotersStartGame.contains(playerID)) this.VotersStartGame.add(playerID);
    }

    public void UnReadyPlayer(String playerID) {
        this.ReadyPlayers.remove(playerID);
    }

    public void UnVoteEndGame(String playerID) {
        this.VotersEndGame.remove(playerID);
    }

    public void UnVoteStartGame(String playerID) {
        this.VotersStartGame.remove(playerID);
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

    public void AttemptStartGame(){
        for(String player : Players.keySet()){
            if(!this.VotersStartGame.contains(player)) return;
        }
        StartGame();
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
