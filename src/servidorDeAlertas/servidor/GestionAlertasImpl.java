package servidorDeAlertas.servidor;

import java.util.ArrayList;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import servidorDeAlertas.sop_corba.*;
import servidorDeAlertas.servidor.*;
import servidorDeNotificaciones.sop_corba.GestionNotificacionesOperations;
import servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.*;
import servidorDeNotificaciones.sop_corba.*;
import servidorDeNotificaciones.servidor.*;

public class GestionAlertasImpl extends GestionAlertasIntPOA {
	int posPaciente;
	ArrayList<PacienteDTO> ListaPacientes;
	GestionNotificacionesOperations referenciaNotificaciones;
	ClsMensajeNotificacionDTO objDatosPaciente;
	ClsMensajeAlertaDTO objAlertas;

	public GestionAlertasImpl() {
		posPaciente = 0;
		ListaPacientes = new ArrayList<PacienteDTO>();
	}

	public boolean registrarPaciente(servidorDeAlertas.sop_corba.PacienteDTO PacienteDTO) {
		boolean resultado = true;
		System.out.println("-----------------------");
		System.out.println("Registrando Paciente...");

		// if(ListaPacientes.size() >= 4){
		if (ListaPacientes.size() == 5) {
			System.out.println("--------------------------------");
			System.out.println("La lista de pacientes esta llena");
			resultado = false;
			return resultado;
		} else {
			for (int i = 0; i < ListaPacientes.size(); i++) {
				if (ListaPacientes.get(i).numeroHabitacion == PacienteDTO.numeroHabitacion) {
					System.out.println("---------------------------------------");
					System.out.println("Ya existe un paciente en esa habitacion");
					resultado = false;
					return resultado;
					// break;
				}

			}
			if (resultado) {
				resultado = ListaPacientes.add(PacienteDTO);
				ClsMensajeNotificacionDTO objNotificacion = new ClsMensajeNotificacionDTO(PacienteDTO.nombre,
						PacienteDTO.apellido, PacienteDTO.numeroHabitacion, 1);
				referenciaNotificaciones.notificarRegistro(objNotificacion);
			}

		}
		System.out.println("----------------------");
		System.out.println("Paciente registrado...");
		return resultado;
	}

	public boolean enviarIndicadores(int numHabitacion, int frecuenciaCardiaca) {
		float edad = 0;
		int habitacion = 0;
		servidorDeAlertas.sop_corba.PacienteCllbckInt pacCllbck = null;
		boolean estado = false;

		for (int i = 0; i < ListaPacientes.size(); i++) {
			if (ListaPacientes.get(i).numeroHabitacion == numHabitacion) {
				habitacion = ListaPacientes.get(i).numeroHabitacion;
				edad = ListaPacientes.get(i).edad;
				posPaciente = i;
				break;
			}
		}

		if (edad >= 13 && edad < 16) {
			if (frecuenciaCardiaca < 70 || frecuenciaCardiaca > 80) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("El Adolecente tiene una falla en su frecuencia cardiaca enviando la alerta");
				// REALIZA CALLBACK
				ListaPacientes.get(posPaciente).pacbck.notificar(habitacion, frecuenciaCardiaca);
				// REALIZA ENVIO DE ALERTAS
				// Objeto con los datos del paciente
				objDatosPaciente = new ClsMensajeNotificacionDTO(ListaPacientes.get(posPaciente).nombre,
						ListaPacientes.get(posPaciente).apellido, ListaPacientes.get(posPaciente).numeroHabitacion, 1);
				// Objeto con los datos de la alerta
				objAlertas = new ClsMensajeAlertaDTO(frecuenciaCardiaca);
				// Invocación de la alerta del paciente con sus datos y los indicadores
				referenciaNotificaciones.notificarAlerta(objDatosPaciente, objAlertas);
				estado = true;
			}
		} else if (edad >= 16) {
			if (frecuenciaCardiaca < 60 || frecuenciaCardiaca > 80) {
				System.out.println("----------------------------------------------------------------------");
				System.out.println("El Adulto tiene una falla en su frecuencia cardiaca enviando la alerta");
				// REALIZA CALLBACK
				ListaPacientes.get(posPaciente).pacbck.notificar(habitacion, frecuenciaCardiaca);
				// REALIZA ENVIO DE ALERTAS
				// Objeto con los datos del paciente
				objDatosPaciente = new ClsMensajeNotificacionDTO(ListaPacientes.get(posPaciente).nombre,
						ListaPacientes.get(posPaciente).apellido, ListaPacientes.get(posPaciente).numeroHabitacion, 1);
				// Objeto con los datos de la alerta
				objAlertas = new ClsMensajeAlertaDTO(frecuenciaCardiaca);
				// Invocación de la alerta del paciente con sus datos y los indicadores
				referenciaNotificaciones.notificarAlerta(objDatosPaciente, objAlertas);
				estado = true;
			}
		}

		return estado;
	}

	public void obtenerLaRefRemotaDelServDeNotificaciones(String direccionIPNS, String puertoNS) {
		try {
			String[] vec = new String[4];
			vec[0] = "-ORBInitialPort";
			vec[1] = direccionIPNS;
			vec[2] = "-ORBInitialPort";
			vec[3] = puertoNS;

			ORB orb = ORB.init(vec, null);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			// *** Resuelve la referencia del objeto en el N_S ***
			String name = "objNotificaciones";
			referenciaNotificaciones = GestionNotificacionesHelper.narrow(ncRef.resolve_str(name));

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}

}
