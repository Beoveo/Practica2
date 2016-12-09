package commands;

import paquete.Engine;

public class Reset extends Command {
	
	 
	public boolean execute(Engine engine) {
		return engine.resetProgram();
	}
		
	@Override
	public Command parse(String[] s) {
	if (s.length!=1 || !s[0].equalsIgnoreCase("RESET")) return null;
		else return new Reset();
	}
	@Override
	public String textHelp() {
	 return " RESET: Permite resetear un programa " +
		 System.getProperty("line.separator");
	}
	public String toString(){
		return "RESET";
	}
	
	
}
