/*
    Primzahlen Finden mit dem Sieb

    By JanO MnemoniX
*/
boolean[] zahlen;
int pointer = 2;
int tmpPointer = 1;
int start = 2;
// ------------------------------------------------------------ Settings
int summe = 6120;
int linebreak = 90;

int multX = 20;
int multY = 14;
int movX = 15;
int movY = 10;

int numSizeX = 20;
int numSizeY = 14;

boolean txt = true;
color txtclr = color(0);

color prim = color(255, 50, 50);
color norm = color( 50,100,206);
color pter = color(150,255,150);

boolean strokes = true;

// ------------------------------------------------------------ Setup
void setup(){
  size(1820, 1000);
  background(52);
  if(!strokes) noStroke();
  textSize(8);
  
  zahlen = new boolean[summe];
  
  for (int i = 0 ; i < zahlen.length ; i++){
    zahlen[i] = true;
  }
  
  zahlen[0] = false;
  zahlen[1] = false;
  
  drawNrs();
}
// ------------------------------------------------------------ Draw
void draw(){
  drawNrs();
  if(tmpPointer == summe){
    pointer++;
    tmpPointer = 0;
  }
  fill(pter);
  drawNr(pointer);
  
  for( int i = start ; i< zahlen.length ; i++){
//    fill(pter);
//    drawNr(i);
    for(int j = 2 ; j < 1000 ; j++){
      int erg = i*j;
      if(erg < zahlen.length) zahlen[erg] = false;
//      else break;
//      delay(5);
     //   sleep(200);
    }
  }
//  drawNrs();
  
/*  pointer++;
  
  drawNrs();
  fill(pter);
  drawNr(pointer);
  
  for(int j = pointer ; j < 1000 ; j++){
    int erg = pointer*j;
    
  }*/
  //delay(5);
}
// ------------------------------------------------------------ Funktionen
void drawNr(int x, int y){
  ellipse(x,y,numSizeX,numSizeY);
}
void drawNr(int nr){
  int y = ((nr/linebreak)*multY)+movY;
  int x = ((nr%linebreak)*multX)+movX;
    
  drawNr(x,y);
  fill(txtclr);
  if(txt)text(""+(nr%100), x-5, y+3); 
}
void drawNrs(){
  for (int i = 0 ; i < zahlen.length ; i++){
    
    if(zahlen[i]) fill(prim);
    else fill(norm);
    
    drawNr(i);
    //print("line "+line+" i "+i);
  }
}
void sleep(int dur){
  int now = millis();
  while(millis()-now < dur){}
  
}









