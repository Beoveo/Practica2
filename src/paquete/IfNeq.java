package paquete;

public class IfNeq extends ConditionalJumps {
	protected ByteCode parseAux(String string1, String string2){
		//Asi le pasa directamente los datos??
		if(string1.equalsIgnoreCase("IFNEQ")) return (new IfNeq());
		else return null;
	}
	
	protected boolean compare(int n1, int n2){
		boolean OK = false;
		if (n1 != n2) OK = true;
		return OK;
	}
	
	protected String toStringAux(){
		return "IFNEQ";
	}

}
