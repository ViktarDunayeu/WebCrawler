package example.com;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsTest {

    @Test
    public void addCount() {
        Statistics statistics = new Statistics("0");
        Integer total = statistics.getTotal();
        total++;
        Assert.assertNotEquals(total, statistics.getTotal());
        statistics.addCount(1);
        Assert.assertEquals(total, statistics.getTotal());

    }

    @Test
    public void formStatistic() {
        StringBuffer buffer = new StringBuffer("hello");
        Statistics statistics = new Statistics("hello");
        buffer.append(" 1");
        Assert.assertNotEquals(buffer.toString(), statistics.getStatistics());
        statistics.formStatistic(1);
        Assert.assertEquals(buffer.toString(), statistics.getStatistics());
    }
}
