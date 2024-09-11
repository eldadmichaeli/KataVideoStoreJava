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
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		result.append("Rental Record for ").append(getName()).append("\n");

		for (var movieWithRentalAmount : rentals.entrySet()) {
			Movie movie = movieWithRentalAmount.getKey();
			int rentalDays = movieWithRentalAmount.getValue();

			double pricePerMovie = movie.calculatePrice(rentalDays);
			frequentRenterPoints += movie.calculateRentalPoints(rentalDays);

			result.append("\t").append(movie.getTitle()).append("\t").append(pricePerMovie).append("\n");
			totalPrice += pricePerMovie;
		}

		result.append("Amount owed is ").append(totalPrice).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}
}