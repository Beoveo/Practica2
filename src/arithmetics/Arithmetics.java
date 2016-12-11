package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

/**
 * CLASE ARITHMETICS:
 * Clase abstracta que contiene los metodos abstractos
 * que heredan las instrucciones bytecode aritmeticas.
 */
abstract public class Arithmetics extends ByteCode {
	abstract public boolean execute(CPU cpu);
	abstract public ByteCode parse(String[] words);
}
