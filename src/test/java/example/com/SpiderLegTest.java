package example.com;

import org.junit.Assert;
import org.junit.Test;

public class SpiderLegTest {

    @Test
    public void countWords() {
        SpiderLeg leg = new SpiderLeg();
        leg.crawl("https://javarush.ru/groups/posts/605-junit");
        Assert.assertEquals(leg.countWords("junit"), leg.countWords("junit"));
    }

    @Test
    public void getLinks() {
        SpiderLeg leg = new SpiderLeg();
        leg.crawl("https://javarush.ru/groups/posts/605-junit");
        Assert.assertArrayEquals(leg.getLinks().toArray(), leg.getLinks().toArray());
    }
}
