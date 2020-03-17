package servidorDeAlertas.sop_corba;


/**
* servidorDeAlertas/sop_corba/GestionAlertasIntPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gestionusuarios3.idl
* s�bado 14 de marzo de 2020 06:33:23 PM COT
*/

public abstract class GestionAlertasIntPOA extends org.omg.PortableServer.Servant
 implements servidorDeAlertas.sop_corba.GestionAlertasIntOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registrarPaciente", new java.lang.Integer (0));
    _methods.put ("enviarIndicadores", new java.lang.Integer (1));
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
       case 0:  // sop_corba/GestionAlertasInt/registrarPaciente
       {
         servidorDeAlertas.sop_corba.PacienteDTO pacientedto = servidorDeAlertas.sop_corba.PacienteDTOHelper.read (in);
         boolean $result = false;
         $result = this.registrarPaciente (pacientedto);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // sop_corba/GestionAlertasInt/enviarIndicadores
       {
         int numHabitacion = in.read_long ();
         int frecuenciaCardiaca = in.read_long ();
         boolean $result = false;
         $result = this.enviarIndicadores (numHabitacion, frecuenciaCardiaca);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/GestionAlertasInt:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public GestionAlertasInt _this() 
  {
    return GestionAlertasIntHelper.narrow(
    super._this_object());
  }

  public GestionAlertasInt _this(org.omg.CORBA.ORB orb) 
  {
    return GestionAlertasIntHelper.narrow(
    super._this_object(orb));
  }


} // class GestionAlertasIntPOA
