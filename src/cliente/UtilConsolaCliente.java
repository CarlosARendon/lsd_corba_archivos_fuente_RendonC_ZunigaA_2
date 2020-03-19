package cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UtilConsolaCliente {
	public static int leerEntero() {
		String linea = "";
		int opcion = 0;
		boolean valido = false;
		do {
			try {
				System.out.print("==> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				linea = br.readLine();
				opcion = Integer.parseInt(linea);
				valido = true;
			} catch (Exception e) {
				System.out.println("--------------------");
				System.out.println("Error intente nuevamente...");
				valido = false;
			}
		} while (!valido);

		return opcion;

	}

	public static int leerHabitacion() {
		String linea = "";
		int numHab = 0;
		boolean valido = false;
		do {
			try {
				System.out.print("Ingrese el numero de habitacion: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				linea = br.readLine();
				numHab = Integer.parseInt(linea);
				if (numHab >= 100 && numHab < 1000) {
					valido = true;
				} else {
					System.out.println("--------------------");
					System.out.println("==> Error, ingrese un numero entre 100 y 999 <==");
					valido = false;
				}
			} catch (Exception e) {
				System.out.println("--------------------");
				System.out.println("==> Error, ingrese un numero de habitacion valido <==");
				valido = false;
			}
		} while (!valido);

		return numHab;

	}

	// 1 AÑO = 52 SEMANAS
	// RN 0-6 SEMANAS
	// INFANTE 7-51 SEMANAS ó 7-1 año
	// 7 semnas -> 0.13 respecto a 1 año

	public static float leerFechaNaciemiento() {
		float edad = 0;

		String fecha = leerCadena();
		// 01/01/2000
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fecha, fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		//periodo.
		System.out.println("Tu edad es: " +periodo.getYears()+ "anios, "+periodo.getMonths()+" meses y "+periodo.getDays()+" dias");
		if (periodo.getYears()!=0) {
			edad = periodo.getYears();
			return edad;
		} else if(periodo.getMonths()!=0) {
			edad = periodo.getMonths();
			//regla de 3 para saber los meses que equivalen a un año
			return edad;
		} else {
			edad = periodo.getDays();
			//regla de 3 para saber los dias que equivalen a un año
			return edad;
		}
	}

	public static float leerEdad() {
		String linea = "";
		float edad = 0;
		boolean valido = false;
		do {
			System.out.println("El paciente es menor de 12 meses? S/N");
			// opc = leerCadena();
			if (leerCadena().equalsIgnoreCase("s")) {
				try {
					System.out.print("Ingrese el numero de semanas de edad del paciente: ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					linea = br.readLine();
					edad = Float.parseFloat(linea);
					edad = edad / 52;
					System.out.println("Imprimiendo edad: " + edad);
					// 1 año equivale a 52 semanas, cada valor introducido en semanas se divide
					// entre 52
					// para valores menores a 52 semanas sera un valor entre 0 y 0.98 (51)
					if (edad > 0 && edad <= 1) {
						valido = true;
					} else {
						System.out.println("--------------------");
						System.out.println("==> Error, ingrese un valor entre 0 y 52 <==");
						valido = false;
					}
				} catch (Exception e) {
					System.out.println("--------------------");
					System.out.println("==> Error, ingrese un valor para la edad valido <==");
					valido = false;
				}

			} else {
				try {
					System.out.print("Ingrese los anios de edad del paciente: ");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					linea = br.readLine();
					edad = Float.parseFloat(linea);
					// System.out.println("Imprimiendo edad: " +edad);
					if (edad > 0 && edad <= 120) {
						valido = true;
					} else {
						System.out.println("--------------------");
						System.out.println("==> Error, ingrese un valor entre 0 y 120 <==");
						valido = false;
					}
				} catch (Exception e) {
					System.out.println("--------------------");
					System.out.println("==> Error, ingrese un valor para la edad valido <==");
					valido = false;
				}
			}

		} while (!valido);

		return edad;

	}

	public static String leerCadena() {
		String linea = "";
		boolean valido = false;
		do {
			try {
				System.out.print("==> ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				linea = br.readLine();
				valido = true;
			} catch (Exception e) {
				System.out.println("--------------------");
				System.out.println("Error intente nuevamente...");
				valido = false;
			}
		} while (!valido);

		return linea;

	}

}
