package servidorDeAlertas.sop_corba;

/**
* servidorDeAlertas/sop_corba/PacienteDTOHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gestionusuarios4.idl
* mi�rcoles 18 de marzo de 2020 07:11:46 PM COT
*/

public final class PacienteDTOHolder implements org.omg.CORBA.portable.Streamable
{
  public servidorDeAlertas.sop_corba.PacienteDTO value = null;

  public PacienteDTOHolder ()
  {
  }

  public PacienteDTOHolder (servidorDeAlertas.sop_corba.PacienteDTO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = servidorDeAlertas.sop_corba.PacienteDTOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    servidorDeAlertas.sop_corba.PacienteDTOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return servidorDeAlertas.sop_corba.PacienteDTOHelper.type ();
  }

}
