boolean[] zahlen = new boolean[6000];
int pointer = 0;
int linebreak = 90;

int start = 2;

int multX = 20;
int multY = 14;
int movX = 15;
int movY = 10;

int numSizeX = 20;
int numSizeY = 14;

color prim = color(255, 50, 50);
color norm = color( 50,100,206);

void setup(){
  size(1820, 1000);
  background(52);
  //noStroke();
  textSize(8);
  
  for (int i = 0 ; i < zahlen.length ; i++){
    zahlen[i] = true;
  }
  
  drawNrs();
}
void draw(){
  for( int i = start ; i< zahlen.length ; i++){
    for(int j = 2 ; j < 1000 ; j++){
      int erg = i*j;
      if(erg < zahlen.length) zahlen[erg] = false;
    }
  }
  drawNrs();
}

void drawNr(int x, int y){
  ellipse(x,y,numSizeX,numSizeY);
}
void drawNr(int nr){
  int y = ((nr/linebreak)*multY)+movY;
  int x = ((nr%linebreak)*multX)+movX;
    
  drawNr(x,y);
  fill(255);
  text(""+nr, x-7, y+3); 
}
void drawNrs(){
  for (int i = 0 ; i < zahlen.length ; i++){
    
    if(zahlen[i]) fill(prim);
    else fill(norm);
    
    drawNr(i);
    //print("line "+line+" i "+i);
  }
}


