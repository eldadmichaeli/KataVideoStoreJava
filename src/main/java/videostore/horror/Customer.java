package videostore.horror;

import lombok.Getter;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static videostore.horror.MovieType.NEW_RELEASE;

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

		for (var movieWithRentalAmount : rentals.entrySet()) {
			Movie movie = movieWithRentalAmount.getKey();
			int rentalDays = movieWithRentalAmount.getValue();

			double amountPerMovie = movie.calculateAmount(rentalDays);
			// add frequent renter points
			frequentRenterPoints += movie.calculateRentalPoints(rentalDays);

			// show figures line for this rental
			result.append("\t").append(movie.getTitle()).append("\t").append(amountPerMovie).append("\n");
			totalAmount += amountPerMovie;
		}

		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}
}