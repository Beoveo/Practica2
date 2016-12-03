package paquete;

/**
 * CLASS BYTECODE:
 * En la clase bytecode tratamos la gestion de las operaciones 
 * que podemos hacer para tratar las diferentes instrucciones.
 */

abstract public class ByteCode {
	abstract public boolean execute(CPU cpu);
	abstract public ByteCode parse(String[] words);
}

/*
public class ByteCode {
	private ENUM_BYTECODE instr;
	private int param;
	
	
	public ByteCode(ENUM_BYTECODE op, int dato){
		this.instr = op;
		this.param = dato;
	}
	
	
	public ByteCode(ENUM_BYTECODE op){
		this.instr = op;
	}
	
	 
	public ByteCode (int valor){
		this.param = valor;
	}
	
	
	
	public int getParam(){
		return this.param;
	}
	
	
	public ENUM_BYTECODE getIntruc(){
		return this.instr;
	}
	
	
	public String toString(){
		String s = "";
		if(instr.getNumArgs() > 0){
			s = this.instr + " " + this.param;
		} else s = "" + this.instr; 
		return s;
	}
}*/
