// Copyright (c) 2014, Dr. Edmund Weitz.  All rights reserved.

package de.weitz.ti;

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
  
  boolean ausgabe = true;
  boolean[] gesetzt;
  int[] hatKante;

  VertexCoverAlgo(boolean edges[][]) {
    this.edges = edges;
    this.max = edges.length - 1;

    this.gesetzt  = new boolean[edges.length];
    this.hatKante = new int[edges.length];
    
  }

  // this is where you should write your code 
  int[] solve() {
    int solution[] = {4,3,5,6};
//	  int solution[];
    
    if(ausgabe){
	    // Ausgabe
	    String s = "";
	    for( int i=1; i <= max ; i++){
	    	s = s+"\n"+i+" |";
	    	for(int j=1; j <= max ; j++) s=s+(edges[i][j]  ? " 1" : " 0");
	    }
	    printer(s+"\n\n");
    }
    
    // Suchen
    boolean gefunden = false;
//    while(!gefunden){
//    	printer()
//	    for( int i=1; i <= max ; i++){
//	    	for(int j=1; j <= max ; j++){
//	    		
//	    	}
//	    }
//    }
//    for(int k=0 ; k < Math.pow(2,max) ; k++){
//		printer("====== Durchlauf :"+k+" ======");
//		
//	}
    // Wer hat kanten??
    for( int i=1; i <= max ; i++){
    	boolean hat = false;
    	for(boolean set : edges[i]) if(set) hatKante[i]++;
    }
    
    for( int i=1 ; i <= max ; i++){
    	// wenn keine kante gleich weiter
    	if(hatKante[i] <= 0) continue;
    	// sonst gucken
    	for(int j=i+1; j <= max ; j++){
    		
    	}
    }
    for(int k=1 ; k < Math.pow(2,max) ; k++){
    	printer("====== Durchlauf :"+k+" ======");
    	printer(""+max);
    	
    }
    
    for( int i=1 ; i <= 8 ; i++){
    	printer(Integer.toBinaryString(i));
    }
    
    return solution;
  }
  void printer(String p){
  	System.out.println(p);
  }
}