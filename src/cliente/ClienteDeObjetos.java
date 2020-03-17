package cliente;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import cliente.PacienteCllbckImpl;
import servidorDeAlertas.sop_corba.GestionAlertasInt;
import servidorDeAlertas.sop_corba.GestionAlertasIntHelper;
import servidorDeAlertas.sop_corba.PacienteCllbckInt;
import servidorDeAlertas.sop_corba.PacienteCllbckIntHelper;
import servidorDeAlertas.sop_corba.PacienteDTO;
import servidorDeAlertas.sop_corba.PacienteDTOHolder;

public class ClienteDeObjetos {
    // *** Atributo estático quecontrola los metodos en el servidor de alertas ***
    static GestionAlertasInt refPacientes;

    public static void main(String args[]) {
        try {

            String[] v = new String[4];

            if (args.length != 4) {
                System.out.println("Debes especificar todos los argumentos");
                return;
            }
            v[0] = args[0];
            v[1] = args[1];
            v[2] = args[2];
            v[3] = args[3];
            // crea e inicia el ORB*
            ORB orb = ORB.init(v, null);

            // obtiene la base del naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

            rootPOA.the_POAManager().activate();

            PacienteCllbckImpl pacbck = new PacienteCllbckImpl();

            org.omg.CORBA.Object ref1 = rootPOA.servant_to_reference(pacbck);
            PacienteCllbckInt href1 = PacienteCllbckIntHelper.narrow(ref1);

            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "ges-alertas";
            refPacientes = GestionAlertasIntHelper.narrow(ncRef.resolve_str(name));

            System.out.println("Obtenido el manejador sobre el servidor de objetos: " + refPacientes);
            int rta = 0;
            do {
                rta = menu();

                switch (rta) {
                    case 1:

                        System.out.println(" === Registro del Paciente ===");

                        // System.out.println(" Digite el numero de la habitacion del paciente: ");
                        int noHabitacion = UtilConsolaCliente.leerHabitacion();

                        System.out.println(" Digite los nombres del paciente: ");
                        String nombres = UtilConsolaCliente.leerCadena();

                        System.out.println(" Digite los apellidos del paciente: ");
                        String apellidos = UtilConsolaCliente.leerCadena();

                        System.out.println(" Digite la edad del paciente: ");
                        float edad = UtilConsolaCliente.leerEdad();

                        PacienteDTO nuevoPaciente = new PacienteDTO(nombres, apellidos, noHabitacion, edad, href1);
                        PacienteCllbckImpl cb = new PacienteCllbckImpl();
                        int frecuenciaCardiaca = 0;
                        boolean bandera = refPacientes.registrarPaciente(nuevoPaciente);
                        if (bandera) {
                            System.out.println("Paciente registrado con exito.");
                            System.out.println(" Digite la frecuencia cardiaca del paciente: ");
                            frecuenciaCardiaca = UtilConsolaCliente.leerEntero();
                            // System.out.println(refPacientes.enviarIndicadores(noHabitacion,frecuenciaCardiaca));
                            refPacientes.enviarIndicadores(noHabitacion, frecuenciaCardiaca);
                        } else {
                            System.out.println(
                                    "Limite de pacientes alcanzado o numero de habitacion repetida. No fue posible registrar el paciente.");
                        }

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }

            } while (rta != 4);

        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    public static int menu() {

        System.out.println(" ==== MENU ====");
        System.out.println(" 1- Registrar Paciente");
        System.out.println(" 2- Buscar Paciente");
        System.out.println(" 3- Modificar Paciente");
        System.out.println(" 4- Salir");
        int rta = UtilConsolaCliente.leerEntero();

        return rta;

    }

    public static void mostrarVector(String[] vectorEvaludores) {

        if (vectorEvaludores != null) {
            for (int i = 0; i < vectorEvaludores.length; i++) {
                System.out.println("Evaluador #" + (i + 1) + ": " + vectorEvaludores[i]);
            }
        } else {
            System.out.println("No hay anteproyectos registrados");
        }

    }

}