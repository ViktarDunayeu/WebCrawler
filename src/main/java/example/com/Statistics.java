package example.com;

/**
 * Class for storage Statistic for one link
 * with properties <b>{@link Integer} total</b> and <b>{@link StringBuffer} statistic</b>.
 *
 * @author Viktor Dunaev
 * @version 1.0
 */
public class Statistics {
    private Integer total = 0;
    final private StringBuffer statistics;

    /**
     * @param currentUrl {@link Statistics#statistics}
     */
    Statistics(String currentUrl) {
        statistics = new StringBuffer(currentUrl);
    }

    /**
     * @return {@link Statistics#total}
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @return {@link Statistics#statistics}
     */
    public String getStatistics() {
        return statistics.toString();
    }

    /**
     * Method for increment count
     *
     * @param count {@link Statistics#total}
     */
    public void addCount(int count) {
        total += count;
    }

    /**
     * Method for format statistic
     *
     * @param count - count of current word
     */
    public void formStatistic(int count) {
        statistics.append(" ");
        statistics.append(count);
    }
}
