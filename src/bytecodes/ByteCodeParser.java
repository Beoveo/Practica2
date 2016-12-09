package bytecodes;

import arithmetics.Add;
import arithmetics.Div;
import arithmetics.Mul;
import arithmetics.Sub;
import conditionals.IfLeq;
import conditionals.IfNeq;
import conditionals.Ifeq;
import conditionals.Ifle;

/**
 *CLASS BYTECODE-PARSER: 
 *Parseamos los bytecodes de tipo String a ENUM_BYTECODE.
 */
public class ByteCodeParser {
	
	private final static ByteCode[] bytecodes = {new Add(),new Sub(), new Div(),
		 new Mul(),new Load(),new Push(), new Ifeq(), new Ifle(), new IfLeq(), new IfNeq(), 
		 new Store(), new Out(), new Halt()};
	
	public static ByteCode parse(String s){
		s = s.trim();
		String[] words = s.split(" +");
		if (words.length == 0) return null; 
		else {
		boolean found = false;
		int i = 0;
		ByteCode c = null;
		while (i < bytecodes.length && !found){
		c = bytecodes[i].parse(words);
		if (c!=null) found=true;
			else i++;
		 }
		 return c;
		}
	}
		
}
