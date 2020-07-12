package example.com;

import java.util.Objects;

/**
 * Class for storage URL with properties <b>depth</b> and <b>url</b>.
 * @author Viktor Dunaev
 * @version 1.0
 */
public class CrawlURL {

    private int depth;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrawlURL crawlURL = (CrawlURL) o;
        return depth == crawlURL.depth &&
                Objects.equals(url, crawlURL.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth, url);
    }

    /**
    * @param depth {@link CrawlURL#depth}
    * @param url {@link CrawlURL#url}
    */
    CrawlURL(int depth, String url) {
        this.depth = depth;
        this.url = url;
    }

    /**
     * @return {@link CrawlURL#depth}
     */
    public int getDepth() {
        return depth;
    }

    /**
     *
     * @param depth {@link CrawlURL#depth}
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     *
     * @return {@link CrawlURL#url}
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url {@link CrawlURL#url}
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
