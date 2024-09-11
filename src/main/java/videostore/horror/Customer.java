package videostore.horror;

import videostore.horror.util.Constants;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int daysRented) {
		rentals.put(movie, daysRented);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder();
		result.append(Constants.RENTAL_RECORD_FOR).append(getName()).append(Constants.LINE_BREAK);
		for (Movie movie : rentals.keySet()) {
			double currentPriceAmount = 0;
			int daysRentedForMovie = rentals.get(movie);
			currentPriceAmount = calculateAmount(movie, currentPriceAmount, daysRentedForMovie);
			frequentRenterPoints = addBonusForTwoDays(movie, daysRentedForMovie, frequentRenterPoints);
			// show figures line for this rental
			result.append(Constants.TAB).append(movie.getTitle()).append(Constants.TAB).append(currentPriceAmount).append(Constants.LINE_BREAK);
			totalAmount += currentPriceAmount;
		}
		return buildResult(result, totalAmount, frequentRenterPoints);
	}

	// add bonus for a two day new release rental
	private static int addBonusForTwoDays(Movie movie, int moviePriceCode, int frequentRenterPoints) {
		frequentRenterPoints++;
		if (shouldAddMoreBonus(movie, moviePriceCode))
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private static boolean shouldAddMoreBonus(Movie movie, int moviePriceCode) {
		return movie.getPriceCode() != null &&
				(movie.getPriceCode() == Constants.NEW_RELEASE)
				&& moviePriceCode > 1;
	}

	private static double calculateAmount(Movie movie, double currentPriceAmount, int daysRentedForMovie) {
		switch (movie.getPriceCode()) {
			case Constants.REGULAR:
				currentPriceAmount = getRegularPriceAmount(currentPriceAmount, daysRentedForMovie);
				break;
			case Constants.NEW_RELEASE:
				currentPriceAmount = getNewReleasePriceAmount(currentPriceAmount, daysRentedForMovie);
				break;
			case Constants.CHILDREN:
				currentPriceAmount = getChildrenPriceAmount(currentPriceAmount, daysRentedForMovie);
				break;
		}
		return currentPriceAmount;
	}

	private static double getChildrenPriceAmount(double currentPriceAmount, int daysRentedForMovie) {
		currentPriceAmount += 1.5;
		if (daysRentedForMovie > Constants.RENTED_FOR_THREE_DAYS)
			currentPriceAmount += (daysRentedForMovie - Constants.RENTED_FOR_THREE_DAYS) * 1.5;
		return currentPriceAmount;
	}

	private static double getNewReleasePriceAmount(double currentPriceAmount, int daysRentedForMovie) {
		currentPriceAmount += daysRentedForMovie * 3;
		return currentPriceAmount;
	}

	private static double getRegularPriceAmount(double currentPriceAmount, int daysRentedForMovie) {
		currentPriceAmount += 2;
		if (daysRentedForMovie > Constants.RENTED_FOR_TWO_DAYS)
			currentPriceAmount += (daysRentedForMovie - Constants.RENTED_FOR_TWO_DAYS) * 1.5;
		return currentPriceAmount;
	}

	private String buildResult(StringBuilder result, double totalAmount, int frequentRenterPoints) {
		result.append(Constants.AMOUNT_OWED_IS).append(totalAmount).append(Constants.LINE_BREAK);
		result.append(Constants.YOU_EARNED).append(frequentRenterPoints).append(Constants.FREQUENT_RENTER_POINTS);
		return result.toString();
	}
}