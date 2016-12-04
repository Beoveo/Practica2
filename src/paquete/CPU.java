package paquete;

/**
 * CLASS CPU:
 *La CPU contiene todos los metodos de las instrucciones bytecode y
 *se encarga de ejecutarlos si son validos.
 */
public class CPU {
	
    private Memory memoria = new Memory();
    private OperandStack pila = new OperandStack();
    private ByteCodeProgram bcProgram = new ByteCodeProgram();
    private boolean exeHalt = false;
    private int programCounter = 0; //indica la sig instruccion a ejecutar
  //Si no poniamos esta constructora daba error en engine
	public CPU(){	  }
	public CPU(ByteCodeProgram program){ this.bcProgram = program; }
	  
	
   	 public void halt(){ this.exeHalt = true; }
	  
	 
	public boolean add(){
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			valor1 = pila.getCima();
			pila.deleteCima();
			valor2 = pila.getCima();
			pila.deleteCima();
			return pila.push(valor1 + valor2);
		} else return false;
	}
		
		
	public boolean sub(){
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			valor1 = pila.getCima();
			pila.deleteCima();
			valor2 = pila.getCima();
			pila.deleteCima();
			return pila.push(valor1 - valor2);
		} else return false;
	}

		
	public boolean mul(){
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			valor1 = pila.getCima();
			pila.deleteCima();
			valor2 = pila.getCima();
			pila.deleteCima();
			return pila.push(valor1 * valor2);
		} else return false;
	}

		
	public boolean div(){
		int valor1, valor2;
		if(pila.getNumElements() >= 2){
			if(pila.getCima() != 0){
				valor1 = pila.getCima();
				pila.deleteCima();
				valor2 = pila.getCima();
				pila.deleteCima();
				return pila.push(valor2 / valor1);
				}else return false;
		}else return false;
	}
		
	public int load(){ return this.pila.load();}
		
	public boolean push(int i) { return pila.push(i);}
		
	public boolean read(int param) { this.memoria.read(param); return true; }
		
	public boolean write(int pos, int valor){ return memoria.write(pos, valor);}
		
	public int getSizeStack(){ return pila.getNumElements();}
	
    	public void increaseProgramCounter(){ this.programCounter++;}
	
	public void setProgramCounter(int jump){ this.programCounter = jump;}
		
	public boolean run() {
		this.programCounter=0;
		boolean error = false;
		//creo que faltaria una condicion de salto
		while (this.programCounter < bcProgram.getNumBC() && !error && !exeHalt) {
			ByteCode bc = bcProgram.getByteCode(this.programCounter);
				if (!bc.execute(this)) error = true;
				increaseProgramCounter();
		}
		return error;
	}
		
		/**
		 * El metodo reset de la CPU resetea la pila y la memoria.
		 */
	public void reset(){
		pila.reset();
		memoria.reset();
	}
		
		/**
		 * Este metodo cambia el estado de exeHalt una vez que se ejecuta HALT. 
		 * Lo vuelve a cambiar a false.
		 */
	public void resetHalt(){exeHalt = false;}

		/**
		 * Este metodo toString devuelve el estado de la CPU.
		 */
	public String toString(){
		  String s = System.getProperty("line.separator") +
		  "Estado de la CPU: " + System.getProperty("line.separator") +
		  " Memoria: " + this.memoria + System.getProperty("line.separator") + 
		 " Pila: " + this.pila + System.getProperty("line.separator");
		  return s;
    }
}
