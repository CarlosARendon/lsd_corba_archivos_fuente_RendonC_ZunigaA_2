package servidorDeAlertas.servidor;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import servidorDeAlertas.sop_corba.*;

public class ServidorDeObjetos {

  public static void main(String args[]) {
    try{
	  //GesPacientesImpl objServant = new GesPacientesImpl();
      // crea e iniciia el ORB
      ORB orb = ORB.init(args, null);

      // obtiene la referencia del rootpoa & activate el POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      //*** crea el servant y lo registra con el ORB ***
      GestionAlertasImpl convref = new GestionAlertasImpl();
	  
      convref.obtenerLaRefRemotaDelServDeNotificaciones(args[1],args[3]);
      //*** obtiene la referencia del objeto desde el servant ***
      //GestionPacientesPOATie objPOATie = new GestionPacientesPOATie(convref);
      org.omg.CORBA.Object obj = 
      rootpoa.servant_to_reference(convref);
      GestionAlertasInt href = GestionAlertasIntHelper.narrow(obj);

      //GestionPacientes href = objPOATie._this(orb);	  
	  
      // obtiene la base del contexto de nombrado
      org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
      // Usa NamingContextExt el cual es parte de la especificacion 
      // Naming Service (INS).
      NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

      // *** Realiza el binding de la referencia de objeto en el N_S ***
      String name = "ges-alertas";
      NameComponent path[] = ncref.to_name( name );
      ncref.rebind(path, href);

      System.out.println("---------------------------------------------------");
      System.out.println(">> El Servidor de Alertas esta listo y esperando <<");
      System.out.println("---------------------------------------------------");
		
	  
      // esperan por las invocaciones desde los clientes
      orb.run();
    } 
	
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      } 
      System.out.println("Servidor: Saliendo ...");      
      
  } 
}
