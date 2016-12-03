package paquete;

/**
 * 
 * CLASS COMMAND_PARSER:
 * Esta clase posee unicamente un metodo estatico.
 * Parsea el comando que se le introduzca.
 *
 */

public class CommandParser {
	private final static Command[] commands = {new Help(),new Quit(), new Reset(),
		 new Replace(),new Run(),new AddByteCodeProgram()};
	
	
	public static Command parse(String line) { 
		line = line.trim(); // elimina los caracteres en blanco iniciales
		String []words = line.split(" +"); // admite varios blancos separando las palabras.
		if (words.length == 0) return null; 
		else {
			words[0] = words[0].toLowerCase(); 
				return null;
			}
		boolean found = false;
		int i=0;
		Command c = null;
		while (i < commands.length && !found){
		c = commands[i].parse(words);
		if (c!=null) found=true;
			else i++;
		 }
		 return c;
		 }
		
		}
	}
	
	