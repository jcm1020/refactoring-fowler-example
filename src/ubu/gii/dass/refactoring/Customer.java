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
	}

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
		result.append("Amount owed is ").append(totalAmount()).append("\n");
		result.append("You earned ").append(totalFrequentRenterPoints()).append(" frequent renter points");
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
		      .append(totalFrequentRenterPoints())
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

	private int totalFrequentRenterPoints() {
		int total = 0;
		for (Rental rental : _rentals) {
			total += rental.getFrequentRenterPoints();
		}
		return total;
	}
}
