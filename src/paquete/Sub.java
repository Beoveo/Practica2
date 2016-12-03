package paquete;

public class Sub extends Arithmetics {
	public boolean execute(CPU cpu){
		return cpu.sub();
	}
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("SUB")){
			return null;
		} else return new Sub();
	}	
}
