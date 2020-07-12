package example.com;


import java.util.*;

/**
 * Class Spider for crawling.
 *
 * @author Viktor Dunaev
 * @version 1.0
 */
public class Spider {

    /**
     * Parameter - max count of pages to crawling
     * set in the script
     */
    private final int maxPagesToSearch;

    /**
     * Parameter - max depth of crawling
     * set in the script
     */
    private final int maxDeepLevel;

    /**
     * <pre>{@link Set} &lt;{@link CrawlURL}&gt;</pre> stores visited pages
     */
    private final Set<CrawlURL> pagesVisited = new HashSet<>();

    /**
     * <pre>{@link List} &lt;{@link CrawlURL}&gt;</pre> stores not visited pages
     */
    private final List<CrawlURL> pagesToVisit = new LinkedList<>();

    /**
     * <pre>{@link List} &lt;{@link Statistics}&gt;</pre> stores results
     */
    private final List<Statistics> statistics = new ArrayList<>();

    /**
     * @param maxDepth {@link Spider#maxDeepLevel}
     * @param maxPages {@link Spider#maxPagesToSearch}
     */
    Spider(int maxDepth, int maxPages) {
        this.maxPagesToSearch = maxPages;
        this.maxDeepLevel = maxDepth;
    }

    /**
     * @return <pre>{@link List} &lt;{@link Statistics}&gt;</pre>
     */
    public List<Statistics> getStatistic() {
        return statistics;
    }

    /**
     * Returns the next {@link CrawlURL} to visit (in the order that they were found). We also do a check to make
     * sure this method doesn't return a URL that has already been visited.
     *
     * @return {@link CrawlURL}
     */
    private CrawlURL nextUrl() {
        CrawlURL nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while (this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

    /**
     * Method counts words on the current page, collect links and statistic
     *
     * @param url         {@link String} current URL
     * @param searchWords array of {@link String}
     * @see SpiderLeg
     * @see Statistics
     */
    public void count(String url, String[] searchWords) {
        do {
            CrawlURL currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if (this.pagesToVisit.isEmpty()) {
                currentUrl = new CrawlURL(0, url);
                this.pagesVisited.add(currentUrl);
            } else {
                currentUrl = this.nextUrl();
            }
            if (currentUrl.getUrl().split("/")[0].equals("https:") || currentUrl.getUrl().split("/")[0].equals("http:")) {
                leg.crawl(currentUrl.getUrl());
                Statistics tempStatistic = new Statistics(currentUrl.getUrl());

                for (String searchWord : searchWords) {
                    int count = leg.countWords(searchWord);
                    tempStatistic.addCount(count);
                    tempStatistic.formStatistic(count);
                }
                statistics.add(tempStatistic);
            }
            if (currentUrl.getDepth() + 1 <= maxDeepLevel) {
                for (String link : leg.getLinks()) {
                    this.pagesToVisit.add(new CrawlURL(currentUrl.getDepth() + 1, link));
                }
            }
        } while (this.pagesVisited.size() < maxPagesToSearch && !pagesToVisit.isEmpty());
    }
}
