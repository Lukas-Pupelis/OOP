public class Animation extends Sprite{
  PImage[] currentImages;
  PImage[] stand;
  PImage[] moveLeft;
  PImage[] moveRight;
  int direction;
  int index;
  int frame;
  
  public Animation(PImage image, float scale){
    super(image, scale);
    direction = NEUTRAL;
    index = 0;
    frame = 0;
  }
  
  public void updateAnimation(){
    frame++;
    if(frame % 4 == 0){
      setDirection();
      selectCurrentImages();
      setNextImage();
    }
  }
  
  public void setDirection(){
    if(changeX > 0){
      direction = RIGHT_FACING;
    }
    else if(changeX < 0){
      direction = LEFT_FACING;
    }
    else{
      direction = NEUTRAL;
    }
  }
  
  public void selectCurrentImages(){
    if(direction == RIGHT_FACING){
      currentImages = moveRight;
    }
    else if(direction == LEFT_FACING){
      currentImages = moveLeft;
    }
    else{
      currentImages = stand;
    }
  }
  
  public void setNextImage(){
    index++;
    if(index >= currentImages.length){
      index = 0; 
    }
    image = currentImages[index];
  }
  
}