package videostore.horror;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

class MovieRentalBusiness {
    @Getter
    private String name;
    private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public MovieRentalBusiness(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.put(movie, daysRented);
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Map.Entry<Movie, Integer> entry : rentals.entrySet()) {
            Movie movie = entry.getKey();
            int daysRented = entry.getValue();
            double thisAmount = calculateAmount(movie, daysRented);

            frequentRenterPoints += calculateFrequentRenterPoints(movie, daysRented);

            result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    private double calculateAmount(Movie movie, int daysRented) {
        return switch(movie.getPriceCode()) {
            case Movie.REGULAR -> calculateRegularAmount(daysRented);
            case Movie.NEW_RELEASE -> calculateNewReleaseAmount(daysRented);
            case Movie.CHILDREN -> calculateChildrenAmount(daysRented);
            default -> throw new IllegalArgumentException("Unexpected price code: " + movie.getPriceCode());
        };
    }

    private double calculateRegularAmount(int daysRented) {
        double amount = RentalConstants.REGULAR_BASE_AMOUNT.getValue();
        if (daysRented > RentalConstants.REGULAR_DAYS_THRESHOLD.getValue()) {
            amount += (daysRented - RentalConstants.REGULAR_DAYS_THRESHOLD.getValue()) * RentalConstants.REGULAR_EXTRA_AMOUNT.getValue();
        }
        return amount;
    }

    private double calculateNewReleaseAmount(int daysRented) {
        return daysRented * RentalConstants.NEW_RELEASE_AMOUNT.getValue();
    }

    private double calculateChildrenAmount(int daysRented) {
        double amount = RentalConstants.CHILDREN_BASE_AMOUNT.getValue();
        if (daysRented > RentalConstants.CHILDREN_DAYS_THRESHOLD.getValue()) {
            amount += (daysRented - RentalConstants.CHILDREN_DAYS_THRESHOLD.getValue()) * RentalConstants.CHILDREN_EXTRA_AMOUNT.getValue();
        }
        return amount;
    }

    private int calculateFrequentRenterPoints(Movie movie, int daysRented) {
        int points = (int) RentalConstants.FREQUENT_RENTER_MIN_POINT.getValue();
        if (movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1) {
            points++;
        }
        return points;
    }
}