package conditionals;

import bytecodes.ByteCode;
import bytecodes.ByteCodeOneParameter;
import paquete.CPU;

public class GoTo extends ByteCodeOneParameter{
	public GoTo(){};
	public GoTo(int pos){
		super(pos);
	}
	
	protected ByteCode parseAux(String string1, String string2){
		int pos = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("GOTO") || pos < 0) return null;
		else return new GoTo(pos);
	}
	
	protected String toStringAux(){
		return "GOTO";
	}
	@Override
	public boolean execute(CPU cpu) {
		cpu.setProgramCounter(super.param);
		return true;
	}
	
	
}
