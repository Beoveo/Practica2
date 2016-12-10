package bytecodes;

import paquete.CPU;

public class Out extends ByteCode {
	public boolean execute(CPU cpu) {
		if(cpu.getSizeStack() > 0) {System.out.println(cpu.out());
		 cpu.increaseProgramCounter(); return true;}
		else return false;
	}
	public ByteCode parse(String[] words) {
		if(words.length !=1 || !words[0].equalsIgnoreCase("OUT")){
			return null;
		}else return new Out();
	}
	public String toString(){ return "OUT"; }
}
