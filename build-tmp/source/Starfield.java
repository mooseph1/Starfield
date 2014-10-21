import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle [] star;
boolean lightspeed = false;
public void setup()
{
  size(1000,600);
  background(0);
  frameRate(30);
  star = new Particle[200];
  for(int a = 0; a < (star.length-2); a++)
  {
    star[a] = new NormalParticle();
  }
  star[198] = new JumboParticle();
  star[199] = new OddballParticle();
}

public void draw()
{
  if(lightspeed == false)
  {
    fill(0,0,0,50);
    rect(-1,-1,1001,601);
  }
  for(int b = 0; b < star.length; b++)
  {
    star[b].move();
    star[b].show();
  }
}

public void mouseClicked()
{
  lightspeed = !lightspeed; 
}

class NormalParticle implements Particle
{
  double particleX, particleY, speed, angle;
  int pigment1, pigment2, pigment3;
  NormalParticle()
  {
    particleX = 500;
    particleY = 300;
    speed = (Math.random()*3+3);
    angle = (Math.random()*(2*Math.PI));
    pigment1 = ((int)(Math.random()*255));
    pigment2 = ((int)(Math.random()*255));
    pigment3 = ((int)(Math.random()*255));
  }
  public void move()
  {
    particleX = particleX + (Math.cos(angle))*(speed);
    particleY = particleY + (Math.sin(angle))*(speed);
    if(particleX > 1005 || particleX < -5)
    {
      particleX = 500;
      particleY = 300;
      speed = (Math.random()*5+5);
      angle = (Math.random()*(2*Math.PI));
    }
    if(particleY > 605 || particleY < -5)
    {
      particleX = 500;
      particleY = 300;
      speed = (Math.random()*5+5);
      angle = (Math.random()*(2*Math.PI));
    }
  }
  public void show()
  {
    fill(pigment1, pigment2, pigment3);
    ellipse((int)particleX, (int)particleY, 10, 10);
  }
}

class JumboParticle extends NormalParticle
{
  public void show()
  {
    fill(0,180,255);
    ellipse((int)particleX, (int)particleY, 100, 100);
  }
}

interface Particle
{
  public void show();
  public void move();
}

class OddballParticle implements Particle
{
  int particleX, particleY;
  OddballParticle()
  {
    particleX = (int)(Math.random()*1001);
    particleY = (int)(Math.random()*601);
  }
  public void move()
  {
    if(particleX <= 15)
    {
      particleX = particleX + (int)(Math.random()*6);
      particleY = particleY + (int)(Math.random()*6-3);
    }
    if(particleX >= 9985)
    {
      particleX = particleX + (int)(Math.random()*6-6);
      particleY = particleY + (int)(Math.random()*6-3);
    }
    if(particleY <= 15)
    {
      particleX = particleX + (int)(Math.random()*6-3);
      particleY = particleY + (int)(Math.random()*6);
    }
    if(particleY >= 585)
    {
      particleX = particleX + (int)(Math.random()*6-3);
      particleY = particleY + (int)(Math.random()*6-6);
    }
    else
    {
      particleX = particleX + (int)(Math.random()*6-3);
      particleY = particleY + (int)(Math.random()*6-3);
    }
  }
  public void show()
  {
    fill(255);
    ellipse(particleX, particleY, 20, 20);
  }
}






  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
