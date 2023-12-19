import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;

public class PointScraper {
    public static void populatePoints(Team _teamName) throws IOException {

        WebClient client = new WebClient();

        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);

        HtmlPage pointsPage = client.getPage("https://www.teamrankings.com/nba/stat/points-per-game");
        HtmlPage oppPointsPage = client.getPage("https://www.teamrankings.com/nba/stat/opponent-points-per-game");

        HtmlTable pointsTable = pointsPage.getFirstByXPath("//table");
        HtmlTable oppPointsTable = oppPointsPage.getFirstByXPath("//table");


        for (int i=1; i < pointsTable.getRows().size(); i++){
            if(correctRow(_teamName, pointsTable.getRows().get(i))){
                populatePoints(_teamName, pointsTable.getRows().get(i));
                break;
            }
        }

        for (int i=1; i < oppPointsTable.getRows().size(); i++){
            if(correctRow(_teamName, oppPointsTable.getRows().get(i))){
                populateOppPoints(_teamName, oppPointsTable.getRows().get(i));
            }
        }

    }

    private static void populateOppPoints(Team teamName, HtmlTableRow row) {
        int index = 0;
        for (HtmlTableCell cell : row.getCells()) {
            //go through each cell and update the player object
            switch (index) {
                case 2:
                    teamName.getConferenceStats().put("oppYearPoints", Double.valueOf(cell.getTextContent()));
                    break;
                case 3:
                    teamName.getConferenceStats().put("oppLast3Games", Double.valueOf(cell.getTextContent()));
                    break;
                case 4:
                    teamName.getConferenceStats().put("oppLastGame", Double.valueOf(cell.getTextContent()));
                    break;
                case 5:
                    teamName.getConferenceStats().put("oppHomePoints", Double.valueOf(cell.getTextContent()));
                    break;
                case 6:
                    teamName.getConferenceStats().put("oppAwayPoints", Double.valueOf(cell.getTextContent()));
                    break;
            }
            index++;
        }
    }

    private static void populatePoints(Team teamName, HtmlTableRow row) {
        int index = 0;
        for (HtmlTableCell cell : row.getCells()) {
            //go through each cell and update the player object
            switch (index) {
                case 2:
                    teamName.getConferenceStats().put("yearPoints", Double.valueOf(cell.getTextContent()));
                    break;
                case 3:
                    teamName.getConferenceStats().put("last3Games", Double.valueOf(cell.getTextContent()));
                    break;
                case 4:
                    teamName.getConferenceStats().put("lastGame", Double.valueOf(cell.getTextContent()));
                    break;
                case 5:
                    teamName.getConferenceStats().put("homePoints", Double.valueOf(cell.getTextContent()));
                    break;
                case 6:
                    teamName.getConferenceStats().put("awayPoints", Double.valueOf(cell.getTextContent()));
                    break;
            }
            index++;
        }
    }

    private static boolean correctRow(Team teamName, HtmlTableRow row) {
        String rowName = row.getCell(1).getTextContent();
        if(teamName.getTeamNames().get(rowName).equals(teamName.getTeamName())){
            return true;
        }
        return false;
    }
}
