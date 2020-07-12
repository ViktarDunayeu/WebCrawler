package example.com;

import org.junit.Assert;
import org.junit.Test;

public class SpiderUnitTest {

    @Test
    public void testGetStatistic() {
        Spider spider = new Spider(1, 1);
        String[] words = new String[1];
        words[0] = "junit";
        spider.count("https://javarush.ru/groups/posts/605-junit", words);

        Assert.assertNotNull(spider.getStatistic());
    }
}
