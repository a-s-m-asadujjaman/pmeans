package pmeans;

public class PMeansTest {	

	public static void main(String argv[]){
		NPoint p1=new NPoint(3);
		p1.setco(0,0);
		p1.setco(1,0);
		p1.setco(2,0);
		p1.setlabel(1);
		NPoint p2=new NPoint(3);
		p2.setco(0,1);
		p2.setco(1,1);
		p2.setco(2,1);
		p2.setlabel(1);
		NPoint p3=new NPoint(3);
		p3.setco(0,5);
		p3.setco(1,5);
		p3.setco(2,5);
		p3.setlabel(2);
		NPoint p4=new NPoint(3);
		p4.setco(0,6);
		p4.setco(1,6);
		p4.setco(2,6);
		//p4.setlabel(2);
		PMeans pm=new PMeans(2);		
		pm.train(p1);
		pm.train(p2);
		pm.train(p3);
		pm.train(p4);
		System.out.println(p1.getdistance(p2));
		NPoint pe=new NPoint(3);
		pe.setco(0,0);
		pe.setco(1,0);
		pe.setco(2,0);
		int ci=pm.findcentroidci(pe);
		System.out.println("Found ci: "+ci);
	}
}
