package paquete;

import java.util.Scanner;

import bytecodes.ByteCode;
import bytecodes.ByteCodeParser;
import bytecodes.ByteCodeProgram;
import commands.Command;
import commands.CommandParser;

/** 
 *CLASS ENGINE:
 * En la clase engine se introducira el comando por teclado, despues se parseara y se comprobara que esta bien escrito y
 * si esto se cumple se ejecutara llamando a executeCommand (que a su vez llamara a engine).
 * Una vez ejecutado si es correcta la ejecuccion se mostrara el programa almacenado, sino se dara error.
 */
	public class Engine {
		private ByteCodeProgram bcProgram;
		private ByteCode bc;
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
			boolean leido = true;
			String line = " ";	
			while(!endLine && leido){
			  line = in.nextLine();
			  line =  line.toUpperCase();
			  if(line.equals("END")) endLine = true;
			  else{
				bc = ByteCodeParser.parse(line);
				if(bc == null) System.out.println("Error : Introduzca un bytecode correcto.");
				else{
				leido = bcProgram.insertarByteCode(bc);
				if(!leido) System.out.println("Error: A alcanzado el limite de instrucciones, o la instruccion está ocupada.");
				}		
			  }
			}
			in.close();
		return leido;
		}
		
		
		public void start(){
			this.end = false;
			String line = " ";
				while(!end){
				System.out.println(">"); 
				line = in.nextLine();
				line =  line.toUpperCase();
				comando = CommandParser.parse(line); 
				if(comando == null) System.out.println("Error, vuelva introducir el comando.");
				else {
					System.out.println("Comienza la ejecucion de " + comando.toString());
					comando.execute(this);
					if (bcProgram.getNumBC() != 0){
						System.out.println("Programa almacenado: " + 
								System.getProperty("line.separator") + bcProgram.toString());
						}else System.out.println("Introduzca al menos una instruccion bytecode.");
				}
			}
			in.close();
		}
		
		//HELP
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

		
		public boolean help(){
			boolean ok = true;
			System.out.println(comando.textHelp());
			return ok;
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
