package bytecodes;

import paquete.CPU;

/**
 * CLASE LOAD:
 * Clase que hereda metodos de ByteCodeOneParameter. Parsea lo introducido por teclado
 * y ejecuta la instruccion.
 */
public class Load extends ByteCodeOneParameter{
	
	/**
	 * Constructora que nos permite coger las instrucciones con un parametro.
	 */
	public Load(){};
	public Load(int param){
		super(param);
	}
	
	/**
	 * Metodo que ejecuta la instruccion LOAD. A la cual se le pasa el parametro
	 * que es la posicion de memoria de la que sacaremos un elemento para meterlo
	 * en la pila.
	 */
	public boolean execute(CPU cpu) {
		if(cpu.load(super.param)){ cpu.increaseProgramCounter(); return true;}
		else return false;
	}
	
	/**
	 * Metodo que ya tiene la linea divida en dos palabras, la primera es el nombre de la
	 * instruccion y la segunda es el parametro/valor que usara.
	 * Si coincide con la instruccion devuelve un nuevo objeto de esta, sino devuelve null.
	 */
	@Override
	protected ByteCode parseAux(String string1, String string2) {
		int param = Integer.parseInt(string2);
		if(!string1.equalsIgnoreCase("LOAD") || param < 0) return null;
		else return new Load(param);
	}

	/**
	 * Metodo toString que devuelve el nombre de la instruccion.
	 */
	@Override
	protected String toStringAux() {return "LOAD";}
}
