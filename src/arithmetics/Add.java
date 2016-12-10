package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

public class Add extends Arithmetics{
	
	public boolean execute(CPU cpu){
		if(cpu.add()){ cpu.increaseProgramCounter(); return true; }
		else return false;
	}
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("ADD")){
			return null;
		} else return new Add();
	}	
	
	public String toString(){ return "ADD";}
}
