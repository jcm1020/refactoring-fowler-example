package ubu.gii.dass.refactoring;


public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private Price _price;



    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

/*    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Código de precio incorrecto");
        }
    }*/

    public double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }

	public String getTitle() {
		return _title;
	}

	public int getPriceCode() {
		return _price.getPriceCode();
	}

	// Establece el comportamiento de precio según el tipo
	public void setPriceCode(int priceCode) {
		switch (priceCode) {
			case REGULAR -> _price = new RegularPrice();
			case NEW_RELEASE -> _price = new NewReleasePrice();
			case CHILDRENS -> _price = new ChildrensPrice();
			default -> throw new IllegalArgumentException("Invalid price code");
		}
	}
}
