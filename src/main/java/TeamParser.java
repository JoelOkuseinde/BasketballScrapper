import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.*;

public class TeamParser {

    public static Map populateConferenceStats(Team _teamName) throws IOException {

        WebClient client = new WebClient();

        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);

        //try going from the team page to a player page
        HtmlPage standingsPage = client.getPage("https://www.espn.co.uk/nba/standings");
        HtmlTable eastTeamNameTable = (HtmlTable) standingsPage.getByXPath("//table").get(0);
        HtmlTable eastTeamDataTable = (HtmlTable) standingsPage.getByXPath("//table").get(1);
        HtmlTable westTeamNameTable = (HtmlTable) standingsPage.getByXPath("//table").get(2);
        HtmlTable westTeamDataTable = (HtmlTable) standingsPage.getByXPath("//table").get(3);


        //create a list of teams names + populate them
        List<String> eastTeams = new ArrayList<>();
        List<String> westTeams = new ArrayList<>();


        for (int i = 1; i <= 15; i++) {
            eastTeams.add(eastTeamNameTable.getRows().get(i).getTextContent());
            westTeams.add(westTeamNameTable.getRows().get(i).getTextContent());
        }
        //everything here can be its own method in its own scraper file, then the teamPopulate method can use that method
        //the method can be called in the main file once rather than called 30 times

        int standingIndex = 1;
        boolean foundEast = false;
        boolean foundWest = false;

        for(int i = 0; i < eastTeams.size(); i++){
            //check east teams
            String check = eastTeams.get(i);
            int index = check.indexOf(_teamName.getTeamName());

            if (index != -1) {
                foundEast = true;
                break;
            }

            //check west teams
            check = westTeams.get(i);
            index = check.indexOf(_teamName.getTeamName());

            if (index != -1) {
                foundWest = true;
                break;
            }

            standingIndex++;
        }
        if(foundEast){
            //Go through the entire row an update the Team class accordingly
            HtmlTableRow row = eastTeamDataTable.getRow(standingIndex);
            return updateContent(row);
        } else if (foundWest) {
            HtmlTableRow row = westTeamDataTable.getRow(standingIndex);
            return updateContent(row);
        } else {
            System.out.println("something went wrong along the way");
            System.out.println("check this team: "+_teamName.getTeamName());
        }
        return null;
    }

    private static Map updateContent(HtmlTableRow row) {
        //given the row of data go through every cell and put the data in the hashMap
        LinkedHashMap<String, Double> conferenceStats = new LinkedHashMap<>();
        int index = 0;
        for (HtmlTableCell cell : row.getCells()) {
            //go through each cell and update the player object
            switch (index) {
                case 0:
                    conferenceStats.put("W", Double.valueOf(cell.getTextContent()));
                    break;
                case 1:
                    conferenceStats.put("L", Double.valueOf(cell.getTextContent()));
                    break;
                case 2:
                    conferenceStats.put("PCT", Double.valueOf(cell.getTextContent()));
                    break;
                case 8:
                    conferenceStats.put("PPG", Double.valueOf(cell.getTextContent()));
                    break;
                case 9:
                    conferenceStats.put("OPP-PPG", Double.valueOf(cell.getTextContent()));
                    break;
                case 10:
                    conferenceStats.put("DIFF", Double.valueOf(cell.getTextContent()));
                    break;

            }
            index++;
        }
        return conferenceStats;
    }
    public static List<Player> populatePlayerList(Team _teamName) throws IOException {
        WebClient client = new WebClient();

        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);

        HtmlPage teamPage = client.getPage("https://www.espn.co.uk/nba/team/_/name/" + _teamName.getTeamName().toLowerCase());
        HtmlPage teamPlayerPage = client.getPage("https://www.espn.co.uk/nba/team/roster/_/name/" + _teamName.getTeamName().toLowerCase());

        return parsePlayers(teamPlayerPage);
    }

    private static List<Player> parsePlayers(HtmlPage teamPlayerPage) throws IOException {
        List<Player> playerList = new ArrayList<>();
        HtmlTable table = teamPlayerPage.getFirstByXPath("//table");

        if (table == null){
            System.out.println(teamPlayerPage.getUrl());
            return null;
        }

        List<HtmlTableRow> allPlayers = table.getRows();
        if (allPlayers.get(1).getCell(1).getTextContent().equalsIgnoreCase("name")){
            allPlayers.remove(allPlayers.get(1));
        }

        //fix the parallel processing of this
        /*
        // Use parallelStream to process elements concurrently
        allPlayers.parallelStream().forEach(element -> {
            // Call your method for each element in the list
            try {
                Player player = createPlayer(element);
                playerList.add(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        */

        for (HtmlTableRow row : table.getRows()){
            if(row.getCell(1).getTextContent().equalsIgnoreCase("name")){
                continue;
            }

            playerList.add(createPlayer(row));
        }

        return playerList;
    }

    private static Player createPlayer(HtmlTableRow row) throws IOException {
        //create a player object then add to the playerList
        String name = row.getCell(1).getTextContent();
        Player player = new Player(name);
        // visit the player's stats page then use the data to populate the player object
        HtmlAnchor playerLink = row.getCell(1).getFirstByXPath(".//a");
        if (playerLink == null){
            System.out.println("something is wrong");
            System.out.println(name);
            return player;
        }
        HtmlPage playerPage = playerLink.click();

        String targetClassName = "Card PlayerStats";
        String xpathExpression = "//section[contains(@class, '" + targetClassName + "')]";
        HtmlSection statsSection = (HtmlSection) playerPage.getFirstByXPath(xpathExpression);
        if (statsSection == null){
//            System.out.println("the stats for this player couldnt be retrieved");
//            System.out.println(playerPage.getUrl());
            return player;
        }
        HtmlAnchor statsPageLink = statsSection.getFirstByXPath(".//a");
        HtmlPage statsPage = statsPageLink.click();

        HtmlTable statsTable = (HtmlTable) statsPage.getByXPath("//table").get(1);
        //then need to return the second bottom row
        int rowOfInterest = statsTable.getRows().size() -2;
        HtmlTableRow currentYearRow = statsTable.getRow(rowOfInterest);
        int index = 0;
        for (HtmlTableCell cell : currentYearRow.getCells()){
            //go through each cell and update the player object
            switch (index){
                case 0:
                    player.setGames(Integer.parseInt(cell.getTextContent()));
                    break;
                case 1:
                    player.setGamesStarted(Integer.parseInt(cell.getTextContent()));
                    break;
                case 2:
                    player.setMinPlayed(Double.parseDouble(cell.getTextContent()));
                    break;
                case 3:
                    char StringSplitter = (char) cell.getTextContent().indexOf('-');
                    player.setFieldGoal(Double.parseDouble(cell.getTextContent().substring(0,StringSplitter)));
                    player.setFieldGoalAttempts(Double.parseDouble(cell.getTextContent().substring(StringSplitter+1)));
                    break;
                case 4:
                    player.setFieldGoalPercentage(Double.parseDouble(cell.getTextContent()));
                    break;
                case 5:
                    char StringSplitter2 = (char) cell.getTextContent().indexOf('-');
                    player.setThreePointers(Double.parseDouble(cell.getTextContent().substring(0,StringSplitter2)));
                    player.setThreePointAttempts(Double.parseDouble(cell.getTextContent().substring(StringSplitter2+1)));
                    break;
                case 6:
                    player.setThreePointPercentage(Double.parseDouble(cell.getTextContent()));
                    break;
                case 7:
                    char StringSplitter3 = (char) cell.getTextContent().indexOf('-');
                    player.setFreeThrow(Double.parseDouble(cell.getTextContent().substring(0,StringSplitter3)));
                    player.setFreeThrowAttempts(Double.parseDouble(cell.getTextContent().substring(StringSplitter3+1)));
                    break;
                case 8:
                    player.setFreeThrowPercentage(Double.parseDouble(cell.getTextContent()));
                    break;
                case 9:
                    player.setOffensiveReboundsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 10:
                    player.setDefensiveReboundsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 11:
                    player.setTotalReboundsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 12:
                    player.setAssistsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 13:
                    player.setBlocksPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 14:
                    player.setStealsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 15:
                    player.setPersonalFoulsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 16:
                    player.setTurnoversPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
                case 17:
                    player.setPointsPerGame(Double.parseDouble(cell.getTextContent()));
                    break;
            }
            index++;
        }
        player.setPointsPerMin();
        return player;
    }


}
