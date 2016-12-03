package paquete;

public class Quit extends Command {
	@Override
	public boolean execute(Engine engine) {
		return engine.endExecution();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("QUIT")) return null;
	else return new Run();
	}
	@Override
	public String textHelp() {
		return "QUIT: Cierra la aplicacion." +
	System.getProperty("line.separator");
	}
	public String toString(){
		return "QUIT";
	}
	
	
	
	
	
}
