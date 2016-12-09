package bytecodes;

import paquete.CPU;

public class Load extends ByteCodeOneParameter{
	
	public Load(){};
	public Load(int param){
		super(param);
	}
	
	public boolean execute(CPU cpu) {
		return cpu.load(super.param);
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("LOAD") || param < 0) return null;
		else return new Load(param);
	}


	@Override
	protected String toStringAux() {
		return "LOAD";
	}
}
