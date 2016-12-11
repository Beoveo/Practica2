package bytecodes;

import paquete.CPU;

/**
 * CLASS BYTECODE:
 * En la clase bytecode se parsean y ejecutan las instrucciones
 * en cada una de las clases de las instrucciones bytecode.
 */
abstract public class ByteCode {
	abstract public boolean execute(CPU cpu);
	abstract public ByteCode parse(String[] words);
}
