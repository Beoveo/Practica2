package commands;

import paquete.Engine;

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
}