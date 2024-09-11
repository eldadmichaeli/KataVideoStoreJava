package videostore.horror;

public class NewReleaseMovieStrategy implements MovieStrategy {
    @Override
    public double calculateAmount(double currentPriceAmount, int daysRentedForMovie) {
        currentPriceAmount += daysRentedForMovie * 3;
        return currentPriceAmount;
    }
}
