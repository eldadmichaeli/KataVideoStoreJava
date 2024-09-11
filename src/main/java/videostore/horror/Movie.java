package videostore.horror;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Movie {
	public static final int CHILDREN = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	private String title;
	private Integer priceCode;
}
