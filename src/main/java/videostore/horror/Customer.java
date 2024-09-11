package videostore.horror;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

class Customer {
	@Getter
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int rentalDays) {
		rentals.put(movie, rentalDays);
	}

	public String statement() {
		StringBuilder result = new StringBuilder();
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		 result.append("Rental Record for ").append(getName()).append("\n");
		// iterate for each rental
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for each line
			int dr = rentals.get(each);
			switch (each.getPriceCode()) {
				case Movie.REGULAR:
					thisAmount += 2;
					if (dr > 2)
						thisAmount += (dr - 2) * 1.5;
					break;
				case Movie.NEW_RELEASE:
					thisAmount += dr * 3;
					break;
				case Movie.CHILDREN:
					thisAmount += 1.5;
					if (dr > 3)
						thisAmount += (dr - 3) * 1.5;
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceCode() != null &&
				 (each.getPriceCode() == Movie.NEW_RELEASE)
				 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result.append("\t").append(each.getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}
}