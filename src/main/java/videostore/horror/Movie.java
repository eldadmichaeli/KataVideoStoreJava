package videostore.horror;

import lombok.Getter;

public class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDREN = 2;
	@Getter
	private final String title;
	@Getter
	private final Integer priceCode;

	public Movie(String title, Integer priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}
}