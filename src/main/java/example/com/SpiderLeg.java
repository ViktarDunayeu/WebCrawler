package example.com;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class SpiderLeg for making low-level crawling operations.
 *
 * @author Viktor Dunaev
 * @version 1.0
 */
public class SpiderLeg {
    /**
     * Parameter {@link String} - fake USER_AGENT so the web server thinks the robot is a normal web browser
     */
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

    /**
     * <pre>{@link List} &lt;{@link String}&gt;</pre> just a list of URLs
     */
    private final List<String> links = new LinkedList<>();

    /**
     * Current web page {@link Document}
     */
    private Document htmlDocument;

    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a {@link SpiderLeg#countWords(String)} after the successful crawl
     *
     * @param url - The URL to visit
     */
    public void crawl(String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;

            Elements linksOnPage = htmlDocument.select("a[href]");
            for (Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
            }
        } catch (IOException ioe) {
            System.out.println("Error in out HTTP request " + ioe);
        }
    }

    /**
     * Method counts words on the current page
     *
     * @param searchWord {@link String} word for searching
     * @return count of words
     */
    public int countWords(String searchWord) {
        if (this.htmlDocument != null && this.htmlDocument.body() != null) {
            String bodyText = this.htmlDocument.body().text();
            return bodyText.toLowerCase().split(searchWord.toLowerCase()).length - 1;
        } else return 0;
    }

    /**
     * @return <pre>{@link List} &lt;{@link String}&gt;</pre> a list of all the URLs on the page
     */
    public List<String> getLinks() {
        return this.links;
    }
}
