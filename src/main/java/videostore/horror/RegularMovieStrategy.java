package videostore.horror;

import videostore.horror.util.Constants;

public class RegularMovieStrategy implements MovieStrategy {
    @Override
    public double calculateAmount(double currentPriceAmount, int daysRentedForMovie) {
        currentPriceAmount += 2;
        if (daysRentedForMovie > Constants.RENTED_FOR_TWO_DAYS)
            currentPriceAmount += (daysRentedForMovie - Constants.RENTED_FOR_TWO_DAYS) * 1.5;
        return currentPriceAmount;
    }
}
