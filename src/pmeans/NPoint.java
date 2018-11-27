package pmeans;

public class NPoint {
	private int numco;
	private double[] co;
	private int label;
	public NPoint(int n){
		this.numco=n;
		this.co=new double[n];
		this.label=0;
	}
	public NPoint(double c1, double c2){
		this.numco=2;
		this.co=new double[2];
		this.co[0]=c1;
		this.co[1]=c2;
		this.label=0;
	}
	public NPoint(NPoint orig){
		this.numco=orig.numco;
		this.co=new double[orig.getnumco()];
		this.label=orig.getlabel();
		for(int i=0;i<this.numco;i++){
			this.co[i]=orig.getco(i);
		}
	}
	public int getnumco(){
		return this.numco;
	}
	public void setco(int i, double c)
	{
		if(i>=0&&i<this.numco)
			this.co[i]=c;
		else
			throw new IllegalArgumentException("invalid value of i");
	}
	public double getco(int i){
		if(i>=0&&i<this.numco)
			return this.co[i];
		else
			throw new IllegalArgumentException("invalid value of i");
	}
	public void setlabel(int l){
		this.label=l;
	}
	public int getlabel(){
		return this.label;
	}
	public double getdistance(NPoint n){
		if(n.getnumco()!=this.numco)
			throw new IllegalArgumentException("numco mismatch");
		double sq=0;
		for(int i=0;i<this.numco;i++){
			sq+=(this.co[i]-n.getco(i))*(this.co[i]-n.getco(i));
		}
		return Math.sqrt(sq);
	}
	public void show(){
		System.out.print("NPoint("+this.numco+"): ");
		for(int i=0;i<this.numco;i++){
			System.out.print(this.co[i]);
			if(i<this.numco-1)
				System.out.print(", ");
		}
		if(this.label>0)
			System.out.print(" label="+this.label);
		else
			System.out.print(" unlabeled");
		System.out.println();
	}
}
