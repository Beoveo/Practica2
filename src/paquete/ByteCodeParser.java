package paquete;

/**
 *CLASS BYTECODE-PARSER: 
 *Parseamos los bytecodes de tipo String a ENUM_BYTECODE.
 */
public class ByteCodeParser {
	
	/**
	 * Este metodo divide el string que se pasa como parametro en dos partes, en la instruccion bytecode y el dato, ademas de parsearlos 
	 * y cambiarlos a sus respectivos tipos (ENUM_BYTECODE e INT). 
	 * 
	 * @param s es el string que se lee por teclado .
	 * @return Si la longitud es solo de 1 elemento (ADD, MUL...) devolvemos solo name. 
     * Si la longitud es de 2 devolvemos name, mediante un metodo, y el Int parseado.
	 */
	public static ByteCode parse(String s){
	    ByteCode name = null; 
		s = s.trim();
		String[] parts = s.split(" +");
		if(parts.length >2 && parts.length <= 0){
			return null;
		} else{
			parts[0] = parts[0].toUpperCase();
			if(parts.length == 1){
			
			switch(parts[0]){
			case "ADD":
				 name = new ByteCode(ENUM_BYTECODE.ADD);
				 break;
			case "SUB": 
				name = new ByteCode(ENUM_BYTECODE.SUB);
				break;
			case "DIV":
				name = new ByteCode(ENUM_BYTECODE.DIV);
				break;
			case "MUL":
				 name = new ByteCode(ENUM_BYTECODE.MUL);
				 break;
			case "LOAD":
			case "PUSH":
			case "STORE":
				 name = null;
				 break;
			case "OUT":
				 name = new ByteCode(ENUM_BYTECODE.OUT);
				 break;
			case "HALT":
				 name = new ByteCode(ENUM_BYTECODE.HALT);
				 break;
			}return name;
			}else
				switch(parts[0]){
				case "LOAD":
					 name = new ByteCode(ENUM_BYTECODE.LOAD);
					 break;
				case "PUSH":
					 name = new ByteCode(ENUM_BYTECODE.PUSH);
					 break;
				case "STORE":
					 name = new ByteCode(ENUM_BYTECODE.STORE);
					 break;
				}
				return new ByteCode (name.getIntruc(), Integer.parseInt(parts[1]));		
		}
	}

}
