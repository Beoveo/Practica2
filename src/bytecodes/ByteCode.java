package bytecodes;

import paquete.CPU;

/**
 * CLASS BYTECODE:
 * En la clase bytecode tratamos la gestion de las operaciones 
 * que podemos hacer para tratar las diferentes instrucciones.
 */

abstract public class ByteCode {
	abstract public boolean execute(CPU cpu);
	abstract public ByteCode parse(String[] words);
}
