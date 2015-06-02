// Copyright (c) 2014, Dr. Edmund Weitz.  All rights reserved.

package de.weitz.ti;

import java.util.ArrayList;
import java.util.concurrent.*;

import eu.sl9.ti.SolveBot2;

class VertexCoverAlgo{
	static String server = "weitz.de";
	static String submitURL = "submit";
  
	// please enter your name here - see also the class
	// VertexCoverAlgoPassword for the password
	static String name = "jmnx";
	// start with problem level 1 - see PDF for details
	static int problemLevel = 42;
	// change to true if you want to repeat the last exercise
	static boolean repeatLast = false;

	boolean[][] edges;
	int max;
	
	// myVariables
	boolean[] hasEdge;
//	int hasEdgeCounter = 0;
	int edgeCount = 0;
	int rounds;
	int minimum = 200000;
	boolean[] loesung;
	
	boolean countDown = true;

	VertexCoverAlgo(boolean edges[][]) {
		this.edges = edges;
		this.max = edges.length - 1;
		this.hasEdge = new boolean[edges.length];
		this.rounds = (int) Math.pow(2,max);
		this.loesung = new boolean[max];
		
		// Ausgabe & wer hat eigentlich Edges?
		String s = "";
		for( int i=1; i <= max ; i++){
			s = s+"\n"+i+" |"+boolArrayToString(edges[i],1);
			hasEdge[i] = (nrInBoolArray(edges[i]) > 0);
			
			for( int j=i+1 ; j <= max ; j++) if(edges[i][j]) edgeCount++;
			
		}
		printer(s);
	}

	// this is where you should write your code 

	int[] solve() {
		int solution[];
		

		printer(">> Max: "+max+" Anzahl Edges: "+edgeCount);
		
		if (edgeCount < max){
			countDown = false;
			printer("count up!");
		}
		else printer("count down!");

		
		if(edgeCount <= 0) solution = new int[0];					// Sonderfall 1 - keine Edges
		else if (edgeCount >= maxNrEdges(max)){						// Sonderfall 2 - alle Vertexes mit allen anderen verbunden
			solution = new int[max];
			for(int j=0; j < max-1 ; j++) solution[j] = j+1;
		}
		else{
			
			int anzahlBots = 8;
			
			int[][] sbrounds = new int[anzahlBots][2]; // Start und Stop fuer sloveBots
			
			int bereiche = rounds/anzahlBots;
			int rest = rounds-(bereiche*anzahlBots);
			printer("Rest = "+rest);
			
//			boolean[][] botLoesungen = new boolean[anzahlBots][max];
			
			
			for (int i = 0 ; i < anzahlBots ; i++){
				sbrounds[i][0] = bereiche+(i*bereiche)+( i == (anzahlBots-1) ? rest : 0);
				sbrounds[i][1] = (i*bereiche);
//				printer("Bereich "+i+" von "+sbrounds[i][0]+" bis "+sbrounds[i][1]);
			}
			
//			List< Future > futuresList = new ArrayList< Future >();
			
			Future[] bots = new Future[anzahlBots];
			
			int nrOfProcessors = Runtime.getRuntime().availableProcessors();
			ExecutorService eservice = Executors.newFixedThreadPool(nrOfProcessors);
			
			printer("los gehts!!");
			
			ArrayList<boolean[]> ergebnisse = new ArrayList<boolean[]>();
			
			for(int i = 0 ; i < anzahlBots ; i++){
				bots[i] = eservice.submit(new SolveBot2(i, countDown, edges, edgeCount, hasEdge, sbrounds[i]));
			}
			
			boolean warten = true;
			
			while(warten){
				boolean istFertig = true;
				for(Future bot : bots){
					istFertig = istFertig && bot.isDone();
				}
				
				warten = !istFertig;
			}
			
			for(Future future : bots) {
				try {
					ergebnisse.add((boolean[]) future.get());
				}
				catch (InterruptedException e) {}
				catch (ExecutionException e) {}
			}

			
			for(boolean[] b : ergebnisse){

				int gr = nrInBoolArray(b);
				
//				printer(boolArrayToString(b,0)+" gr = "+gr);
				
				if ((gr >= minimum)||( gr == 0 )){
//					printer("NOPE: "+minimum+" und "+gr);
					continue;
				}
				else{
					minimum = gr;
					loesung = b;
//					printer("habe eine: "+minimum);
				}
			}

			
			solution = new int[minimum];
			int solCount = 0;
			for(int j=0; j < loesung.length ; j++){
				if(loesung[j]){
					solution[solCount] = j+1;
					solCount++;
				}
			}
			
		}
		
		return solution;
	}
	
	private void printer(String p){
		System.out.println(p);
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
	private String boolArrayToString(boolean[] array, int start){
//		printer("haaaaaaaaalloooo "+nr+"  Start: "+start);
		
		String f = "  ";
		String t = " X";
		String seperator = "";
		
		String s = ""+(array[start]  ? t : f);
		for(int j=start+1; j < array.length ; j++) s=s+seperator+(array[j]  ? t : f);
	
		return s;
	}
	
}