package videostore.horror;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Movie {
	private final String title;
	private final MovieType movieType;
}