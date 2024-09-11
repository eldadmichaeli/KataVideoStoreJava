package videostore.horror;

import java.util.*;

class Customer {
	private String name;
	private Map<Movie, Integer> movieToAmountOfDays = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Movie movie, int amountOfDays) {
		movieToAmountOfDays.put(movie, amountOfDays);
	}

	public String getName() {
		return this.name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		// iterate for each rental
		for (Movie movie : movieToAmountOfDays.keySet()) {
			int amountOfDays = movieToAmountOfDays.get(movie);
			double amount = calculateAmount(movie, amountOfDays);
			// add frequent renter points
			frequentRenterPoints += calculateFrequent(movie, amountOfDays);
			// show figures line for this rental
			result += "\t" + movie.getTitle() + "\t" + amount + "\n";
			totalAmount += amount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private int calculateFrequent(Movie movie, int amountOfDays) {
		int frequentRenterPoints = 1;
		// add bonus for a two day new release rental
		if (movie.getPriceCode() != null &&
				(movie.getPriceCode() == PriceCode.NEW_RELEASE)
				&& amountOfDays > 1)
			frequentRenterPoints++;

		return frequentRenterPoints;
	}

	private double calculateAmount(Movie movie, int amountOfDays) {
		double amount = 0;
		// determine amounts for each line
		switch (movie.getPriceCode()) {
			case REGULAR:
				amount += 2;
				if (amountOfDays > 2)
					amount += (amountOfDays - 2) * 1.5;
				break;
			case NEW_RELEASE:
				amount += amountOfDays * 3;
				break;
			case CHILDREN:
				amount += 1.5;
				if (amountOfDays > 3)
					amount += (amountOfDays - 3) * 1.5;
				break;
		}

		return amount;
	}
}