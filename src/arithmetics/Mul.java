package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

public class Mul extends Arithmetics{
	
	public boolean execute(CPU cpu){
		if(cpu.mul()){ cpu.increaseProgramCounter(); return true; }
		else return false;
	}
	@Override
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("MUL")){
			return null;
		} else return new Mul();
	}
	
	public String toString(){ return "MUL";}
	
}
