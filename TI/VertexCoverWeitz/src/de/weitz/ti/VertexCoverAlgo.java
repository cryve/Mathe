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

  VertexCoverAlgo(boolean edges[][]) {
    this.edges = edges;
    this.max = edges.length - 1;
  }

  // this is where you should write your code 
  int[] solve() {
    int solution[] = {1,2};
    String s = "";
    
    for( int i=0; i < edges.length ; i++){
    	s = s+"\n"+i+" )";
    	for(int j=0; j < edges[i].length ; j++) s=s+(edges[i][j]  ? " 1" : " 0");
    }

    
    System.out.println(s+"\n\n");
    
    return solution;
  }
}