package paquete;

/** 
 * 
 * CLASS COMMAND:
 * Esta clase maneja los comandos de la maquina virtual.
 * Dentro de dichos comandos estan los bytecodes almacenados.
 * Ademas se encarga de ejecutar los comandos llamando a engine.
 *
 */

abstract public class Command {
	abstract public boolean execute(Engine engine);
	abstract public Command parse(String[] s);
	abstract public String textHelp();
	
	/*public boolean executeCommand(Engine engine){  
		boolean ejecutado = false;
	switch (this.comando){
	
		case HELP:
			ejecutado = engine.help();
			break;
		case QUIT:
			ejecutado = engine.endExecution();
			break;
		case NEWINST:
			if(this.byteCode == null)ejecutado = false;
			else ejecutado = engine.addBcInstruction(this.byteCode); // En addBcInstruccion se llama a un metodo que ya aumenta el contador.
			break;
		case RUN:
			ejecutado = engine.executeCommandRun();
			break;
		case RESET:
			ejecutado = engine.resetProgram();
			break;
		case REPLACE:
			ejecutado = engine.replace(this.replace);
			break;
	
		}
		return ejecutado;
	}
	*/
	
	public String toString() {
		String s = "";
		s= "Comienza la ejecucion de " + this.comando + "  ";
		if(this.byteCode != null){
			s = s + byteCode.toString();
		}
		return s;
	}
	
}