import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Team {
    //String for now but will become player objects later
    private List<Player> players = null;
    private String teamName = "";
    private Map<String, String> teamNames = null;
    private Map<String, Double> conferenceStats = null;

    public Team(String _teamName){
        teamName = _teamName;
        try {
            this.players = TeamParser.populatePlayerList(this);
            this.conferenceStats = TeamParser.populateConferenceStats(this);
            PointScraper.populatePoints(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getTeamName(){
        populateTeamNames();
        return teamNames.get(teamName);
    }
    private void populateTeamNames(){

        teamNames = new HashMap<>();

        teamNames.put("TBD", "TBD");
        teamNames.put("Atlanta", "ATL");
        teamNames.put("Boston", "BOS");
        teamNames.put("Brooklyn", "BKN");
        teamNames.put("Charlotte", "CHA");
        teamNames.put("Chicago", "CHI");
        teamNames.put("Cleveland", "CLE");
        teamNames.put("Dallas", "DAL");
        teamNames.put("Denver", "DEN");
        teamNames.put("Detroit", "DET");
        teamNames.put("Golden State", "GS");
        teamNames.put("Houston", "HOU");
        teamNames.put("Indiana", "IND");
        teamNames.put("LA", "LAC");
        teamNames.put("LA Clippers", "LAC");
        teamNames.put("Los Angeles", "LAL");
        teamNames.put("LA Lakers", "LAL");
        teamNames.put("Memphis", "MEM");
        teamNames.put("Miami", "MIA");
        teamNames.put("Milwaukee", "MIL");
        teamNames.put("Minnesota", "MIN");
        teamNames.put("New Orleans", "NO");
        teamNames.put("New York", "NY");
        teamNames.put("Oklahoma City", "OKC");
        teamNames.put("Okla City", "OKC");
        teamNames.put("Orlando", "ORL");
        teamNames.put("Philadelphia", "PHI");
        teamNames.put("Phoenix", "PHX");
        teamNames.put("Portland", "POR");
        teamNames.put("Sacramento", "SAC");
        teamNames.put("San Antonio", "SA");
        teamNames.put("Toronto", "TOR");
        teamNames.put("Utah", "UTAH");
        teamNames.put("Washington", "WSH");
        
    }

    public Map<String, String> getTeamNames() {
        populateTeamNames();
        return teamNames;
    }
    public List<Player> getPlayers(){
        return players;
    }
    public List<Player> getPlayersPointsOBJ(){
        List<Player> playersSorted = new ArrayList<>(players);
        Comparator<Player> pointsComparator = Comparator.comparing(Player::getPointsPerGame).reversed();
        Collections.sort(playersSorted, pointsComparator);
        return playersSorted;
    }
    public List<Player> getPlayersMinPlayedOBJ(){
        List<Player> playersSorted = new ArrayList<>(players);
        Comparator<Player> minPlayedComparator = Comparator.comparing(Player::getMinPlayed).reversed();
        Collections.sort(playersSorted, minPlayedComparator);
        return playersSorted;
    }

    public List<String> getPlayersPoints(){
        return getPlayersPointsOBJ()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
    public List<String> getPlayersMinPlayed(){
        return getPlayersMinPlayedOBJ()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Map<String, Double> getConferenceStats() {
        return conferenceStats;
    }
}
