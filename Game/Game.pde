
final static float MOVE_SPEED = 6.0;
final static float BLOCK_SCALE = 64/64;
final static int BLOCK_SIZE = 64;
final static float GRAVITY = 0.4;
final static float JUMPING_SPEED = 10;

final static float SCREEN_WIDTH = BLOCK_SIZE * 16;
final static float SCREEN_HEIGHT = BLOCK_SIZE * 10;
final static float GROUND_LEVEL = SCREEN_HEIGHT - BLOCK_SIZE;

final static float RIGHT_M = 400;
final static float LEFT_M = 60;
final static float VERTICAL_M = 40;

final static int NEUTRAL = 0;
final static int RIGHT_FACING = 1;
final static int LEFT_FACING = 2;

Player p;
PImage character, tiles;
PImage selectImage;
PImage[] imageArray;
ArrayList <Sprite> blocks;

float viewX;
float viewY;

boolean isGameOver;
boolean lava;
boolean win = false;
boolean show = false;

int level = 1;
int timesPressed = 0;
int imageNumber;
int column, row;
int positionX, positionY;

int[] mapArray = {
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,20,20,20,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,0,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,0,0,0,13,13,13,13,13,13,13,13,20,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,78,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,20,13,13,20,13,13,13,13,13,13,13,20,20,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,3,0,0,13,13,13,13,0,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,0,0,0,0,0,0,0,0,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
0,0,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,5,5,5,5,5,5,5,5,0,0,0,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,3,3,3,0,0,0,0,3,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,3,3,3,3,3,3,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
3,3,3,3,3,3,3,3,3,3,3,3,19,19,3,3,3,3,19,19,19,19,19,19,19,19,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,3,3,3,3,3,3,3,3,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,3,3,3,3,3,3,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
};

int[] level2 = {
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,7,13,7,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,30,30,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,30,13,13,13,13,13,13,13,13,13,13,13,13,13,2,13,13,13,13,13,13,13,
2,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,2,2,2,13,13,30,13,13,30,13,13,30,13,13,2,13,13,13,45,45,13,13,7,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,30,30,13,13,13,13,30,30,13,13,13,13,13,13,13,13,13,13,30,13,13,13,13,13,30,13,13,13,13,13,13,13,13,13,13,3,13,13,13,13,13,13,13,
3,13,13,13,13,13,13,13,13,44,44,13,13,13,13,2,3,3,3,5,5,5,5,5,5,5,5,5,5,5,3,2,2,2,3,3,2,13,13,13,13,13,13,7,7,2,2,2,2,2,5,2,2,5,2,2,5,5,5,5,5,5,5,5,2,2,2,13,13,13,13,13,30,13,13,13,13,13,13,13,13,13,13,13,30,13,13,13,13,13,78,13,3,13,13,13,13,13,13,13,
3,2,2,2,2,5,2,2,13,13,13,13,44,13,2,3,3,3,3,19,19,19,19,19,19,19,19,19,19,19,3,3,3,3,3,3,3,13,13,13,13,13,13,13,13,3,3,3,3,3,19,3,3,19,3,3,19,19,19,19,19,19,19,19,3,3,3,2,2,2,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,2,2,2,2,2,2,2,3,13,13,13,13,13,13,13,
3,3,3,3,3,19,3,3,13,13,13,13,13,13,3,3,3,3,3,19,19,19,19,19,19,19,19,19,19,19,3,3,3,3,3,3,3,13,13,13,13,13,13,13,13,3,3,3,3,3,19,3,3,19,3,3,19,19,19,19,19,19,19,19,3,3,3,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,3,3,3,3,3,3,13,13,13,13,13,13,13,
};

int[] level3 = {
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,43,43,43,43,43,43,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,43,43,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,43,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,1,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
1,13,13,13,13,13,13,13,13,13,43,43,43,59,59,59,59,59,59,13,43,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,43,43,13,13,13,13,13,13,43,43,13,13,13,13,13,13,13,13,13,43,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
3,13,13,13,13,13,13,43,13,13,13,13,13,59,59,59,59,59,59,13,13,13,13,43,13,13,1,1,1,1,1,5,5,1,1,5,5,1,1,1,13,13,13,13,13,43,43,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,43,13,13,13,13,13,13,13,13,13,13,13,13,78,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
3,1,1,1,1,5,5,5,5,5,5,5,5,59,59,59,59,59,59,5,5,5,5,5,5,5,3,3,3,3,3,19,19,3,3,19,19,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1,1,1,1,1,1,13,13,1,1,1,1,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
3,3,3,3,3,19,19,19,19,19,19,19,19,59,59,59,59,59,59,19,19,19,19,19,19,19,3,3,3,3,3,19,19,3,3,19,19,3,3,3,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,3,3,3,3,3,3,13,13,3,3,3,3,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
};

void setup(){
  size(1024,640);
  imageMode(CENTER);
  character = loadImage("platformChar_S.jpg");
  p = new Player(character, 1.0);
  p.setBottom(GROUND_LEVEL);
  p.centerX = 110;
  blocks = new ArrayList <Sprite>();
  isGameOver = false;
  lava = false;
  win = false;
  
  tiles = loadImage("platformPack_tilesheet.png");
  column = tiles.width / BLOCK_SIZE;
  row = tiles.height / BLOCK_SIZE;
  generateImageArray();
  
  createLevel();

  viewX = 0;
  viewY = 0;
}

void generateImageArray(){
   imageArray = new PImage[column*row];
   for(int i = 0; i < row; i++) {
      for(int j = 0; j < column; j++) {
        selectImage = tiles.get(j*BLOCK_SIZE, i*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        imageArray[i*column+j] = selectImage;
      }
   }
}

void createBlocks(int[] mapArray){
  for(int rows = 0; rows < 10; rows++){
    for(int columns = 0; columns < 100; columns++){
      int index = mapArray[rows * 100 + columns]; 
        Sprite s = new Sprite(imageArray[index], BLOCK_SCALE);
        s.centerX =  BLOCK_SIZE/2 + columns * BLOCK_SIZE;
        s.centerY =  BLOCK_SIZE/2 + rows * BLOCK_SIZE;
        blocks.add(s);
    }
  }
}

void scroll(){

  float rBoundary = viewX + width - RIGHT_M;
  if(p.getRight() > rBoundary){
    viewX += p.getRight() - rBoundary;
  }
 
  float lBoundary = viewX + LEFT_M;
  if(p.getLeft() < lBoundary){
    viewX -= lBoundary - p.getLeft();
  }
  
  float bBoundary = viewY + height - VERTICAL_M;
  if(p.getBottom() > bBoundary){
    viewY += p.getBottom() - bBoundary;
  }
  
  float tBoundary = viewY + VERTICAL_M;
  if(p.getTop() < tBoundary){
    viewY -= tBoundary - p.getTop();
  }
  
  translate(-viewX, -viewY);
}

void draw(){
  background(185,255,250);
  scroll();
  for(Sprite s: blocks){
   s.display();
  }
  p.display();
  
  if(show){
    image(tiles, viewX + (tiles.width / 4), viewY + (tiles.height / 4), 896/2, 448/2);
    image(selectImage, mouseX + viewX, mouseY + viewY, 64, 64);
  }
  
  if(isGameOver){
    textSize(32);
    if(win){
      fill(0,255,0);
      if (level == 4){
        text("CONGRATULATIONS!\n ALL LEVELS ARE COMPLETED!!", viewX + width/2 - 100, viewY + height/2);
        text("SPACE to restart", viewX + width/2 - 100, viewY + height/2 + 100);
         level = 1;
      }else{
         text("Level passed!", viewX + width/2 - 100, viewY + height/2);
         text("SPACE to go to next level", viewX + width/2 - 100, viewY + height/2 + 100);
      }
    }
    else{
      fill(255,0,0);
      text("GAME OVER :(", viewX + width/2 - 100, viewY + height/2);
      text("SPACE to restart", viewX + width/2 - 100, viewY + height/2 + 100);
    }
  }
  
  if(!isGameOver){
    p.updateAnimation();
    resolveCollisions(p, blocks);
    isDead();
  }
  
}

void createLevel(){
  if(level == 1){
    createBlocks(mapArray);
  }
  if(level == 2){
    createBlocks(level2);
  }
  else if (level == 3){
    createBlocks(level3);
  }else{
    createBlocks(mapArray);
  }
}

void isDead(){
  boolean fall = p.getBottom() > GROUND_LEVEL;
  if(fall || lava || win){
    isGameOver = true;
  }
}

public boolean isOnPlatform(Sprite s, ArrayList <Sprite> walls){
  s.centerY += 5;
  ArrayList <Sprite> collision = checkCollisionList(s, walls);
  s.centerY -= 5;
  if(collision.size() > 0){
    return true;
  }
  else{
    return false;
  }
}

boolean checkCollision(Sprite s1, Sprite s2){
  boolean noXOverlap, noYOverlap;
  
  if(s2.image != imageArray[13] && s2.image != imageArray[72]){
    noXOverlap = s1.getRight() <= s2.getLeft() || s1.getLeft() >= s2.getRight();
    noYOverlap = s1.getBottom() <= s2.getTop() || s1.getTop() >= s2.getBottom();
  }
  else{
    noXOverlap = true;
    noYOverlap = true;
  }
  
  if(noXOverlap || noYOverlap){
    return false;
  }
  else{
    return true;
  }
}

public void resolveCollisions(Sprite s, ArrayList <Sprite> walls){

  s.changeY += GRAVITY;
  s.centerY += s.changeY;
  ArrayList <Sprite> collisions = checkCollisionList(s, walls);
  if(collisions.size() > 0){ 
    Sprite collided = collisions.get(0);
    if(collided.image == imageArray[5] || collided.image == imageArray[19]){
      lava = true;
    }
    if(collided.image == imageArray[78] || collided.image == imageArray[77] || 
        collided.image == imageArray[76] || collided.image == imageArray[75] || 
        collided.image == imageArray[79]){
      win = true;
      level++;
    }
    if(s.changeY > 0){ 
      s.setBottom(collided.getTop());
    }
    else if(s.changeY < 0){ 
      s.setTop(collided.getBottom());
    }
    s.changeY = 0;
  }
   
  s.centerX += s.changeX;
  collisions = checkCollisionList(s, walls);
  if(collisions.size() > 0){ 
    Sprite collided = collisions.get(0);
    if(collided.image == imageArray[5] || collided.image == imageArray[19]){
      lava = true;
    }
    if(collided.image == imageArray[78] || collided.image == imageArray[77] || 
        collided.image == imageArray[76] || collided.image == imageArray[75] || collided.image == imageArray[79]){
      win = true;
      level++;
    }
    if(s.changeX > 0){ 
      s.setRight(collided.getLeft());
    }
    else if(s.changeX < 0){ 
      s.setLeft(collided.getRight());
    }
  }
}

public ArrayList <Sprite> checkCollisionList (Sprite s, ArrayList <Sprite> list){
  ArrayList <Sprite> collision_list = new ArrayList <Sprite>();
  for(Sprite p: list){
    if(checkCollision(s, p)){
      collision_list.add(p);
    }
  }
  return collision_list;
}

void keyPressed(){
  if(keyCode == RIGHT){
    p.changeX = MOVE_SPEED;
  }
  else if(keyCode == LEFT){
    p.changeX = -MOVE_SPEED;
  }
  else if(keyCode == UP && isOnPlatform(p, blocks)){
    p.changeY = -JUMPING_SPEED;
  }
  else if(isGameOver && key == ' '){
    setup();
  }
  else if(key == 'e'){
    timesPressed++;
    if(timesPressed >= 2){
      show = false;
      timesPressed = 0;
    }
    else{
      show = true;
    }
  }
}

void keyReleased(){
  if(keyCode == RIGHT){
    p.changeX = 0;
  }
  else if(keyCode == LEFT){
    p.changeX = 0;
  }
}

void mousePressed(){
  if(show && mouseX + viewX >=0 && mouseX < (tiles.width / 2) && mouseY + viewY >= 0 && mouseY < (tiles.height / 2)){
    imageNumber = mouseY/(BLOCK_SIZE/2)*column + mouseX/(BLOCK_SIZE/2);
    selectImage = imageArray[imageNumber];
  }
  if(show &&  (mouseX  + viewX > (tiles.width / 2) + viewX || mouseY + viewY> (tiles.height / 2) + viewY)){
  
    mouseX += viewX;
    mouseY += viewY;
    positionX = mouseX / BLOCK_SIZE;
    positionY = mouseY / BLOCK_SIZE;
    if(level == 1){
      mapArray[positionY * 100 + positionX] = imageNumber;
      createBlocks(mapArray);
    }
    if(level == 2){
      level2[positionY * 100 + positionX] = imageNumber;
      createBlocks(level2);
    }
    else if (level == 3){
      level3[positionY * 100 + positionX] = imageNumber;
      createBlocks(level3);
    }
	else{
      mapArray[positionY * 100 + positionX] = imageNumber;
      createBlocks(mapArray);
    }
  }
 
}