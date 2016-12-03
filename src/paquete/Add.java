package paquete;

public class Add extends Arithmetics{
	
	public boolean execute(CPU cpu){
		return cpu.add();
	}
	public ByteCode parse(String[] words){
		if(words.length !=1 || !words[0].equalsIgnoreCase("ADD")){
			return null;
		} else return new Add();
	}	
}
