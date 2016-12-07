package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

public class Div extends Arithmetics {
	public boolean execute(CPU cpu){
		return cpu.div();
	}
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("DIV")){
			return null;
		} else return new Div();
	}	
}
