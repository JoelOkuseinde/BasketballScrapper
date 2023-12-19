import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleParser {
    public static void printScheudle(List<MatchUp> schedule) {
        for (MatchUp matchup : schedule){
            System.out.println(matchup.getAllString());
        }
    }

    public static List<List<String>> returnScheule() throws IOException {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        HtmlPage schedulePage = client.getPage("https://www.espn.co.uk/nba/schedule");
        return parseSchedule(schedulePage);
    }

    private static List<List<String>> parseSchedule(HtmlPage schedulePage) {
        List<List<String>> allMatchups = new ArrayList<>();
        int numberOfTables = schedulePage.getByXPath("//table").size();

        for (int i = 0; i < numberOfTables; i++) {
            HtmlTable table = (HtmlTable) schedulePage.getByXPath("//table").get(i);
            for (HtmlTableRow currentRow : table.getRows()){
                if (currentRow.getCell(0).getTextContent().equalsIgnoreCase("matchup") || currentRow.getCell(0).getTextContent().equalsIgnoreCase("tbd")){
                    continue;
                }
                List<String> row = new ArrayList<>();
                row.add(currentRow.getCell(0).getTextContent());
                row.add(currentRow.getCell(1).getTextContent().substring(currentRow.getCell(1).getTextContent().indexOf("@")+3));
                row.add(currentRow.getCell(2).getTextContent());
                allMatchups.add(row);
            }
        }
        return allMatchups;
    }
}