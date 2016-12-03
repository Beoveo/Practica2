package paquete;

/**
 * CLASS BYTECODE:
 * En la clase bytecode tratamos la gestion de las operaciones 
 * que podemos hacer para tratar las diferentes instrucciones.
 */
public class ByteCode {
	private ENUM_BYTECODE instr;
	private int param;
	
	/** Constructora de bytecode.
	 * Recibe:
	 * @param op ; el cual es el bytecode, ya sea push, load, add...
	 * @param dato ; el parametro que meta el usuario, solo es usado en load, push y store
	 */
	public ByteCode(ENUM_BYTECODE op, int dato){
		this.instr = op;
		this.param = dato;
	}
	
	/** Constructora de bytecode mas simple.
	 *  Recibe:
	 * @param op ; el bytecode que corresponda.
	 * Esta constructora es para aquellos casos en los que no hay un parametro (dato) de entrada.
	 */
	public ByteCode(ENUM_BYTECODE op){
		this.instr = op;
	}
	
	/** Constructora de bytecode mas simple.
	 *  Recibe:
	 * @param valor ; dato de entrada.
	 * Mete valor dentro de parametro.
	 */ 
	public ByteCode (int valor){
		this.param = valor;
	}
	
	
	/** Metodo que devuelve el parametro.
	 * No recibe nada.
	 * @return ; devuelve el parametro.
	 */
	public int getParam(){
		return this.param;
	}
	
	/** Metodo que devuelve la instruccion bytecode.
	 * No recibe nada.
	 * @return ; devuelve la instruccion.
	 */
	public ENUM_BYTECODE getIntruc(){
		return this.instr;
	}
	
	/** El metodo to string devuelve una cadena de caracteres
	 *  y dependiendo de si el numero de argumentos es mayor que cero para
	 *  los bytecodes tales como push, load y store, o  igual a cero para
	 *  las operaciones ADD, SUB, HALT... Devolverá por tanto, la instuccion 
	 *  y el parametro o solo la instuccion.
	 */
	public String toString(){
		String s = "";
		if(instr.getNumArgs() > 0){
			s = this.instr + " " + this.param;
		} else s = "" + this.instr; 
		return s;
	}
}
