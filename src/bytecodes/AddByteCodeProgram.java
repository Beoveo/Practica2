package bytecodes;

import commands.Command;
import paquete.Engine;

public class AddByteCodeProgram extends Command {
	
	@Override
	public boolean execute(Engine engine) {
		return engine.readByteCodeProgram();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("BYTECODE")) return null;
	else return new AddByteCodeProgram();
	}
	@Override
	public String textHelp() {
		return " BYTECODE: Permite introducir un programa " +
	System.getProperty("line.separator");
	}
	public String toString(){
		return "BYTECODE";
	}
}
