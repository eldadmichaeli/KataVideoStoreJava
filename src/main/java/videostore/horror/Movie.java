package videostore.horror;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private final String title;
    private Integer priceCode;

    public Movie(String title, Integer priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }
}