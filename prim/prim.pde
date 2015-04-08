/*
    Primzahlen Finden mit dem Sieb

    By JanO MnemoniX
*/
boolean[] zahlen;
int pointer = 2;
int tmpPointer = 1;
int start = 2;
int erg = 0;
boolean blinck = true;
// ------------------------------------------------------------ Settings
int summe = 40000;
int linebreak = 250;

int multX = 5;
int multY = 5;
int movX = 15;
int movY = 40;

int numSizeX = 5;
int numSizeY = 5;

boolean txt = false;
color txtclr = color(0);

color prim     = color(0,150,150);
color norm     = color(0, 50, 50);

color normNeu  = color(255, 50, 0);
color normNoch = color( 50,  0, 0);

color pter1    = color(255,255,  0);
color pter2    = color(  0,  0,255);

boolean strokes = true;

boolean animation = true;
int startAnimationAt = 7;
boolean statusBar = true;

// ------------------------------------------------------------ Setup
void setup(){
  size(1600, 900);
  background(10);
  if(!strokes) noStroke();
  textSize(8);
  
  frameRate(1200);
  
  zahlen = new boolean[summe];
  
  for (int i = 0 ; i < zahlen.length ; i++){
    zahlen[i] = true;
  }
  
  zahlen[0] = false;
  zahlen[1] = false;
  if(!animation){
    for( int i = start ; i <= summe ; i++){
      for(int j = 2 ; j < summe ; j++){
        int erg = i*j;
        if(erg < zahlen.length) zahlen[erg] = false;
        else break;
      }
    }
  } else {
    for( int i = start ; i < startAnimationAt ; i++){
      for(int j = 2 ; j < summe ; j++){
        int erg = i*j;
        if(erg < zahlen.length) zahlen[erg] = false;
        else break;
      }
    }
    pointer = startAnimationAt;
  }
  
  drawNrs();
}
// ------------------------------------------------------------ Draw
void draw(){
  if(statusBar){
    fill(color(0,50,50));
    rect(15, 5, 200, 25, 7);
    fill(color(0,150,150));
    textSize(15);
    text("Pointer-Prim: "+pointer, 25, 25);
  }
  if(animation){
//    noStroke();
//    if(blinck)fill(pter1);
//    else fill(pter2);
    fill(pter19);
    drawNr(pointer);
//    blinck = !blinck;
//    if(strokes) stroke(0);

    tmpPointer++;
    erg = pointer*tmpPointer;
    if(erg < summe){
      
      if(zahlen[erg]) fill(normNeu);
      else fill(normNoch);
      drawNr(erg);
      
      zahlen[erg] = false;
      
    } else {
      pointer++;
      while(zahlen[pointer] != true){
        pointer++;
      }
      tmpPointer = 2;
      drawNrs();
      //if(pointer >= 17)frameRate(60);
      if(pointer*2 >= summe){
        animation = false;
        println("ani aus!!!!");
      }
    }
  }
}
// ------------------------------------------------------------ Funktionen
void drawNr(int x, int y){
  ellipse(x,y,numSizeX,numSizeY);
}
void drawNr(int nr){
  int y = ((nr/linebreak)*multY)+movY;
  int x = ((nr%linebreak)*multX)+movX;
    
  drawNr(x,y);
  if(txt){
    fill(txtclr);
    text(""+(nr%100), x-5, y+3);
  } 
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









