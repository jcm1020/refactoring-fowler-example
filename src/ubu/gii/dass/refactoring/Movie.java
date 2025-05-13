package ubu.gii.dass.refactoring;
/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5.
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos López</A>
 * @version 1.1
 * @see java.io.File
 * 
 */


public class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;

	private String _title;
	private Price _price;

	public Movie(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode);
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

	public double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}

	public int getFrequentRenterPoints(int daysRented) {
		return _price.getFrequentRenterPoints(daysRented);
	}
}
