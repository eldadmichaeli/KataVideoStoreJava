package videostore.horror;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class Cart {
    public static final double DAY_PRICE = 1.5;
    public static final int MIN_DAYS_FOR_REGULAR = 2;
    public static final int MIN_DAYS_CHILDREN = 3;

    @Getter
    private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public void addRental(Movie movie, int days) {
        rentals.put(movie, days);
    }

    public double calcMovieRentalPrice(Movie movie) {
        int rentalDays = rentals.get(movie);
        return getPriceCalculationStrategy(movie.getPriceCode()).apply(rentalDays);
    }

    private Map<Integer, Function<Integer, Double>> priceCalculationStrategies = Map.of(
            Movie.REGULAR, this::calculateRegularPrice,
            Movie.NEW_RELEASE, this::calculateNewReleasePrice,
            Movie.CHILDRENS, this::calculateChildrenPrice
    );

    private Double calculateChildrenPrice(Integer days) {
        double thisAmount = DAY_PRICE;
        if (days > MIN_DAYS_CHILDREN)
            thisAmount += (days - 3) * DAY_PRICE;
        return thisAmount;
    }

    private Double calculateNewReleasePrice(Integer days) {
        return days * 3d;
    }

    private Double calculateRegularPrice(Integer days) {
        double thisAmount = 2;
        if (days > MIN_DAYS_FOR_REGULAR)
            thisAmount += (days - 2) * DAY_PRICE;
        return thisAmount;
    }

    private Function<Integer, Double> getPriceCalculationStrategy(int priceCode) {
        return priceCalculationStrategies.getOrDefault(priceCode, days -> 0.0);
    }
}
