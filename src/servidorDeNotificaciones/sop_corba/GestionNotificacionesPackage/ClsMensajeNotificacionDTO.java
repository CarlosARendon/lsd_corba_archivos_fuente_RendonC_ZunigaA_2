package servidorDeNotificaciones.sop_corba.GestionNotificacionesPackage;


/**
* servidorDeNotificaciones/sop_corba/GestionNotificacionesPackage/ClsMensajeNotificacionDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from notificaciones2.idl
* lunes 16 de marzo de 2020 08:32:54 PM COT
*/

public final class ClsMensajeNotificacionDTO implements org.omg.CORBA.portable.IDLEntity
{
  public String nombre = null;
  public String apellido = null;
  public int numeroHabitacion = (int)0;
  public int estado = (int)0;

  public ClsMensajeNotificacionDTO ()
  {
  } // ctor

  public ClsMensajeNotificacionDTO (String _nombre, String _apellido, int _numeroHabitacion, int _estado)
  {
    nombre = _nombre;
    apellido = _apellido;
    numeroHabitacion = _numeroHabitacion;
    estado = _estado;
  } // ctor

} // class ClsMensajeNotificacionDTO
