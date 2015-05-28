// Copyright (c) 2014, Dr. Edmund Weitz.  All rights reserved.

package de.weitz.ti;

import java.util.Arrays;

class VertexCoverAlgo {
	static String server = "weitz.de";
	static String submitURL = "submit";
  
	// please enter your name here - see also the class
	// VertexCoverAlgoPassword for the password
	static String name = "6x9gleich42";
	// start with problem level 1 - see PDF for details
	static int problemLevel = 1;
	// change to true if you want to repeat the last exercise
	static boolean repeatLast = true;

	boolean[][] edges;
	int max;
	
	// myVariables
	boolean[] hasEdge;
	int hasEdgeCounter = 0;
	int edgeCount = 0;
	int rounds;

	VertexCoverAlgo(boolean edges[][]) {
		this.edges = edges;
		this.max = edges.length - 1;
		this.hasEdge = new boolean[edges.length];
		this.rounds = (int) Math.pow(2,max);
	}

	// this is where you should write your code 

	int[] solve() {
		int solution[];
		
		solution = new int[0]; // später entfernen
		
		
		if(true){
			// Ausgabe & wer hat eigentlich Edges?
			String s = "";
			for( int i=1; i <= max ; i++){
				s = s+"\n"+i+" |";
				for(int j=1; j <= max ; j++){
					s=s+(edges[i][j]  ? " X" : " -");

					hasEdge[i] = ( hasEdge[i] | edges[i][j] );
				}
				if(hasEdge[i]) hasEdgeCounter++;
			}
			printer(s+"\n\n"+hasEdgeCounter+"\n\n");
		}
		String s = "    ";
		for( int j=1 ; j <= max ; j++) s = s+j+" ";
//		s = s+"\n";
//		for( int j=1 ; j <= (max*2)+4 ; j++) s = s+"-";
		for (int i = 1 ; i <= max ; i++){
			s = s+"\n "+i+" |";
			for( int j=1 ; j <= i ; j++) s = s+" .";
			for( int j=i+1 ; j <= max ; j++){
				s=s+(edges[i][j]  ? "X" : " ")+"|";
				if(edges[i][j]) edgeCount++;
			}
		}
		printer(s+"\n\n"+hasEdgeCounter+"\n\n");
		
		if(edgeCount <= 0) solution = new int[0];					// Sonderfall 1 - keine Edges
		else if (edgeCount >= maxNrEdges(max)){						// Sonderfall 2 - alle Vertexes mit allen anderen verbunden
			solution = new int[max];
			for(int j=0; j < max ; j++) solution[j] = j+1;
		}
		else{
			for(int i = rounds; i >= 1 ; i--){
				
				
				boolean[] aktuellerVersuch = intToBool(i);
				int anzahlAuswahl = nrInBoolArray(aktuellerVersuch);
				
				if(anzahlAuswahl < (edgeCount/2)-1) continue;
				else{
					printer("Position :"+i);
					printer("Array: "+Arrays.toString(aktuellerVersuch));
					printer("anzahl auswahl: "+anzahlAuswahl+" Edges = "+edgeCount);
					printer("run");
				}
				
				
			}
		}
			
		printer("runden: "+rounds);
		
		return solution;
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
	private int maxNrEdges(int vtxNr){
		int count = 0;
		
		for (int i = vtxNr-1 ; i > 0 ; i-- ) count += i;
		
		return count;
	}
	
}