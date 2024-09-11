package videostore.horror;

public class Movie {

	private String title;
	private PriceCode priceCode;

	public Movie(String title, PriceCode priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public PriceCode getPriceCode() {
		return this.priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return this.title;
	}
}