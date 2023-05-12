public class Sprite{
  PImage image;
  float centerX, centerY;
  float changeX, changeY;
  float imgW, imgH;
  
  public Sprite(String filename, float scale, float x, float y){
    image = loadImage(filename);
    imgW = image.width * scale;
    imgH = image.height * scale;
    centerX = x;
    centerY = y;
    changeX = 0;
    changeY = 0;
  }
  
  public Sprite(String filename, float scale){
    this(filename, scale, 0, 0);
  }
  
  public Sprite(PImage img, float scale){
    image = img;
    imgW = image.width * scale;
    imgH = image.height * scale;
    centerX = 0;
    centerY = 0;
    changeX = 0;
    changeY = 0;
  }
  
  public void display(){
    image(image, centerX, centerY);
  }
  
  public void update(){
    centerX += changeX;
    centerY += changeY;
  }
  
  void setLeft(float left){
    centerX = left + imgW/2;
  }
  
  float getLeft(){
    return centerX - imgW/2;
  }
  
  void setRight(float right){
    centerX = right - imgW/2;
  }
  
  float getRight(){
    return centerX + imgW/2;
  }
  
  void setTop(float top){
    centerY = top + imgH/2;
  }
  
  float getTop(){
    return centerY - imgH/2;
  }
  
  void setBottom(float bottom){
    centerY = bottom - imgH/2;
  }
  
  float getBottom(){
    return centerY + imgH/2;
  }
}