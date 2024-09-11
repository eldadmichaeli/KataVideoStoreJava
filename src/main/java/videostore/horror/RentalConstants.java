package videostore.horror;

import lombok.Getter;

@Getter
public enum RentalConstants {
    REGULAR_BASE_AMOUNT(2.0),
    REGULAR_EXTRA_AMOUNT(1.5),
    NEW_RELEASE_AMOUNT(3.0),
    CHILDREN_BASE_AMOUNT(1.5),
    CHILDREN_EXTRA_AMOUNT(1.5),
    REGULAR_DAYS_THRESHOLD(2),
    CHILDREN_DAYS_THRESHOLD(3),
    FREQUENT_RENTER_MIN_POINT(1);

    private final double value;

    RentalConstants(double value) {
        this.value = value;
    }
}