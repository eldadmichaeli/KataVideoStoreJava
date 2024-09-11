package videostore.horror;

public enum MovieType {
    CHILDREN(2),REGULAR(0), NEW_RELEASE(1);

    private final int priceCode;

    MovieType(int priceCode) {
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public static MovieType fromPriceCode(int priceCode) {
        for (MovieType type : MovieType.values()) {
            if (type.priceCode == priceCode) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown price code: " + priceCode);
    }
}
