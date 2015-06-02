package eu.sl9.ti;

import java.util.concurrent.Callable;

public class SolveBot2 implements Callable {
	
	int nr;

	boolean countDown = true;
	
	boolean[][] edges;
	int max;
	
	int[] rounds;
	
	int edgeCount;
	boolean[] hasEdge;
	
	int minimum = 200000;
	boolean[] loesung;
	
	public SolveBot2(int nr, boolean countDown, boolean[][] edges, int edgeCount, boolean[] hasEdge, int[] rounds) {
		super();
		this.nr = nr;
		this.countDown = countDown;
		
		this.edges = edges;
		this.max = edges.length - 1;
		this.rounds = rounds;
		this.edgeCount = edgeCount;
		this.hasEdge = hasEdge;
		
		loesung = new boolean[max];
	}

	@Override
	public boolean[] call(){
		
		printer("Call bot "+nr);
		
		int i;
		if(countDown)	i = rounds[0];
		else			i = rounds[1];
		
		boolean running = true;
		
		while(running){
			
			if(countDown){
				i--;
				running = (i >= rounds[1]);
			}
			else{
				i++;
				running = (i <= rounds[0]);
//				printer("Bot "+nr+" ist bei "+i);
			}
//			printer("Bot "+nr+" ist bei "+i);
//			printer("runde : "+i);
			boolean[] aktuellerVersuch = intToBool(i);
			int anzahlAuswahl = nrInBoolArray(aktuellerVersuch);
			
			if      (anzahlAuswahl > (edgeCount*2)+1) continue;
			else if (!allEdgesInCover(aktuellerVersuch))continue;
			else if (anzahlAuswahl >= minimum) continue;
			else{
				minimum = anzahlAuswahl;
				loesung = aktuellerVersuch;
//				printer("habe eine: "+minimum);
			}
//			System.out.print("/ "+i);
		}
		
//		printer("habe eine: "+minimum+" >> "+boolArrayToString(loesung,0));
		if(minimum < 200000) printer("Bot "+nr+" hat eine mit "+minimum+" gefunden!");
		else printer("Bot "+nr+" hat keine gefunden!");
		
		
		
		
		return loesung;
	}
	private void printer(String p){
		System.out.println(p);
	}

	private boolean[] intToBool(int num){
		boolean[] bits = new boolean[max];
		
		for (int i = (max-1); i >= 0; i--) {
	        bits[i] = (num & (1 << i)) != 0;
	    }
		return bits;
	}
	private int nrInBoolArray(boolean[] array){
		int count = 0;
		
		for( int i=0; i < array.length ; i++) if(array[i]) count++;
		
		return count;
	}
	private boolean allEdgesInCover(boolean[] set){
		
		for ( int i = 0 ; i < set.length ; i++){
			
			if(set[i] && !hasEdge[i+1]) return false;
			
			for( int j = i+1 ; j < set.length ; j++){
				if( ( edges[i+1][j+1] ) &&  ( !set[i] && !set[j] ) ) return false;
			}
		}
		
		return true;
	}
//	private String boolArrayToString(boolean[] array, int start){
//		printer("haaaaaaaaalloooo "+nr+"  Start: "+start);
//		
//		String f = " ";
//		String t = "X";
//		String seperator = "";
//		
//		String s = ""+(array[start]  ? t : f);
//		for(int j=start+1; j < array.length ; j++) s=s+seperator+(array[j]  ? t : f);
//	
//		return s;
//	}
//
//	public boolean[] getLoesung() {
//		return loesung;
//	}
}
