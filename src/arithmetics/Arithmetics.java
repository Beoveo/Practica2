package arithmetics;

import bytecodes.ByteCode;
import paquete.CPU;

abstract public class Arithmetics extends ByteCode {
	abstract public boolean execute(CPU cpu);
	abstract public ByteCode parse(String[] words);
}
