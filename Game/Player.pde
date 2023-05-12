public class Player extends Animation{
  int lives;
  boolean onPlatform, inPlace;
  PImage[] standLeft;
  PImage[] standRight;
  PImage[] jumpLeft;
  PImage[] jumpRight;
  
  public Player(PImage image, float scale){
    super(image, scale);
    lives = 3;
    direction = RIGHT_FACING;
    onPlatform = true;
    inPlace = true;
    
    standLeft = new PImage[1];
    standLeft[0] = loadImage("platformChar_S.jpg");
    
    standRight = new PImage[1];
    standRight[0] = loadImage("platformChar_S.jpg");
    
    jumpLeft = new PImage[1];
    jumpLeft[0] = loadImage("platformChar_L.jpg");
    
    jumpRight = new PImage[1];
    jumpRight[0] = loadImage("platformChar_R.jpg");
    
    moveLeft = new PImage[2];
    moveLeft[0] = loadImage("platformChar_L.jpg");
    moveLeft[1] = loadImage("platformChar_S.jpg");
    
    moveRight = new PImage[2];
    moveRight[0] = loadImage("platformChar_R.jpg");
    moveRight[1] = loadImage("platformChar_S.jpg");
    
    currentImages = standRight;
  }
  
  public void selectCurrentImages(){
    if(direction == RIGHT_FACING){
      if(inPlace){
        currentImages = standRight;
      }
      else if(!onPlatform){
        currentImages = jumpRight;
      }
      else{
        currentImages = moveRight;
      }
    }
    
    else if(direction == LEFT_FACING){
      if(inPlace){
        currentImages = standLeft;
      }
      else if(!onPlatform){
        currentImages = jumpLeft;
      }
      else{
        currentImages = moveLeft;
      }
    }
  }
  
  public void updateAnimation(){
    onPlatform = isOnPlatform(this, blocks);
    inPlace = changeX == 0 && changeY == 0;
    super.updateAnimation();
  }
  
  public void setDirection(){
    if(changeX > 0){
      direction = RIGHT_FACING;
    }
    else if(changeX < 0){
      direction = LEFT_FACING;
    }
  }
}