public class Player{
    private String name = "";
    private String team = "";
    private int games = 0;
    private int gamesStarted = 0;
    private double minPlayed = 0.0;
    private double fieldGoal = 0.0;
    private double fieldGoalAttempts = 0.0;
    private double fieldGoalPercentage = 0.0;
    private double threePointers = 0.0;
    private double threePointAttempts = 0.0;
    private double threePointPercentage = 0.0;
    private double twoPointers = 0.0;
    private double twoPointAttempts = 0.0;
    private double twoPointPercentage = 0.0;
    private double effectiveFieldGoalPercentage = 0.0;
    private double freeThrow = 0.0;
    private double freeThrowAttempts = 0.0;
    private double freeThrowPercentage = 0.0;
    private double offensiveReboundsPerGame = 0.0;
    private double defensiveReboundsPerGame = 0.0;
    private double totalReboundsPerGame = 0.0;
    private double assistsPerGame = 0.0;
    private double stealsPerGame = 0.0;
    private double blocksPerGame = 0.0;
    private double turnoversPerGame = 0.0;
    private double personalFoulsPerGame = 0.0;
    private double pointsPerGame = 0.0;



    private double pointsPerMin = 0.0;

    //constructor
    public Player(String name) {
        this.name = trueName(name);
    }

    public Player(String name, String team, String position, int games, double minPlayed, double fieldGoal, double fieldGoalPercentage, double threePointers, double threePointAttempts, double threePointPercentage, double twoPointers, double twoPointAttempts, double twoPointPercentage, double effectiveFieldGoalPercentage, double freeThrow, double freeThrowAttempts, double freeThrowPercentage, double offensiveReboundsPerGame, double defensiveReboundsPerGame, double totalReboundsPerGame, double assistsPerGame, double stealsPerGame, double blocksPerGame, double turnoversPerGame, double personalFoulsPerGame, double pointsPerGame) {
        this.name = trueName(name);
        this.team = team;
        this.games = games;
        this.minPlayed = minPlayed;
        this.fieldGoal = fieldGoal;
        this.fieldGoalPercentage = fieldGoalPercentage;
        this.threePointers = threePointers;
        this.threePointAttempts = threePointAttempts;
        this.threePointPercentage = threePointPercentage;
        this.twoPointers = twoPointers;
        this.twoPointAttempts = twoPointAttempts;
        this.twoPointPercentage = twoPointPercentage;
        this.effectiveFieldGoalPercentage = effectiveFieldGoalPercentage;
        this.freeThrow = freeThrow;
        this.freeThrowAttempts = freeThrowAttempts;
        this.freeThrowPercentage = freeThrowPercentage;
        this.offensiveReboundsPerGame = offensiveReboundsPerGame;
        this.defensiveReboundsPerGame = defensiveReboundsPerGame;
        this.totalReboundsPerGame = totalReboundsPerGame;
        this.assistsPerGame = assistsPerGame;
        this.stealsPerGame = stealsPerGame;
        this.blocksPerGame = blocksPerGame;
        this.turnoversPerGame = turnoversPerGame;
        this.personalFoulsPerGame = personalFoulsPerGame;
        this.pointsPerGame = pointsPerGame;
    }

    public String trueName(String name){
         return name.replaceAll("[0-9]","");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public void setGamesStarted(int gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public double getMinPlayed() {
        return minPlayed;
    }

    public void setMinPlayed(double minPlayed) {
        this.minPlayed = minPlayed;
    }

    public double getFieldGoal() {
        return fieldGoal;
    }

    public void setFieldGoal(double fieldGoal) {
        this.fieldGoal = fieldGoal;
    }

    public double getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public void setFieldGoalAttempts(double fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    public double getFieldGoalPercentage() {
        return fieldGoalPercentage;
    }

    public void setFieldGoalPercentage(double fieldGoalPercentage) {
        this.fieldGoalPercentage = fieldGoalPercentage;
    }

    public double getThreePointers() {
        return threePointers;
    }

    public void setThreePointers(double threePointers) {
        this.threePointers = threePointers;
    }

    public double getThreePointAttempts() {
        return threePointAttempts;
    }

    public void setThreePointAttempts(double threePointAttempts) {
        this.threePointAttempts = threePointAttempts;
    }

    public double getThreePointPercentage() {
        return threePointPercentage;
    }

    public void setThreePointPercentage(double threePointPercentage) {
        this.threePointPercentage = threePointPercentage;
    }

    public double getTwoPointers() {
        return twoPointers;
    }

    public void setTwoPointers(double twoPointers) {
        this.twoPointers = twoPointers;
    }

    public double getTwoPointAttempts() {
        return twoPointAttempts;
    }

    public void setTwoPointAttempts(double twoPointAttempts) {
        this.twoPointAttempts = twoPointAttempts;
    }

    public double getTwoPointPercentage() {
        return twoPointPercentage;
    }

    public void setTwoPointPercentage(double twoPointPercentage) {
        this.twoPointPercentage = twoPointPercentage;
    }

    public double getEffectiveFieldGoalPercentage() {
        return effectiveFieldGoalPercentage;
    }

    public void setEffectiveFieldGoalPercentage(double effectiveFieldGoalPercentage) {
        this.effectiveFieldGoalPercentage = effectiveFieldGoalPercentage;
    }

    public double getFreeThrow() {
        return freeThrow;
    }

    public void setFreeThrow(double freeThrow) {
        this.freeThrow = freeThrow;
    }

    public double getFreeThrowAttempts() {
        return freeThrowAttempts;
    }

    public void setFreeThrowAttempts(double freeThrowAttempts) {
        this.freeThrowAttempts = freeThrowAttempts;
    }

    public double getFreeThrowPercentage() {
        return freeThrowPercentage;
    }

    public void setFreeThrowPercentage(double freeThrowPercentage) {
        this.freeThrowPercentage = freeThrowPercentage;
    }

    public double getOffensiveReboundsPerGame() {
        return offensiveReboundsPerGame;
    }

    public void setOffensiveReboundsPerGame(double offensiveReboundsPerGame) {
        this.offensiveReboundsPerGame = offensiveReboundsPerGame;
    }

    public double getDefensiveReboundsPerGame() {
        return defensiveReboundsPerGame;
    }

    public void setDefensiveReboundsPerGame(double defensiveReboundsPerGame) {
        this.defensiveReboundsPerGame = defensiveReboundsPerGame;
    }

    public double getTotalReboundsPerGame() {
        return totalReboundsPerGame;
    }

    public void setTotalReboundsPerGame(double totalReboundsPerGame) {
        this.totalReboundsPerGame = totalReboundsPerGame;
    }

    public double getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(double assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public double getStealsPerGame() {
        return stealsPerGame;
    }

    public void setStealsPerGame(double stealsPerGame) {
        this.stealsPerGame = stealsPerGame;
    }

    public double getBlocksPerGame() {
        return blocksPerGame;
    }

    public void setBlocksPerGame(double blocksPerGame) {
        this.blocksPerGame = blocksPerGame;
    }

    public double getTurnoversPerGame() {
        return turnoversPerGame;
    }

    public void setTurnoversPerGame(double turnoversPerGame) {
        this.turnoversPerGame = turnoversPerGame;
    }

    public double getPersonalFoulsPerGame() {
        return personalFoulsPerGame;
    }

    public void setPersonalFoulsPerGame(double personalFoulsPerGame) {
        this.personalFoulsPerGame = personalFoulsPerGame;
    }

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(double pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }
    public double getPointsPerMin() {
        return pointsPerMin;
    }

    public void setPointsPerMin() {
        this.pointsPerMin = this.pointsPerGame / this.minPlayed;
    }
}


