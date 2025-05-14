package ubu.gii.dass.refactoring;

/**
* Tema  Refactorizaciones 
*
* Ejemplo de aplicaci�n de refactorizaciones. Actualizado para colecciones gen�ricas de java 1.5
*
* @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
* @version 1.1
* @see java.io.File
*
*/

import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();
	}

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	};	

	public String statement() {
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		for (Rental rental : _rentals) {
			// Delegamos el cálculo al objeto rental
			result.append("\t")
			      .append(rental.getMovie().getTitle())
			      .append("\t")
			      .append(rental.getCharge())
			      .append("\n");
		}
		// add footer lines		
		result.append("Amount owed is ").append(totalAmount()).append("\n");
		result.append("You earned ").append(totalFrecuentRenterPoints()).append(" frequent renter points");
		return result.toString();
	}

	public String htmlStatement() {
		StringBuilder result = new StringBuilder("<h1>Rental Record for " + getName() + "</h1>\n");
		result.append("<table border='1'>\n<tr><th>Movie Title</th><th>Amount</th></tr>\n");

		for (Rental rental : _rentals) {
			result.append("<tr><td>")
			      .append(rental.getMovie().getTitle())
			      .append("</td><td>")
			      .append(rental.getCharge())
			      .append("</td></tr>\n");
		}

		result.append("</table>\n");
		result.append("<p>Amount owed is <strong>")
		      .append(totalAmount())
		      .append("</strong></p>\n");
		result.append("<p>You earned <strong>")
		      .append(totalFrecuentRenterPoints())
		      .append("</strong> frequent renter points</p>");

		return result.toString();
	}

	private double totalAmount() {
		double total = 0;
		for (Rental rental : _rentals) {
			total += rental.getCharge();
		}
		return total;
	}

	private int totalFrecuentRenterPoints() {
		int frequentRenterPoints = 0;
		for (Rental rental : _rentals) {
			frequentRenterPoints = calculateFrequentRenterPoints(frequentRenterPoints, rental);
		}
		return frequentRenterPoints;
	}

/*	private double totalAmount() {
		double totalAmount = 0;
		for (Rental rental : _rentals) {
			// determine amounts for each line
			totalAmount += calculateAmount(rental);			 
		}
		return totalAmount;
	}*/

	private int calculateFrequentRenterPoints(int frequentRenterPoints, Rental rental) {
		// add frequent renter points
		frequentRenterPoints++;
		// add bonus for a two day new release rental
		if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& rental.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private double calculateAmount(Rental rental) {
		double thisAmount = 0;
		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			thisAmount = regularAmount(rental, thisAmount);
			break;
		case Movie.NEW_RELEASE:
			thisAmount = newreleaseAmount(rental, thisAmount);
			break;
		case Movie.CHILDRENS:
			thisAmount = childrensAmount(rental, thisAmount);
			break;
		}
		return thisAmount;
	}

	private double childrensAmount(Rental rental, double thisAmount) {
		thisAmount += 1.5;
		if (rental.getDaysRented() > 3)
			thisAmount += (rental.getDaysRented() - 3) * 1.5;
		return thisAmount;
	}

	private double newreleaseAmount(Rental rental, double thisAmount) {
		thisAmount += rental.getDaysRented() * 3;
		return thisAmount;
	}

	private double regularAmount(Rental rental, double thisAmount) {
		thisAmount += 2;
		if (rental.getDaysRented() > 2)
			thisAmount += (rental.getDaysRented() - 2) * 1.5;
		return thisAmount;
	}
}

