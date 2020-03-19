package servidorDeAlertas.servidor;

import java.util.HashMap;
import java.util.ArrayList;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import servidorDeAlertas.sop_corba.*;
import servidorDeAlertas.servidor.*;
import servidorDeAlertas.sop_corba.PacienteDTOHolder;
import servidorDeNotificaciones.sop_corba.GestionNotificacionesOperations;
import servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.*;
import servidorDeNotificaciones.sop_corba.*;
import servidorDeNotificaciones.servidor.*;

public class GestionAlertasImpl extends GestionAlertasIntPOA {
	// Con hashmap
	HashMap<Integer, PacienteDTO> HMPacientes;// = new HashMap<>();

	// Sin hashmap
	// int posPaciente;
	// ArrayList<PacienteDTO> ListaPacientes;

	// variables de tipos y objetos remotos
	GestionNotificacionesOperations referenciaNotificaciones;
	ClsMensajeNotificacionDTO objDatosPaciente;
	ClsMensajeAlertaDTO objAlertas;

	public GestionAlertasImpl() {
		// posPaciente = 0;
		// ListaPacientes = new ArrayList<PacienteDTO>();
		HMPacientes = new HashMap<>();
	}

	//REGISTRAR PACIENTE
	public boolean registrarPaciente(servidorDeAlertas.sop_corba.PacienteDTO PacienteDTO) {
		boolean resultado = true;
		System.out.println("-----------------------");
		System.out.println("Registrando Paciente...");

		if (HMPacientes.size() == 5) {
			System.out.println("--------------------------------");
			System.out.println("La lista de pacientes esta llena");
			resultado = false;
			return resultado;
		} else {
			if (HMPacientes.get(PacienteDTO.numeroHabitacion) != null) {
				System.out.println("---------------------------------------");
				System.out.println("Ya existe un paciente en esa habitacion");
				resultado = false;
				return resultado;
			}
		}
		if (resultado) {
			HMPacientes.put(PacienteDTO.numeroHabitacion, PacienteDTO);
			// resultado = ListaPacientes.add(PacienteDTO);
			ClsMensajeNotificacionDTO objNotificacion = new ClsMensajeNotificacionDTO(PacienteDTO.nombre,
					PacienteDTO.apellido, PacienteDTO.numeroHabitacion, 1);
			referenciaNotificaciones.notificarRegistro(objNotificacion);
		}

		System.out.println("-------------------");
		System.out.println("Paciente registrado");
		return resultado;
	}

	//BUSCAR PACIENTE
	public boolean buscarPaciente(int numeroHabitacion, PacienteDTOHolder objPacienteBuscado) {
		System.out.println("--------------------");
		System.out.println("Buscando Paciente...");
		
		boolean siEsta = false;
		objPacienteBuscado.value = new PacienteDTO("", "", 0, 0, null);

		if (HMPacientes.get(numeroHabitacion) != null) {
			objPacienteBuscado.value = HMPacientes.get(numeroHabitacion);
			siEsta = true;
		}

		return siEsta;
	}

	public void modificarPaciente(int numeroHabitacion) {
		PacienteDTO objPaciente;
	}

	//ENVIAR INDICADORES PACIENTE
	public boolean enviarIndicadores(int numHabitacion, int frecuenciaCardiaca) {
		//System.out.println("bandera");
		float edad = 0;
		int habitacion = 0;
		servidorDeAlertas.sop_corba.PacienteCllbckInt pacCllbck = null;
		boolean estado = false;

		if (HMPacientes.get(numHabitacion).edad >= 13 && HMPacientes.get(numHabitacion).edad < 16) {
			if (frecuenciaCardiaca < 70 || frecuenciaCardiaca > 80) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("El Adolecente tiene una falla en su frecuencia cardiaca enviando la alerta");
				// REALIZA CALLBACK
				HMPacientes.get(numHabitacion).pacbck.notificar(numHabitacion, frecuenciaCardiaca);
				// ListaPacientes.get(posPaciente).pacbck.notificar(habitacion,
				// frecuenciaCardiaca);
				// REALIZA ENVIO DE ALERTAS
				// Objeto con los datos del paciente
				objDatosPaciente = new ClsMensajeNotificacionDTO(HMPacientes.get(numHabitacion).nombre,
						HMPacientes.get(numHabitacion).apellido, HMPacientes.get(numHabitacion).numeroHabitacion, 1);
				// Objeto con los datos de la alerta
				objAlertas = new ClsMensajeAlertaDTO(frecuenciaCardiaca);
				// Invocación de la alerta del paciente con sus datos y los indicadores
				referenciaNotificaciones.notificarAlerta(objDatosPaciente, objAlertas);
				estado = true;
			}
		} else if (HMPacientes.get(numHabitacion).edad >= 16) {
			if (frecuenciaCardiaca < 60 || frecuenciaCardiaca > 80) {
				System.out.println("----------------------------------------------------------------------");
				System.out.println("El Adulto tiene una falla en su frecuencia cardiaca enviando la alerta");
				// REALIZA CALLBACK
				HMPacientes.get(numHabitacion).pacbck.notificar(numHabitacion, frecuenciaCardiaca);
				// ListaPacientes.get(posPaciente).pacbck.notificar(habitacion,
				// frecuenciaCardiaca);
				// REALIZA ENVIO DE ALERTAS
				// Objeto con los datos del paciente
				objDatosPaciente = new ClsMensajeNotificacionDTO(HMPacientes.get(numHabitacion).nombre,
						HMPacientes.get(numHabitacion).apellido, HMPacientes.get(numHabitacion).numeroHabitacion, 1);
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
