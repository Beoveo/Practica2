package paquete;

import java.util.Scanner;

/** 
 *CLASS ENGINE:
 * En la clase engine se introducira el comando por teclado, despues se parseara y se comprobara que esta bien escrito y
 * si esto se cumple se ejecutara llamando a executeCommand (que a su vez llamara a engine).
 * Una vez ejecutado si es correcta la ejecuccion se mostrara el programa almacenado, sino se dara error.
 */
public class Engine {
	private ByteCodeProgram bcProgram;
	private boolean end;
	private static Scanner in = new Scanner(System.in);
	private CPU cpu = new CPU();
	private Command comando = null;
	
	/**
	 * La constructora engine inicializa el booleano end que usaremos en el metodo quit.
	 * Ademas inicializa un nuevo objeto del tipo ByteCodeProgram, 
	 * que usaremos para mostrar el programa almacenado por pantalla.
	 */
	public Engine(){
		this.end = false;
		this.bcProgram = new ByteCodeProgram();
	}
	
	/**
	 * En el metodo start introducimos por tecladp el comando que deseamos ejecutar.
	 * Esta linea se parsea y se comprueba si el comando es valido, si no lo es da mensaje de error.
	 * Si es valido comprueba que se ha podido ejecutar y que existe un programa almacenado.
	 * Si se cumplen todas las condiciones anteriores, se mostrara el programa almacenado sin ningun mensaje de error.
	 * El bucle se ejecutara siempre y cuando no se ejecute quit.
	 */
	public void start(){
		this.end = false;
		String line = " ";
			
		while(!end){
			System.out.println(">"); 
			line = in.nextLine();
			comando = CommandParser.parse(line); //lee una linea
			if(comando == null) System.out.println("Error: Ejecucion incorrecta del comando.");
			else {
				System.out.println(comando.toString());
				
				if(!comando.executeCommand(this) ) {
					System.out.println("Error: Ejecucion incorrecta del comando.");
					System.out.println("Programa almacenado: " + 
							System.getProperty("line.separator") + bcProgram.toString());
				}else if (bcProgram.getNumBC() != 0){
					System.out.println("Programa almacenado: " + 
							System.getProperty("line.separator") + bcProgram.toString());
				}
			}
		}
		System.out.println("Fin de la ejecucion...."); 
		in.close();
	}
	
	/**
     * El metodo help muestra por pantalla una ayuda para que el usuario 
     * sepa que instrucciones puede usar.
     * @return Devuelve true si se ha podido mostrar por pantalla, es decir, si se ha ejecutado.
     */
	public boolean help(){
		boolean ok = true;
		if(ok){
		System.out.println("HELP: Muestra esta ayuda.");
		System.out.println("QUIT: Cierra la aplicacion.");
		System.out.println("RUN: Ejecuta el programa.");
		System.out.println("NEWINST BYTECODE: Introduce una nueva instruccion al programa.");
		System.out.println("RESET: Vacia el programa actual.");
		System.out.println("REPLACE N: Reemplaza la instruccion N por la solicitada al usuario.");
		System.out.println("");
		} else ok = false;
		return ok;
	}

	/**
	 * Este metodo resetea el programa y la cpu, es decir, resetea toda la maquina virtual.
	 * Se ejecuta cuando llamamos a RESET.
	 * @return Devuelve true si se ha ejecutado.
	 */
	public boolean resetProgram(){ 
		bcProgram.reset();
		cpu.reset();
		return true;
	}
	
	/**
	 * Este metodo cambia el valor del booleano global end a true, lo que significa que se va ha ejecutar quit.
	 * @return Devuelve el nuevo valor de end.
	 */
	public boolean endExecution(){ 
		return this.end = true;
	}
	
	/**
	 * El metodo addBcInstruction comprueba si el ByteCode que le pasan como parametro coincide con alguno
	 * de los disponibles. Si es valido se inserta en el programa de bytecodes.
	 * @param bc Es la instruccion que se añadira al programa.
	 * @return Devuelve false si no coincide con ninguna de las instrucciones y si coincide, la introduce.
	 */
	public boolean addBcInstruction(ByteCode bc){
		if(bc.getIntruc() == ENUM_BYTECODE.ADD) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.DIV) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.HALT) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.LOAD) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.MUL) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.OUT) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.PUSH) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.STORE) return bcProgram.insertarByteCode(bc);
		else if (bc.getIntruc() == ENUM_BYTECODE.SUB) return bcProgram.insertarByteCode(bc);
		else {System.out.println("ERROR! La instruccion introducida no es valida.");
           return false;
			}
		}

	 /**
	  * El metodo executeCommandRun ejecuta el comando RUN.
	  * Este ejecutará en la CPU cada bytecode almacenado en bcProgram e 
	  * irá mostrando por pantalla el estado de la cpu, siempre y cuando no se ejecute HALT.
	  * Si se ejecuta HALT la maquina ejecutara todas las instrucciones hasta esta, en la cual se para.
	  * Una vez salga del bucle, se llama a resetHalt(), un metodo que vuelve a poner halt() a false.
	  * @return Devuelve si se ha ejecutado el comando RUN.
	  */
	public boolean executeCommandRun(){
		boolean exeOK = true;
		int i = 0;
		while (i< this.bcProgram.getNumBC() && exeOK && !cpu.halt()) {
			ByteCode instr = this.bcProgram.getByteCode(i); 
		if (cpu.execute(instr)){
			System.out.println("El estado de la maquina tras ejecutar el bytecode  "+ bcProgram.getByteCode(i) +"  es:");
			System.out.println(cpu.toString());
			i++; 
			}else{
				exeOK = false; // salir del bucle
			}
		}cpu.reset();
		cpu.resetHalt();
		return exeOK; 
    }


	/**
	 * El metodo replace comprueba que la pos sea valida, que exista el programa de instrucciones 
	 * y que no reemplacemos en una pos vacia.
	 * Si se cumplen estas condiciones, se muestra por pantalla el mensaje "Nueva instruccion: ", se lee por teclado
	 * la instruccion a reemplazar y se llama al metodo que lo reemplaza.
	 * @param replace Es la pos en la cual se encuentra la instruccion a reemplazar.
	 * @return Devuelve si se ha podido realizar el cambio.
	 */
	public boolean replace(int replace){
		String line;
		ByteCode bc;
		boolean replaceOK = false;
		if (replace >= 0 && bcProgram.getNumBC() > 0 && bcProgram.getByteCode(replace) != null){
			replaceOK = true;
		System.out.println("Nueva instruccion: ");
		line = in.nextLine();
		bc = ByteCodeParser.parse(line);
		bcProgram.replace(replace, bc);
		}
		return replaceOK;
	}

}