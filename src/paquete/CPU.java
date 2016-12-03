package paquete;

/**
 * CLASS CPU:
 *La CPU contiene todos los metodos de las instrucciones bytecode y
 *se encarga de ejecutarlos si son validos.
 */
public class CPU {
	
	  private Memory memoria = new Memory();
	  private OperandStack pila = new OperandStack(); 
	  private boolean exeHalt = false;
	  private int cima;
	  private int programCounter = 0;
	  private ByteCodeProgram bcProgram = new ByteCodeProgram();
	  
	  public CPU(ByteCodeProgram program){
		  this.bcProgram = program;
		  }
	  public void halt(){
		  this.exeHalt = true;
	  }
	  /**
	   * El metodo halt para la maquina.
	   * Es decir, se ejecutan todas las instrucciones introducidas hasta que llegue a HALT.
	   * @return Devuelve si halt se puede ejecutar. Siempre esta a false, hasta que se introduce HALT,
	   *  en este momento el metodo debe devolver true.
	   */
	  public boolean returnHalt(){
		  return exeHalt;
	  }
	  
	  public boolean run() {
		  this.programCounter=0;
		  boolean error = false;
		  while (this.programCounter < bcProgram.getNumeroInstrucciones() && ) {
		  ByteCode bc = bcProgram.getByteCode(this.programCounter);
		  if (!bc.execute(this)) // salir del bucle
		  }
		  return // ejecución correcta?
		  }
	  
	  public int getStackSize(){
		  return pila.getNumElements();
	  }
	  
	  public void setProgramCounter(int jump){
		  this.programCounter = jump;
	  }
	  
	  public int pop() {
		  return this.pila.load(); 
		  }
	  public boolean push(int i){
		  return this.pila.push(i);
	  }
	  public boolean read(int param) {
		  return this.memoria.read(param);
	  }
	  public void write(int param, int value) {
		  this.memoria.write(param, value);
	  }
	  
	  public void increaseProgramCounter() {
		  this.programCounter++;
	  }
	  
	  /**
	   * El metodo add realiza la suma de la cima y la subcima.
	   * Primero comprueba si hay al menos 2 elementos para poder realizar la operacion.
	   * Si se cumple, entonces se suman los datos de la pila.
	   * @return Devuelve el valor resultante de la suma y se guarda en la pila.
	   * En caso de que no se cumpla la condicion, se devuelve false.
	   */
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
		
		/**
		 * El metodo sub realiza la resta de la subcima y la cima.
		 * Primero comprueba si hay al menos 2 elementos para poder realizar la operacion.
		 * Si se cumple, entonces se restan los datos.
		 * @return Devuelve el valor resultante de la resta y se guarda en la pila.
		 * En caso de que no se cumpla la condicion, se devuelve false.
		 */
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

		/**
		 * El metodo mul realiza la multiplicacion de la cima y la subcima.
		 * Primero comprueba si hay al menos 2 elementos para poder realizar la operacion.
		 * Si se cumple, entonces se multiplican los datos.
		 * @return Devuelve el valor resultante de la multiplicacion y se guarda en la pila.
		 * En caso de que no se cumpla la condicion, se devuelve false.
		 */
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

		/**
		 * El metodo div realiza la division de la subcima y la cima.
		 * Primero comprueba si hay al menos 2 datos para poder realizar la division,
		 *  si los hay comprueba que la cima sea distinta a cero, ya que cualquier numero dividido entre cero da infinito.
		 *  Si se cumplen estas condiciones realiza la division y guarda el valor en la pila.
		 * @return Devuelve false en caso de que no se cumplan alguna de las dos condiciones y si se cumplen devuelve
		 * el valor llamando al metodo que lo guarda en la pila.
		 */
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
		public void resetHalt(){
			exeHalt = false;
		}

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
