package videostore.horror;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Movie {
	private static final Integer STANDARD_MOVIE_BONUS = 1;
	private static final Integer NEW_RELEASE_MOVIE_BONUS = 2;

	private static final Integer REGULAR_PRICE = 2;
	private static final Integer REGULAR_DAYS_LIMIT = 2;
	private static final Double REGULAR_OVERDRAFT_COEFFICIENT = 1.5;

	private static final Integer NEW_RELEASE_PRICE = 3;

	private static final Double CHILDREN_PRICE = 1.5;
	private static final Integer CHILDREN_DAYS_LIMIT = 3;
	private static final Double CHILDREN_OVERDRAFT_COEFFICIENT = 1.5;

	@NonNull
	private final String title;
	@NonNull
	private final MovieType movieType;

	public double calculatePrice(int rentalDays){
		double price = 0;
		switch (movieType) {
			case REGULAR:
				price += REGULAR_PRICE;
				if (rentalDays > REGULAR_DAYS_LIMIT)
					price += (rentalDays - REGULAR_DAYS_LIMIT) * REGULAR_OVERDRAFT_COEFFICIENT;
				break;
			case NEW_RELEASE:
				price += rentalDays * NEW_RELEASE_PRICE;
				break;
			case CHILDREN:
				price += CHILDREN_PRICE;
				if (rentalDays > CHILDREN_DAYS_LIMIT)
					price += (rentalDays - CHILDREN_DAYS_LIMIT) * CHILDREN_OVERDRAFT_COEFFICIENT;
				break;
		}
		return price;
	}

	public int calculateRentalPoints(int rentalDays){
		if (movieType == MovieType.NEW_RELEASE && rentalDays > 1)
			return NEW_RELEASE_MOVIE_BONUS;
		return STANDARD_MOVIE_BONUS;
	}
}