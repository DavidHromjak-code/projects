/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipog2algoritmy;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author PC
 */
public class OrezAlg {
   private static final Random RAND = new Random();  
    public static Polygon generujPolygonKonvexni()
    {int pocetBodu=(int)(Math.random()*(12-4)+4);
        List<Double> xPool = new ArrayList<>(pocetBodu);
        List<Double> yPool = new ArrayList<>(pocetBodu);
        
        for (int i = 0; i < pocetBodu; i++) {
         xPool.add(RAND.nextDouble()*75+20);
         yPool.add(RAND.nextDouble()*75+20);   
        }
        Collections.sort(xPool);
        Collections.sort(yPool);
        Double minX = xPool.get(0);
        Double maxX = xPool.get(pocetBodu - 1);
        Double minY = yPool.get(0);
        Double maxY = yPool.get(pocetBodu - 1);
        
        List<Double> xVec = new ArrayList<>(pocetBodu);
        List<Double> yVec = new ArrayList<>(pocetBodu);
         double lastTop = minX, lastBot = minX;

        for (int i = 1; i < pocetBodu- 1; i++) {
            double x = xPool.get(i);

            if (RAND.nextBoolean()) {
                xVec.add(x - lastTop);
                lastTop = x;
            } else {
                xVec.add(lastBot - x);
                lastBot = x;
            }
        }

        xVec.add(maxX - lastTop);
        xVec.add(lastBot - maxX);

        double lastLeft = minY, lastRight = minY;

        for (int i = 1; i < pocetBodu - 1; i++) {
            double y = yPool.get(i);

            if (RAND.nextBoolean()) {
                yVec.add(y - lastLeft);
                lastLeft = y;
            } else {
                yVec.add(lastRight - y);
                lastRight = y;
            }
        }

        yVec.add(maxY - lastLeft);
        yVec.add(lastRight - maxY);


        Collections.shuffle(yVec);

 
        List<Point2D.Double> vec = new ArrayList<>(pocetBodu);

        for (int i = 0; i < pocetBodu; i++) {
            vec.add(new Point2D.Double(xVec.get(i), yVec.get(i)));
        }


        Collections.sort(vec, Comparator.comparingDouble(v -> Math.atan2(v.getY(), v.getX())));


        double x = 0, y = 0;
        double minPolygonX = 0;
        double minPolygonY = 0;
        List<Point2D.Double> points = new ArrayList<>(pocetBodu);

        for (int i = 0; i < pocetBodu; i++) {
            points.add(new Point2D.Double(x, y));

            x += vec.get(i).getX();
            y += vec.get(i).getY();

            minPolygonX = Math.min(minPolygonX, x);
            minPolygonY = Math.min(minPolygonY, y);
        }

    
        double xShift = Math.random()*650;
        double yShift = Math.random()*650;

        for (int i = 0; i < pocetBodu; i++) {
            Point2D.Double p = points.get(i);
            points.set(i, new Point2D.Double(p.x + xShift, p.y + yShift));
        }
        int[] xpoints=new int[pocetBodu];
        int[] ypoints=new int[pocetBodu];
        for (int i = 0; i < pocetBodu; i++) {
            xpoints[i]=(int)points.get(i).x;
            ypoints[i]=(int)points.get(i).y;
        }
     return   new Polygon(xpoints, ypoints, pocetBodu);
        
    }
    public static Polygon generujPolygoObecny1()
    {
    int pocetBodu=(int)(Math.random()*(12-4)+4);
        double prumUhel = Math.PI * 2 / pocetBodu;
        int[] xpoints=new int[pocetBodu];
        int[] ypoints=new int[pocetBodu];
        double posunx=RAND.nextDouble()*650;
        double posuny=RAND.nextDouble()*650;
        for (int i = 0; i < pocetBodu; i++) {
            double aktualniUhel= prumUhel*i;
            double uhel = aktualniUhel + (RAND.nextDouble() - 0.5) * prumUhel * 0.25;
            double radius = RAND.nextDouble()*50+10;
        
        xpoints[i] = (int)(Math.cos(uhel) * radius+posunx); 
        ypoints[i] =(int)( Math.sin(uhel) * radius+posuny);
        
        }
        
        
     return   new Polygon(xpoints, ypoints, pocetBodu);
     
    }
    public static Polygon generujPolygoObecny2()
    {
        int pocetBodu=(int)(Math.random()*(12-4)+4);
        double prumUhel = Math.PI * 2 / pocetBodu;
        
        int[] xpoints=new int[pocetBodu];
        int[] ypoints=new int[pocetBodu];
        double posunX=RAND.nextInt(1000)-30;
        double posunY=RAND.nextInt(1000)-30;
        for (int i = 0; i < pocetBodu; i++) {
            double aktualniUhel= prumUhel*i;
            double uhel = aktualniUhel + (RAND.nextDouble() - 0.5) * prumUhel * 0.25;
            double radius = RAND.nextDouble()*50+10;
        
        double pokriveni=RAND.nextDouble()*100+10;
        xpoints[i] = (int)(Math.cos(uhel) * radius+posunX+pokriveni); 
        ypoints[i] =(int)( Math.sin(uhel) * radius+posunY+pokriveni);
        
        }
        
        
     return   new Polygon(xpoints, ypoints, pocetBodu);
     
    }
 public static boolean jeBodVKonvexPolygonu (Point test,Polygon polygon)
 {
 int[] xpoints=polygon.xpoints;
 int[] ypoints=polygon.ypoints;
 int minX=xpoints[0];
 int maxX=xpoints[0];
 int minY=ypoints[0];
 int maxY=ypoints[0];
     for (int i = 0; i < xpoints.length; i++) {
        if(xpoints[i]<minX)
        {
        minX=xpoints[i];
        }
        if(xpoints[i]>maxX)
        {
        maxX=xpoints[i];
        }
     }
     for (int j = 0; j < ypoints.length; j++) {
        if(ypoints[j]<minY)
        {
        minY=ypoints[j];
        }
        if(ypoints[j]>maxY)
        {
        maxY=ypoints[j];
        }
     }
    
     if(test.x>minX &&test.x<maxX&& test.y>minY&&test.y<maxY )
     {
     
        
     return true;
     }
     else 
      return false;   
}
public static boolean jeBodVObecnemPolygonu (Point test,Polygon polygon,boolean metoda)
{ if(metoda)
{
 int i;
      int j;
      boolean result = false;
      Point[] points=new Point[polygon.npoints];
      for (int k = 0; k < polygon.npoints; k++) {
         points[k]=new Point(polygon.xpoints[k], polygon.ypoints[k]);
     }
      for (i = 0, j = points.length - 1; i < points.length; j = i++) {
        if ((points[i].y > test.y) != (points[j].y > test.y) &&
            (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
          result = !result;
         }
      }
 return result;
}
else
{
return false;
}

}
}
