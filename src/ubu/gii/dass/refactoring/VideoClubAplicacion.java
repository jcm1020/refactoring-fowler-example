package ubu.gii.dass.refactoring;

import java.io.FileWriter;
import java.io.IOException;

public class VideoClubAplicacion {

	public static void main(String[] arg) {
		Movie m1 = new Movie("Sky Captain", 1);
		Movie m3 = new Movie("Accion Mutante", 0);
		Movie m4 = new Movie("Hermano Oso", 2);

		Customer c1 = new Customer("Manuel");

		Rental r1 = new Rental(m1, 5);
		Rental r2 = new Rental(m3, 1);
		Rental r3 = new Rental(m4, 10);

		c1.addRental(r1);
		c1.addRental(r2);
		c1.addRental(r3);

		System.out.println(c1.statement());
		
		String htmlContent = c1.htmlStatement();

        // Escribir el contenido HTML en un archivo
        try (FileWriter fileWriter = new FileWriter("rental_record.html")) {
            fileWriter.write(htmlContent);
            System.out.println("Contenido HTML ha sido escrito en rental_registro.html");
        } catch (IOException e) {
            System.err.println("Error escribiendo a archivo: " + e.getMessage());
        }

	}

}