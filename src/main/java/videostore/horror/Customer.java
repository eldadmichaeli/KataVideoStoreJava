package videostore.horror;

import lombok.Getter;

class Customer {

	@Getter
    private final String name;
	private final Cart cart = new Cart();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Movie movie, int days) {
		cart.addRental(movie, days);
	}


    public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		// iterate for each rental
		for (Movie movie : cart.getRentals().keySet()) {
			double movieRentalPrice = cart.calcMovieRentalPrice(movie);
			// add frequent renter points
			frequentRenterPoints++;

			frequentRenterPoints += addRenterBonusPoint(movie);
			// show figures line for this rental
			result.append("\t").append(movie.getTitle()).append("\t").append(movieRentalPrice ).append( "\n");
			totalAmount += movieRentalPrice;
		}
		// add footer lines
		result.append("Amount owed is ").append(totalAmount).append( "\n");
		result.append( "You earned ").append( frequentRenterPoints ).append( " frequent renter points");
		return result.toString();
	}

	private int addRenterBonusPoint(Movie movie) {
		if ((movie.getPriceCode() == MovieType.NEW_RELEASE.getPriceCode()) && cart.getRentals().get(movie) > 1)
			return 1;
		else return 0;
	}


}