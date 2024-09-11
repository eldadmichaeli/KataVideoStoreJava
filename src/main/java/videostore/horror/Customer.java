package videostore.horror;

import java.util.*;

class Customer {
    private static final double REGULAR_BASE_AMOUNT = 2.0;
    private static final double REGULAR_EXTRA_AMOUNT = 1.5;
    private static final double NEW_RELEASE_AMOUNT = 3.0;
    private static final double CHILDREN_BASE_AMOUNT = 1.5;
    private static final double CHILDREN_EXTRA_AMOUNT = 1.5;
    private static final int REGULAR_DAYS_THRESHOLD = 2;
    private static final int CHILDREN_DAYS_THRESHOLD = 3;

    private String name;
    private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int daysRented) {
        rentals.put(movie, daysRented);
    }

    public String getName() {
        return name;
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
        double amount = 0;
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                amount += REGULAR_BASE_AMOUNT;
                if (daysRented > REGULAR_DAYS_THRESHOLD) {
                    amount += (daysRented - REGULAR_DAYS_THRESHOLD) * REGULAR_EXTRA_AMOUNT;
                }
                break;
            case Movie.NEW_RELEASE:
                amount += daysRented * NEW_RELEASE_AMOUNT;
                break;
            case Movie.CHILDREN:
                amount += CHILDREN_BASE_AMOUNT;
                if (daysRented > CHILDREN_DAYS_THRESHOLD) {
                    amount += (daysRented - CHILDREN_DAYS_THRESHOLD) * CHILDREN_EXTRA_AMOUNT;
                }
                break;
        }
        return amount;
    }

    private int calculateFrequentRenterPoints(Movie movie, int daysRented) {
        int points = 1;
        if (movie.getPriceCode() == Movie.NEW_RELEASE && daysRented > 1) {
            points++;
        }
        return points;
    }
}
