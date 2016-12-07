package conditionals;

import bytecodes.ByteCodeOneParameter;
import paquete.CPU;

abstract public class ConditionalJumps extends ByteCodeOneParameter{
	public ConditionalJumps(){}
	abstract protected boolean compare(int n1, int n2);
	public ConditionalJumps(int j){ 
		super(j); 
	}
	
	@Override
	public boolean execute(CPU cpu) {
	if (cpu.getSizeStack()>=2){
		int n1 = cpu.getStack();
		int n2 = cpu.getStack();
		if (compare(n2,n1)) cpu.setProgramCounter(this.param);
		else cpu.increaseProgramCounter();
		return true;
	}else return false;
	}
	
}
