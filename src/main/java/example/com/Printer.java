package example.com;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class Printer for output results.
 * @author Viktor Dunaev
 * @version 1.0
 */
public class Printer {

    /** Const for count of sorted pages */
    private static final int SORTED_PAGES = 10;

    /**
     * Method serialized not sorted results from CSV format
     * @param result <pre>{@link List} &lt;{@link Statistics}&gt;</pre>
     * @param csvFile {@link String}
     */
    public void printNotSortedStatistic(List<Statistics> result, String csvFile) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
            for (Statistics statistic : result) {
                String[] elements = statistic.getStatistics().split(" ");
                writer.writeNext(elements);
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("File not created " + exception);
        }
    }

    /**
     * Method serializes top-10 sorted results from CSV format
     * and prints top-10 sorted results in console
     * @see Printer#printNotSortedStatistic(List, String)
     * @see Printer#printToConsole(List)
     * @param result <pre>{@link List} &lt;{@link Statistics}&gt;</pre>
     * @param csvFile {@link String}
     */
    public void printSortedStatistic(List<Statistics> result, String csvFile) {
        result.sort((o1, o2) -> o2.getTotal() - o1.getTotal());
        printToConsole(result.subList(0, SORTED_PAGES));
        printNotSortedStatistic(result.subList(0, SORTED_PAGES), csvFile);
    }

    /**
     * Method prints results in console
     * @param result <pre>{@link List} &lt;{@link Statistics}&gt;</pre>
     */
    public void printToConsole(List<Statistics> result) {
        for (Statistics statistic : result) {
            System.out.println(statistic.getStatistics());
        }
    }

}
