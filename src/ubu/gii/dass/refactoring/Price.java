package ubu.gii.dass.refactoring;

// Estrategia base para calcular precios y puntos
abstract class Price {
	abstract int getPriceCode();
	abstract double getCharge(int daysRented);

	public int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}
