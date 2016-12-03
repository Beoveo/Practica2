package paquete;

public class Replace extends Command {
	

	@Override
	public boolean execute(Engine engine) {
		return engine.replace(0);
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("RUN")) return null;
	else return new Run();
	}
	@Override
	public String textHelp() {
		return "RUN: Ejecuta el programa." +
	System.getProperty("line.separator");
	}
	public String toString(){
		return "RUN";
	}
	
	
	
	
	
}
