package bytecodes;

public abstract class ByteCodeOneParameter extends ByteCode { 
	protected int param;
	
	public ByteCodeOneParameter(){};
	
	public ByteCodeOneParameter(int p){ this.param = p; }
	
	@Override
	public ByteCode parse(String[] words) {
		if (words.length!=2) return null;
		else return this.parseAux(words[0],words[1]);
	}
	
	abstract protected ByteCode parseAux(String string1, String string2);
	
	public String toString(){
		return this.toStringAux() + " " + this.param;
	}
	
	abstract protected String toStringAux(); 
	
}
