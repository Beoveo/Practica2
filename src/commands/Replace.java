package commands;

import paquete.Engine;

public class Replace extends Command {
	protected int param;
	

	public Replace() {}
	public Replace(int p){
		this.param = p;
	}

	@Override
	public boolean execute(Engine engine) {
		return engine.replace(this.param);
	}
	
	public Command parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("REPLACE")|| param < 0) return null;
		else { return new Replace(param);}
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length!=2) return null;
	else return parseAux(s[0], s[1]);
	}
	@Override
	public String textHelp() {
		return "REPLACE: Ejecuta el programa." +
	System.getProperty("line.separator");
	}
	public String toString(){
		return "REPLACE";
	}
	
	
}
