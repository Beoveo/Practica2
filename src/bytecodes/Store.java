package bytecodes;

import paquete.CPU;

public class Store extends ByteCodeOneParameter {
	public Store(){};
	public Store(int param){
		super(param);
	}
	
	public boolean execute(CPU cpu) {
		return cpu.write(super.param);
	}
	
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(string1 != "STORE" || param < 0) return null;
		else return new Load(param);
	}


	@Override
	protected String toStringAux() {
		return "STORE";
	}
	}
	
