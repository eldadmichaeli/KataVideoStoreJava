package videostore.horror;

import videostore.horror.util.Constants;

public class ChildrenMovieStrategy implements MovieStrategy {
    @Override
    public double calculateAmount(double currentPriceAmount, int daysRentedForMovie) {
        currentPriceAmount += 1.5;
        if (daysRentedForMovie > Constants.RENTED_FOR_THREE_DAYS)
            currentPriceAmount += (daysRentedForMovie - Constants.RENTED_FOR_THREE_DAYS) * 1.5;
        return currentPriceAmount;
    }
}
