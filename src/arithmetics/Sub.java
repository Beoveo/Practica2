package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

public class Sub extends Arithmetics {
	public boolean execute(CPU cpu){
		if(cpu.sub()){ cpu.increaseProgramCounter(); return true;}
		else return false;
	}
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("SUB")){
			return null;
		} else return new Sub();
	}	
	
	public String toString(){ return "SUB";}
}
