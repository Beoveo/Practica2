package bytecodes;

import paquete.CPU;

public class Push extends ByteCodeOneParameter {
	
	public Push(){};
	public Push(int param){
		super(param);
	}
	
	public boolean execute(CPU cpu) {
		if(cpu.push(super.param)){ cpu.increaseProgramCounter(); return true;}
		else return false;
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("PUSH") || param < 0) return null;
		else return new Push(param);
	}


	@Override
	protected String toStringAux() {
		return "PUSH";
	}
}
