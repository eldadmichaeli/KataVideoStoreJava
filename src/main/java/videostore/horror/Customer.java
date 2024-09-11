package videostore.horror;

import java.util.*;

class Customer {
	public static final double DAY_PRICE = 1.5;
	public static final int MIN_DAYS_FOR_REGULAR = 2;
	public static final int MIN_DAYS_CHILDREN = 3;
	private String name;
	private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String getName() {
		return name;
	}


	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate for each rental
		for (Movie movie : rentals.keySet()) {
			double movieRentalPrice = calcMovieRentalPrice(movie);
			// add frequent renter points
			frequentRenterPoints++;

			frequentRenterPoints += addRenterBonusPoint(movie);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + movieRentalPrice + "\n";
			totalAmount += movieRentalPrice;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int addRenterBonusPoint(Movie movie) {
		if (movie.getPriceCode() != null && (movie.getPriceCode() == Movie.NEW_RELEASE) && rentals.get(movie) > 1)
			return 1;
		else return 0;
	}

	private double calcMovieRentalPrice(Movie movie){
		double thisAmount = 0;
		// determine amounts for movie line
		int rentalDays = rentals.get(movie);
		switch (movie.getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (rentalDays > MIN_DAYS_FOR_REGULAR)
					thisAmount += (rentalDays - 2) * DAY_PRICE;
				break;
			case Movie.NEW_RELEASE:
				thisAmount += rentalDays * 3;
				break;
			case Movie.CHILDRENS:
				thisAmount += DAY_PRICE;
				if (rentalDays > MIN_DAYS_CHILDREN)
					thisAmount += (rentalDays - 3) * DAY_PRICE;
				break;
		}

		return thisAmount;
	}
}