package servidorDeAlertas.sop_corba;


/**
* servidorDeAlertas/sop_corba/_PacienteCllbckIntStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from gestionusuarios3.idl
* s�bado 14 de marzo de 2020 06:33:22 PM COT
*/

public class _PacienteCllbckIntStub extends org.omg.CORBA.portable.ObjectImpl implements servidorDeAlertas.sop_corba.PacienteCllbckInt
{

  public void notificar (int numeroHabitacion, int frecCardiaca)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("notificar", true);
                $out.write_long (numeroHabitacion);
                $out.write_long (frecCardiaca);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                notificar (numeroHabitacion, frecCardiaca        );
            } finally {
                _releaseReply ($in);
            }
  } // notificar

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sop_corba/PacienteCllbckInt:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _PacienteCllbckIntStub
