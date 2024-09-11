package videostore.horror;

import lombok.Getter;

@Getter
public class Movie {
	private final String title;
	private final int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}
}