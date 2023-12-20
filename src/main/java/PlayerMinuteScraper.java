import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
public class PlayerMinuteScraper {
    public static void playerMinuteScraper() throws IOException {
        WebClient client = new WebClient();

        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);

        HtmlPage minutesPage = client.getPage("https://www.teamrankings.com/nba/player-stat/minutes-played");
        HtmlTable table =  minutesPage.getFirstByXPath("//table");
        HtmlTableRow checking = table.getRow(3);
        System.out.println(checking.getCell(4).getTextContent());
    }
}

