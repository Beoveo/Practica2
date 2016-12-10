package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

public class Div extends Arithmetics {
	public boolean execute(CPU cpu){
		if(cpu.div()){ cpu.increaseProgramCounter(); return true; }
		else return false;
	}
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("DIV")){
			return null;
		} else return new Div();
	}	
	
	public String toString(){ return "DIV";}
}
