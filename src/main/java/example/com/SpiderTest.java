package example.com;

import java.util.Arrays;

/**
 * Class SpiderTest - main class
 *
 * @author Viktor Dunaev
 * @version 1.0
 */
public class SpiderTest {
    /**
     * It creates a spider (which creates spider legs) and crawls the web.
     *
     * @param args :
     *             args[0] - max depth,
     *             args[1] - max count of pages,
     *             args[2] - seed,
     *             args[3] ... - words for search
     */
    public static void main(String[] args) {
        if (args.length > 3) {
            try {
                Spider spider = new Spider(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                String seed = args[2];
                String[] terms = Arrays.copyOfRange(args, 3, args.length);

                spider.count(seed, terms);
                Printer printer = new Printer();
                printer.printNotSortedStatistic(spider.getStatistic(), "statistic.csv");
                printer.printSortedStatistic(spider.getStatistic(), "sorted_statistic.csv");
            } catch (NumberFormatException ex) {
                System.out.println("Wrong parameters of max depth or max pages count.");
            }
        } else {
            System.out.println("You don't write all necessary parameters");
        }
    }
}

