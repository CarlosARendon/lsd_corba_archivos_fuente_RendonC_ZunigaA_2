package servidorDeNotificaciones.servidor;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import servidorDeNotificaciones.*;
import servidorDeNotificaciones.sop_corba.*;

public class ServidorDeObjetos {

  public static void main(String args[]) {
    try{
      // crea e iniciia el ORB
      ORB orb = ORB.init(args, null);

      // obtiene la referencia del rootpoa & activate el POAManager
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootPOA.the_POAManager().activate();

      //*** crea el servant y lo registra con el ORB ***
      GesNotificacionesImpl convref = new GesNotificacionesImpl();
       
      //*** obtiene la referencia del objeto desde el servant ***
      ////GestionNotificacionesPOATie objPOATie = new GestionNotificacionesPOATie(convref);
      org.omg.CORBA.Object obj = rootPOA.servant_to_reference(convref);
      GestionNotificaciones href = GestionNotificacionesHelper.narrow(obj);
      
      //GestionNotificaciones href = objPOATie._this(orb);
	  
      // obtiene la base del contexto de nombrado
      org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
      // Usa NamingContextExt el cual es parte de la especificacion 
      // Naming Service (INS).
      NamingContextExt ncref = NamingContextExtHelper.narrow(objref);

      // *** Realiza el binding de la referencia de objeto en el N_S ***
      String name = "objNotificaciones";
      NameComponent path[] = ncref.to_name( name );
      ncref.rebind(path, href);

      System.out.println("La referencia remota del servidor de notificaciones ..."+href);
      System.out.println("El Servidor Notificaciones esta listo y esperando ...");

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