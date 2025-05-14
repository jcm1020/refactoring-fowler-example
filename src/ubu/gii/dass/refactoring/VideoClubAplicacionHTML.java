package ubu.gii.dass.refactoring;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VideoClubAplicacionHTML {

	public static void main(String[] arg) {
				
		Movie movie1 = new Movie("Movie 1", Movie.REGULAR);
        Movie movie2 = new Movie("Movie 2", Movie.NEW_RELEASE);

        Customer customer = new Customer("Jose");
		
		// Crear una lista de rentals
        List<Rental> rentals = new ArrayList<>();
        
        rentals.add(new Rental(movie1, 3));
        rentals.add(new Rental(movie2, 2));
        
        for (Rental rental : rentals) {
            customer.addRental(rental);
        }
        
        String htmlContent = customer.htmlStatement();
        
        System.out.println(htmlContent);

        // Escribir el contenido HTML en un archivo
        try (FileWriter fileWriter = new FileWriter("rental_registro.html")) {
            fileWriter.write(htmlContent);
            System.out.println("Contenido HTML ha sido escrito en rental_registro.html");
        } catch (IOException e) {
            System.err.println("Error escribiendo a archivo: " + e.getMessage());
        }

	}

}