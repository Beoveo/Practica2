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
	  
	  /**
	   * El metodo halt para la maquina.
	   * Es decir, se ejecutan todas las instrucciones introducidas hasta que llegue a HALT.
	   * @return Devuelve si halt se puede ejecutar. Siempre esta a false, hasta que se introduce HALT,
	   *  en este momento el metodo debe devolver true.
	   */
	  public boolean halt(){
		  return exeHalt;
	  }
	  
	  	/**
	  	 * Este metodo ejecuta la instruccion que se pasa como parametro.
	  	 * Primero se comprueba si la instruccion introducida coincide con alguna de las disponibles.
	     * Si coincide, entonces se ejecuta.
	  	 * @param instruccion Es la instruccion a ejecutar.
	  	 * @return Devolveremos si dicha operacion se ha podido ejecutar.
	  	 */
	  public boolean execute(ByteCode instruccion){  
		if(instruccion.getIntruc() == ENUM_BYTECODE.PUSH) return pila.push(instruccion.getParam());
			else if (instruccion.getIntruc() == ENUM_BYTECODE.LOAD){
	    	int pos;
	    	pos = instruccion.getParam();
	    	return  pila.push(memoria.read(pos));
	    }
	    else if (instruccion.getIntruc() == ENUM_BYTECODE.STORE){
	    	cima = pila.getCima();
	    	pila.deleteCima();
	    	return memoria.write(instruccion.getParam(), cima);
	    }
	    else if (instruccion.getIntruc() == ENUM_BYTECODE.ADD) return add();
	    else if (instruccion.getIntruc() == ENUM_BYTECODE.SUB) return sub();
	    else if (instruccion.getIntruc() == ENUM_BYTECODE.MUL) return mul();
	    else if (instruccion.getIntruc() == ENUM_BYTECODE.DIV) return div();
	    else if (instruccion.getIntruc() == ENUM_BYTECODE.OUT){
	    	if(pila.getNumElements() > 0){
	    		System.out.println(pila.getCima());
	    		return true;
	    	}else return false;
	    }
	    else if(instruccion.getIntruc() == ENUM_BYTECODE.HALT){ return exeHalt = true;} 
	    else {System.out.println("Error: La instruccion introducida no es valida.");
	     return false;
	    }
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
