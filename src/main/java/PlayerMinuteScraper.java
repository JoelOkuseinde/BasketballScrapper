import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
public class PlayerMinuteScraper {
    public static void playerMinuteScraper() throws IOException {
        WebClient client = new WebClient();

        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);

        HtmlPage minutesPage = client.getPage("https://basketball.realgm.com/nba/stats");
        int table =  minutesPage.getByXPath("//table").size();
        System.out.println(table);
    }
}

