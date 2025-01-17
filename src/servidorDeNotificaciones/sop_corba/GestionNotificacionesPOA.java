package servidorDeNotificaciones.sop_corba;


/**
* servidorDeNotificaciones/sop_corba/GestionNotificacionesPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from notificaciones2.idl
* lunes 16 de marzo de 2020 08:32:54 PM COT
*/

public abstract class GestionNotificacionesPOA extends org.omg.PortableServer.Servant
 implements servidorDeNotificaciones.sop_corba.GestionNotificacionesOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("notificarRegistro", new java.lang.Integer (0));
    _methods.put ("notificarAlerta", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // sop_corba/GestionNotificaciones/notificarRegistro
       {
         servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.ClsMensajeNotificacionDTO objNotificacion = servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.ClsMensajeNotificacionDTOHelper.read (in);
         this.notificarRegistro (objNotificacion);
         out = $rh.createReply();
         break;
       }

       case 1:  // sop_corba/GestionNotificaciones/notificarAlerta
       {
         servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.ClsMensajeNotificacionDTO objNotificacion = servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.ClsMensajeNotificacionDTOHelper.read (in);
         servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.ClsMensajeAlertaDTO objAlerta = servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage.ClsMensajeAlertaDTOHelper.read (in);
         this.notificarAlerta (objNotificacion, objAlerta);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/GestionNotificaciones:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public GestionNotificaciones _this() 
  {
    return GestionNotificacionesHelper.narrow(
    super._this_object());
  }

  public GestionNotificaciones _this(org.omg.CORBA.ORB orb) 
  {
    return GestionNotificacionesHelper.narrow(
    super._this_object(orb));
  }


} // class GestionNotificacionesPOA
