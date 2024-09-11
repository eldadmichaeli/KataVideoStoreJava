package videostore.horror;

import lombok.Getter;

import java.util.*;

class Customer {

	@Getter
  private String name;

	private final Map<Movie, Integer> movieToAmountOfDays = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int amountOfDays) {
		movieToAmountOfDays.put(movie, amountOfDays);
	}

  public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Map<Movie, Double> movieToAmount = new LinkedHashMap<>();

		// iterate for each rental
		for (Movie movie : movieToAmountOfDays.keySet()) {
			int amountOfDays = movieToAmountOfDays.get(movie);
			double amount = calculateAmount(movie, amountOfDays);
			movieToAmount.put(movie, amount);
			// add frequent renter points
			frequentRenterPoints += calculateFrequent(movie, amountOfDays);
			totalAmount += amount;
		}

		return createReport(movieToAmount, totalAmount, frequentRenterPoints);
	}

	private String createReport(Map<Movie, Double> movieToAmount, double totalAmount, int frequentRenterPoints) {
		StringBuilder result = new StringBuilder();
		result.append("Rental Record for ").append(this.name).append(System.lineSeparator());

		for (Movie movie : movieToAmount.keySet()) {
			double amount = movieToAmount.get(movie);
			result.append("\t").append(movie.title()).append("\t").append(amount).append(System.lineSeparator());
		}

		result.append("Amount owed is ").append(totalAmount).append(System.lineSeparator());
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

		return result.toString();
	}

	private int calculateFrequent(Movie movie, int amountOfDays) {
		int frequentRenterPoints = 1;
		// add bonus for a two day new release rental
		if (movie.priceCode() != null &&
				(movie.priceCode() == PriceCode.NEW_RELEASE)
				&& amountOfDays > 1)
			frequentRenterPoints++;

		return frequentRenterPoints;
	}

	private double calculateAmount(Movie movie, int amountOfDays) {
		double amount = 0;
		// determine amounts for each line
		switch (movie.priceCode()) {
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