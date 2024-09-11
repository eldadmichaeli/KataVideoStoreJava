package videostore.horror;

public enum MovieType {
    REGULAR(0),
    NEW_RELEASE(1),
    CHILDREN(2);

    private final Integer priceCode;

    MovieType(Integer priceCode) {
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }
}
