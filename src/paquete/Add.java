package paquete;

public class Add extends ByteCode{
	
	public boolean execute(CPU cpu){
		return cpu.add();
	}
	public ByteCode parse(String[] words){
		//Aqui seria words[0] o words[1]?
		if(words.length !=2 || words.length <= 0 || !words[0].equalsIgnoreCase("ADD")){
			return null;
		} else{
			words[0] = words[0].toUpperCase();
			if(words.length == 1) return new Add();
			
	/*if (s.length!=1 || !s[0].equalsIgnoreCase("HELP")) return null;
	else return new Help();*/
	}

	}
}
