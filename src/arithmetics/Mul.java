package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

public class Mul extends Arithmetics{
	
	public boolean execute(CPU cpu){
		return cpu.mul();
	}
	@Override
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("MUL")){
			return null;
		} else return new Mul();
	}
	
}
