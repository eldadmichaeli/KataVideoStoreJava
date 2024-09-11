package videostore.horror;

public interface MovieStrategy {
    double calculateAmount(double currentPriceAmount, int daysRentedForMovies);
}
