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
	
	public boolean readByteCodeProgram(){
		String line = " ";
		line = line.toUpperCase();
		//LEE DATOS DE TECLADO HASTA QUE INTRODUZCA END.
		while(line != "END"){
			System.out.println(">"); 
			line = in.nextLine();
			comando = CommandParser.parse(line); //lee una linea
			if(comando == null) System.out.println("Error: Ejecucion incorrecta del comando.");
			else {
				//addByteCodeProgram();
				System.out.println(comando.toString());
				if(!comando.execute(this)) {
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
		return end;
	}
	
	/*
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
	*/
	
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
	public boolean endExecution(){ return this.end = true;}
	
	


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

