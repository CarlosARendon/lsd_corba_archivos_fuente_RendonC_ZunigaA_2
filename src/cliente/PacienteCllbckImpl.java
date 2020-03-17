package cliente;

import servidorDeAlertas.sop_corba.*;

public class PacienteCllbckImpl extends PacienteCllbckIntPOA{

	/*@Override
	public String notificar(int numeroHabitacion, int frecCardiaca) {
		String mensaje = "El paciente en la habitación "+numeroHabitacion+" tiene una frecuencia Cardiaca anormal con un valor de "+frecCardiaca;
		System.out.println(mensaje);
		return mensaje;
	}*/

	@Override
	public void notificar(int numeroHabitacion, int frecCardiaca) {
		System.out.println("El paciente en la habitación "+numeroHabitacion+" tiene una frecuencia Cardiaca anormal con un valor de "+frecCardiaca);
	}
}