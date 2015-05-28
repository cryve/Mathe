// Copyright (c) 2014, Dr. Edmund Weitz.  All rights reserved.

package de.weitz.ti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import eu.sl9.ti.SolveBot;
import eu.sl9.ti.SolveBot2;

class VertexCoverAlgo{
	static String server = "weitz.de";
	static String submitURL = "submit";
  
	// please enter your name here - see also the class
	// VertexCoverAlgoPassword for the password
	static String name = "6x9gleich42";
	// start with problem level 1 - see PDF for details
	static int problemLevel = 3;
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

	VertexCoverAlgo(boolean edges[][]) {
		this.edges = edges;
		this.max = edges.length - 1;
		this.hasEdge = new boolean[edges.length];
		this.rounds = (int) Math.pow(2,max);
		this.loesung = new boolean[max];
		
		// Ausgabe & wer hat eigentlich Edges?
//		String s = "";
		for( int i=1; i <= max ; i++){
//			s = s+"\n"+i+" |"+boolArrayToString(edges[i],1, " -",  " X", "");
			hasEdge[i] = (nrInBoolArray(edges[i]) > 0);
			
			for( int j=i+1 ; j <= max ; j++) if(edges[i][j]) edgeCount++;
			
		}
//		printer(s);
	}

	// this is where you should write your code 

	int[] solve() {
		int solution[];
		

		printer(">> Max: "+max+" Anzahl Edges: "+edgeCount);

		
		if(edgeCount <= 0) solution = new int[0];					// Sonderfall 1 - keine Edges
		else if (edgeCount >= maxNrEdges(max)){						// Sonderfall 2 - alle Vertexes mit allen anderen verbunden
			solution = new int[max];
			for(int j=0; j < max-1 ; j++) solution[j] = j+1;
		}
		else{
			
			int anzahlBots = 4;
			
			int[][] sbrounds = new int[anzahlBots][2]; // Start und Stop fuer sloveBots
			
			int bereiche = rounds/anzahlBots;
			int rest = rounds-(bereiche*anzahlBots);
			printer("Rest = "+rest);
			
			boolean[][] botLoesungen = new boolean[anzahlBots][max];
			
			
			for (int i = 0 ; i > anzahlBots ; i++){
				sbrounds[i][0] = bereiche+(i*bereiche)+( i == (anzahlBots-1) ? rest : 0);
				sbrounds[i][1] = (i*bereiche);
			}
			
			List< Future > futuresList = new ArrayList< Future >();
			int nrOfProcessors = Runtime.getRuntime().availableProcessors();
			ExecutorService eservice = Executors.newFixedThreadPool(nrOfProcessors);
			
			printer("los gehts!!");
			
			for(int i = 0 ; i < anzahlBots ; i++){
				futuresList.add(eservice.submit(new SolveBot2(i, edges, edgeCount, hasEdge, sbrounds[i])));
				
				boolean[] result;
				for(Future future:futuresList) {
					try {
						result = (boolean[]) future.get();
					}
					catch (InterruptedException e) {}
					catch (ExecutionException e) {}
				}
			}
			
//			SolveBot solveBot_1 = new SolveBot(1, edges, edgeCount, hasEdge, sbrounds[0]);
//			SolveBot solveBot_2 = new SolveBot(2, edges, edgeCount, hasEdge, sbrounds[1]);
//			SolveBot solveBot_3 = new SolveBot(3, edges, edgeCount, hasEdge, sbrounds[2]);
//			SolveBot solveBot_4 = new SolveBot(4, edges, edgeCount, hasEdge, sbrounds[3]);
			

			
			
//			solveBot_1.run();
//			solveBot_2.run();
//			solveBot_3.run();
//			solveBot_4.run();
//			
//			try {
//				solveBot_1.join();
//				solveBot_2.join();
//				solveBot_3.join();
//				solveBot_4.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			
//			botLoesungen[0] = solveBot_1.getLoesung();
//			botLoesungen[1] = solveBot_2.getLoesung();
//			botLoesungen[2] = solveBot_3.getLoesung();
//			botLoesungen[3] = solveBot_4.getLoesung();
			
			for(boolean[] b : botLoesungen){

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
	
	
}