package paquete;

abstract public class ConditionalJumps extends ByteCodeOneParameter{
	public ConditionalJumps(){}
	public ConditionalJumps(int j){ 
		super(j); 
	}
	
	@Override
	public boolean execute(CPU cpu) {
	if (cpu.getStackSize()>=2){
		// Rellenar
	if (compare(n2,n1)) cpu.setProgramCounter(this.param);
	else cpu.increaseProgramCounter();
	return true;
	}
	else return false;
	}
	abstract protected boolean compare(int n1, int n2);
}
