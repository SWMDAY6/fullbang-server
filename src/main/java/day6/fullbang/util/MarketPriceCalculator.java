package day6.fullbang.util;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;

public class MarketPriceCalculator {

    private MarketPriceCalculator() {
    }

    private static final Double STANDARD_CRITICAL_VALUE = 1.96D;
    private static final Double ZERO = 0D;

    public static Double getMean(List<Double> prices) {

        Double sum = ZERO;

        for (Double price : prices) {
            sum += price;
        }

        return sum / prices.size();
    }

    public static Double getConfidenceIntervalOffset(List<Double> prices) {
        return STANDARD_CRITICAL_VALUE * getStandardDeviation(prices) / sqrt(prices.size());
    }

    public static Double getStandardDeviation(List<Double> values) {

        List<Double> squaredValues = new ArrayList<>();
        values.forEach(v -> squaredValues.add(pow(v, 2)));

        return sqrt(getMean(squaredValues) - pow(getMean(values), 2));
    }
}
