package paquete;

public class Help extends Command{
	@Override
	public boolean execute(Engine engine) {
		return engine.help();
	}
	
	@Override
	public Command parse(String[] s) {
		if (s.length!=1 || !s[0].equalsIgnoreCase("HELP")) return null;
	else return new Help();
	}
	@Override
	public String textHelp() {
		return "HELP: Muestra esta ayuda." +
	System.getProperty("line.separator");
	}
	public String toString(){
		return "HELP";
	}
	
}
