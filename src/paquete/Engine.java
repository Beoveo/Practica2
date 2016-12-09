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
		
		
		public boolean readByteCodeProgram(){
			boolean lleno = false;
			String line = " ";	
			while(!line.equalsIgnoreCase("END") && !lleno){
				line = in.nextLine();
				line =  line.toUpperCase();
				if(!line.equalsIgnoreCase("END")){
					bc = ByteCodeParser.parse(line);
					if(bc == null) System.out.println("Error : Introduzca un bytecode correcto.");
					else{
						lleno = bcProgram.insertarByteCode(bc);
						if(lleno) System.out.println("Error: A alcanzado el limite de instrucciones, o la instruccion esta ocupada.");
				}
			}
		}
		return lleno;
}
		
		
		public void start(){
			this.end = false;
			String line = " ";
				while(!end){
				System.out.println(">"); 
				line = in.nextLine();
				line =  line.toUpperCase();
				comando = CommandParser.parse(line); 
				if(comando == null) System.out.println("Error: Vuelva introducir el comando.");
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
		  * Este ejecutarÃ¡ en la CPU cada bytecode almacenado en bcProgram e 
		  * irÃ¡ mostrando por pantalla el estado de la cpu, siempre y cuando no se ejecute HALT.
		  * Si se ejecuta HALT la maquina ejecutara todas las instrucciones hasta esta, en la cual se para.
		  * Una vez salga del bucle, se llama a resetHalt(), un metodo que vuelve a poner halt() a false.
		  * @return Devuelve si se ha ejecutado el comando RUN.
		  */
		public boolean executeCommandRun(){
			boolean exeOK = true;
			int i = 0;
			while (i< this.bcProgram.getNumBC() && exeOK) {
				ByteCode instr = this.bcProgram.getByteCode(i); 
			if (instr.execute(this.cpu)){
				i++; 
				}else{
					exeOK = false; // salir del bucle
				}
			}
			System.out.println("El estado de la maquina tras ejecutar el programa es:" +
								System.getProperty("line.separator"));
			System.out.println(cpu.toString());
			cpu.reset();
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
