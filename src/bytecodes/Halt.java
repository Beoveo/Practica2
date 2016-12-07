package bytecodes;


import paquete.CPU;

public class Halt extends ByteCode {
	public boolean execute(CPU cpu) {
		cpu.halt();
		return true;
	}
	public ByteCode parse(String[] words) {
		if(words.length !=1 || !words[0].equalsIgnoreCase("HALT")){
			return null;
		}else return new Halt();
	}
	public String toString(){ return "HALT"; }
}

