package paquete;

public class Replace extends Command {
	@Override
	public boolean execute(Engine engine) {
		return engine.replace(0);
	}
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("REPLACE")) return null;
	else return new Run();
	}
	@Override
	public String textHelp() {
		return "REPLACE: Vacia el programa actual." +
	System.getProperty("line.separator");
	}
	public String toString(){
		return "REPLACE";
	}
	
	
	
	
	
}
