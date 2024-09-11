package videostore.horror;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import static videostore.horror.MovieType.NEW_RELEASE;

@Getter
@AllArgsConstructor
public class Movie {
	@NonNull
	private final String title;
	@NonNull
	private final MovieType movieType;

	public double calculateAmount(int rentalDays){
		double amount = 0;
		switch (movieType) {
			case REGULAR:
				amount += 2;
				if (rentalDays > 2)
					amount += (rentalDays - 2) * 1.5;
				break;
			case NEW_RELEASE:
				amount += rentalDays * 3;
				break;
			case CHILDREN:
				amount += 1.5;
				if (rentalDays > 3)
					amount += (rentalDays - 3) * 1.5;
				break;
		}
		return amount;
	}

	public int calculateRentalPoints(int rentalDays){
		if (movieType == NEW_RELEASE && rentalDays > 1)
			return 2;
		return 1;
	}
}