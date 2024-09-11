package videostore.horror;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static videostore.horror.MovieType.CHILDREN;
import static videostore.horror.MovieType.NEW_RELEASE;
import static videostore.horror.MovieType.REGULAR;


class CustomerTest {
    public static final String EXPECTED_CUSTOMER_STATEMENT = """
    Rental Record for John Doe
    \tStar Wars	18.0
    \tSofia	7.5
    \tInception	6.5
    Amount owed is 32.0
    You earned 4 frequent renter points""";

    @Test
    void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Movie("Star Wars", NEW_RELEASE), 6);
        customer.addRental(new Movie("Sofia", CHILDREN), 7);
        customer.addRental(new Movie("Inception", REGULAR), 5);

        assertEquals(EXPECTED_CUSTOMER_STATEMENT, customer.statement());
    }
}
