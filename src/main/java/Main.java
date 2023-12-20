import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        PlayerMinuteScraper.playerMinuteScraper();

//        //populate all teams with current data
//        HashMap<String, Team> teamsMap = parseTeamInfo();
//        //return the schedule
//        List<List<String>> schedule = ScheduleParser.returnScheule();
//        System.out.println("+++++++++++++++++++++++++++++++");
//        //calculate all data
//        for (int i = 0; i < 11; i++){
//            System.out.println(schedule.get(i).get(0) + " vs " + schedule.get(i).get(1) + " @ " + schedule.get(i).get(2));
//            //simply return two scores given two teams
//            List<String> results = simpleTeamProjection(teamsMap.get(schedule.get(i).get(0)), teamsMap.get(schedule.get(i).get(1)));
//            System.out.println("The total for the game will be " + results.get(4));
//
//            System.out.println("The winning team is " + results.get(0));
//            System.out.println("Their predicted score is " + results.get(1));
//            System.out.println("The losing team is " + results.get(2));
//            System.out.println("Their predicted score is " + results.get(3));
//
//            System.out.println("+++++++++++++++++++++++++++++++");
//        }

    }

    private static HashMap<String,Team> parseTeamInfo() {
        //list of all teams
        String[] allTeamsStrings = {
                "Atlanta","Boston","Brooklyn","Charlotte","Chicago",
                "Cleveland","Dallas","Denver","Detroit","Golden State",
                "Houston", "Indiana", "LA","Los Angeles", "Memphis",
                "Miami", "Milwaukee", "Minnesota", "New Orleans", "New York",
                "Oklahoma City", "Orlando", "Philadelphia", "Phoenix", "Portland",
                "Sacramento", "San Antonio", "Toronto", "Utah", "Washington"
        };

        //simultaneously call instantiate a new a team object for every string in the array above
        Team[] allTeamsArr = Arrays.stream(allTeamsStrings)
                .parallel()
                .map(Team::new)
                .toArray(Team[]::new);

        //add all the teams to a hashmap then return the hashmap
        HashMap<String, Team> teamsMap = new HashMap<>();
        for (int i = 0; i < allTeamsStrings.length; i++) {
            teamsMap.put(allTeamsStrings[i], allTeamsArr[i]);
        }
        return teamsMap;
    }

    private static List<String> simpleTeamProjection(Team _teamA, Team _teamB) {
        List<String> results = new ArrayList<>();
        //calculate the score for teamA
        double a = _teamA.getConferenceStats().get("yearPoints");
        double b = _teamA.getConferenceStats().get("last3Games");
        double c = _teamA.getConferenceStats().get("lastGame");
        double d = _teamA.getConferenceStats().get("homePoints");
        double e = _teamA.getConferenceStats().get("awayPoints");

        double f = _teamB.getConferenceStats().get("oppYearPoints");
        double g = _teamB.getConferenceStats().get("oppLast3Games");
        double h = _teamB.getConferenceStats().get("oppLastGame");
        double i = _teamB.getConferenceStats().get("oppHomePoints");
        double j = _teamB.getConferenceStats().get("oppAwayPoints");

        double avgA = (a + b + c + d + e + f + g + h + i + j) / 10;


        //then calculate the score for teamA
        a = _teamB.getConferenceStats().get("yearPoints");
        b = _teamB.getConferenceStats().get("last3Games");
        c = _teamB.getConferenceStats().get("lastGame");
        d = _teamB.getConferenceStats().get("homePoints");
        e = _teamB.getConferenceStats().get("awayPoints");

        f = _teamA.getConferenceStats().get("oppYearPoints");
        g = _teamA.getConferenceStats().get("oppLast3Games");
        h = _teamA.getConferenceStats().get("oppLastGame");
        i = _teamA.getConferenceStats().get("oppHomePoints");
        j = _teamA.getConferenceStats().get("oppAwayPoints");

        double avgB = (a + b + c + d + e + f + g + h + i + j) / 10;

        //calculate the total points scored
        double total = avgA + avgB;

        if (avgA >= avgB){
            //add teamA results first then teamB
            results.add(_teamA.getTeamName());
            results.add(String.valueOf(avgA));

            results.add(_teamB.getTeamName());
            results.add(String.valueOf(avgB));
        }
        else {
            //vice versa
            results.add(_teamB.getTeamName());
            results.add(String.valueOf(avgB));

            results.add(_teamA.getTeamName());
            results.add(String.valueOf(avgA));
        }

        results.add(String.valueOf(total));


        return results;
    }

    private static Player findPlayer(String playerName, HashMap<String,Team> teamMap){
        return teamMap.values().parallelStream()
                .map(team -> findPlayerInTeam(playerName, team))
                .filter(player -> player != null)
                .findFirst()
                .orElse(null);
    }
    private static Player findPlayerInTeam(String playerName, Team team){
        return team.getPlayers().parallelStream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElse(null);
    }
}