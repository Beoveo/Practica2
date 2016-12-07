package conditionals;

import bytecodes.ByteCode;

public class IfNeq extends ConditionalJumps{

	public IfNeq(){};
	public IfNeq(int pos){
		super(pos);
	}
	
	protected ByteCode parseAux(String string1, String string2){
		int pos = Integer.parseInt(string2);
		if(string1 != "IFNEQ" || pos < 0) return null;
		else return new Ifeq(pos);
	}
	
	protected boolean compare(int n1, int n2){
		if (n1 != n2) return true;
		return false;
	}
	
	protected String toStringAux(){
		return "IFNEQ";
	}
}
