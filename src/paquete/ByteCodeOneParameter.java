package paquete;

public abstract class ByteCodesOneParameter extends ByteCode { 
	protected int param;
	protected int n1;
	protected int n2;
	
	public ByteCodesOneParameter(){};
	
	public ByteCodesOneParameter(int p){ this.param = p; }
	public ByteCodesOneParameter(int param1, int param2){ 
		this.n1 = param1;
		this.n2 = param2;
	};
	
	@Override
	//Este ByteCode parse parsea la palabra y la divide en dos (sustituye bytecodeParse)
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


