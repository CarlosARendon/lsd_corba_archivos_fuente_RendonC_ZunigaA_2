package servidorDeAlertas.sop_corba;


/**
* servidorDeAlertas/sop_corba/PacienteDTO.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gestionusuarios4.idl
* mi�rcoles 18 de marzo de 2020 07:11:46 PM COT
*/

public final class PacienteDTO implements org.omg.CORBA.portable.IDLEntity
{
  public String nombre = null;
  public String apellido = null;
  public int numeroHabitacion = (int)0;
  public float edad = (float)0;
  public servidorDeAlertas.sop_corba.PacienteCllbckInt pacbck = null;

  public PacienteDTO ()
  {
  } // ctor

  public PacienteDTO (String _nombre, String _apellido, int _numeroHabitacion, float _edad, servidorDeAlertas.sop_corba.PacienteCllbckInt _pacbck)
  {
    nombre = _nombre;
    apellido = _apellido;
    numeroHabitacion = _numeroHabitacion;
    edad = _edad;
    pacbck = _pacbck;
  } // ctor

} // class PacienteDTO
