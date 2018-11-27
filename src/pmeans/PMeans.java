package pmeans;

import java.util.*;

public class PMeans {
	private int numcentroids;
	private int numco;
	private NPoint[] centroids;
	private int centinitcount;
	private ArrayList<LinkedList<NPoint>> points;

	public PMeans(int nc){
		this.numcentroids=nc;
		this.centroids=new NPoint[nc];
		this.points=new ArrayList<LinkedList<NPoint>>();
		for(int i=0;i<nc;i++){
			this.centroids[i]=null;
		}
		this.numco=0;
		this.centinitcount=0;
	}

	public void train(NPoint npnt){
		System.out.print("Training with$ ");
		npnt.show();
		if(this.numco==0)
			this.numco=npnt.getnumco();
		NPoint result=new NPoint(this.numco);
		if(npnt.getlabel()>0){//If point is labeled (apply supervised learning)
			int ci=npnt.getlabel()-1;
			if(this.centroids[ci]==null)
			{
				this.centroids[ci]=new NPoint(npnt);
				if(this.points.size()<ci+1){
					for(int i=0;i<ci+1;i++){
						this.points.add(new LinkedList<NPoint>());
					}
				}
				this.points.get(ci).add(new NPoint(npnt));
				System.out.print("Centroid initialized$ ");
				centroids[ci].show();
			}
			else{
				updatecentroid(npnt, ci);
				System.out.print("New centroid for labeled data$ ");
				centroids[ci].show();

			}
		}
		else{//Find centroid
			int ci=findcentroidci(npnt);
			System.out.println("Found class="+(ci+1));
			updatecentroid(npnt, ci);
			System.out.print("New centroid for unlabeled data$ ");
			centroids[ci].show();
		}
	}

	private void updatecentroid(NPoint npnt, int ci){
		this.points.get(ci).add(new NPoint(npnt));
		NPoint newcent=new NPoint(npnt.getnumco());
		
		for(NPoint p: points.get(ci)){
			for(int i=0;i<npnt.getnumco();i++){
				double d=newcent.getco(i)+p.getco(i);
				newcent.setco(i, d);
			}
		}

		for(int i=0;i<npnt.getnumco();i++){
			double d=newcent.getco(i)/points.get(ci).size();
			newcent.setco(i, d);
			//System.out.println("New centroid("+(ci+1)+"): "+i+", "+d);
		}

		centroids[ci]=newcent;
		
	}
	
	public int findcentroidci(NPoint npnt){
		int ci=0;
		double mindistance=Double.MAX_VALUE;
		for(int i=0;i<this.numcentroids;i++){
			if(this.centroids[i]==null)
				throw new IllegalStateException("centroids not yet fully initialized");
			double dist=npnt.getdistance(this.centroids[i]);
			//System.out.println("Distance: "+dist);
			if(dist<mindistance){
				ci=i;
				mindistance=dist;
			}
		}
		//System.out.println("Found ci: "+ci);
		return ci;
	}
}
