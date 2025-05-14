package ubu.gii.dass.refactoring;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tema Refactorizaciones
 * 
 * Ejemplo de aplicación de refactorizaciones. Actualizado para colecciones
 * genéricas de java 1.5
 * 
 * @author M. Fowler y <A HREF="mailto:clopezno@ubu.es">Carlos L�pez</A>
 * @version 1.1

 * 
 */
public class VideoClubTest {
	protected Movie m0, m11, m12, m2, movie1, movie2;
	protected Customer c1, customer;
	
	@Before
	public void setUp() {
		m11 = new Movie("Sky Captain", 1);
		m12 = new Movie("Alejandro Magno", 1);
		m0 = new Movie("Accion Mutante", 0);
		m2 = new Movie("Hermano Oso", 2);
		
		movie1 = new Movie("Movie 1", Movie.REGULAR);
        movie2 = new Movie("Movie 2", Movie.NEW_RELEASE);

		c1 = new Customer("Manuel");
		
		customer = new Customer("John Doe");
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public void testAlquiler() {

		Rental r1 = new Rental(m11, 5);
		Rental r2 = new Rental(m0, 1);
		Rental r3 = new Rental(m2, 10);

		c1.addRental(r1);
		c1.addRental(r2);
		c1.addRental(r3);

		String salida = c1.statement();

		String salidaEsperada = new String("Rental Record for Manuel\n"
				+ "\tSky Captain\t15.0\n" + "\tAccion Mutante\t2.0\n"
				+ "\tHermano Oso\t12.0\n" + "Amount owed is 29.0\n"
				+ "You earned 4 frequent renter points");

		assertTrue("Calcula mal el alquiler", salidaEsperada.equals(salida));

	}
	
	@Test
    public void testHtmlStatement() {
        // Crear una lista de rentals
        List<Rental> rentals = new ArrayList<>();
        
        rentals.add(new Rental(movie1, 3));
        rentals.add(new Rental(movie2, 2));

        // Crear un customer y agregar los rentals
        
        for (Rental rental : rentals) {
            customer.addRental(rental);
        }

        // Llamar al método htmlStatement y verificar el resultado
        String expectedHtml = "<h1>Rental Record for John Doe</h1>\n" +
                              "<table border='1'>\n" +
                              "<tr><th>Movie Title</th><th>Amount</th></tr>\n" +
                              "<tr><td>Movie 1</td><td>3.5</td></tr>\n" +
                              "<tr><td>Movie 2</td><td>6.0</td></tr>\n" +
                              "</table>\n" +
                              "<p>Amount owed is <strong>9.5</strong></p>\n" +
                              "<p>You earned <strong>3</strong> frequent renter points</p>";
        
        assertEquals(expectedHtml, customer.htmlStatement());
    }

}
