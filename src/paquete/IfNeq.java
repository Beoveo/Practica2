package paquete;

public class IfNeq extends ConditionalJumps {
	protected ByteCode parseAux(String string1, String string2){
		
		return new IfNeq();
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