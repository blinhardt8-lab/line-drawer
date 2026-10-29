/*
   Brett Linhardt
   AP Computer Science
   9/30/26
*/   

import java.util.Scanner;

public class LineDrawer {

Scanner input = new Scanner(System.in);

int x1 = 0;
int y1 = 0;
int x2 = 0;
int y2 = 0;

int sx = 0;
double sy = 0.0;

double slope = 0.0;

int canvasLength = 16; // Default value
int max = 0;

   public static void main(String[] args) {
      System.out.println("This program allows you to draw a line on a canvas with two coordinate points!");
      LineDrawer c = new LineDrawer();
      c.initialize();
   }
   
   public void initialize() {
      updateCanvas();
   }
   
   public void updateCanvas() {
      System.out.print("Select a value for canvas size (1-32): ");
      canvasLength = input.nextInt(); if (canvasLength > 32 || canvasLength < 1) { canvasLength = 16; System.out.println("Out of bounds. Value set to 16."); }
   
      max = canvasLength - 1;
   
      System.out.print("Select a value for x1 (0-" + max +"): ");
      x1 = input.nextInt(); if (x1 > max || x1 < 0) { x1 = 1; System.out.println("Out of bounds. Value set to 1."); }
      System.out.print("Select a value for y1 (0-" + max +"): ");
      y1 = input.nextInt(); if (y1 > max || y1 < 0) { y1 = 1; System.out.println("Out of bounds. Value set to 1."); }
      System.out.print("Select a value for x2 (0-" + max +"): ");
      x2 = input.nextInt(); if (x2 > max || x2 < 0) { x2 = 15; System.out.println("Out of bounds. Value set to 15."); }
      System.out.print("Select a value for y2 (0-" + max +"): ");
      y2 = input.nextInt(); if (y2 > max || y2 < 0) { y2 = 15; System.out.println("Out of bounds. Value set to 15."); }
   
      for (int i = 0; i < canvasLength; i++) {
         for (int j = 0; j < canvasLength; j++) {
            int specialTile = 0; // Does anything besides the line need to be drawn at our current position?
            if (j == 0) { specialTile = 1; } // If current x is 0, draw the y-axis tile ("| ").
            if (i == max) { specialTile = 2; } // If current y is the maximum, draw the x-axis tile ("__") (overpowers y-axis tile).
            drawTile(j, i, specialTile);
         }
         System.out.println();
      }
      System.out.println("\nTa-da!");
      
      sx = -1 * x1;
      sy = y1 + (slope * sx);
      
      System.out.print("Your slope equation is: y = ");
      System.out.printf("%.2f", slope);
      System.out.print("x ");
      if (sy < 0) { System.out.print("- "); } else { System.out.print("+ "); }
      System.out.printf("%.2f", Math.abs(sy));
      if (x1 > 0 || x2 < max) { System.out.print(" {" + x1 + ", " + x2 + "}"); }
      System.out.println();
      
      updateCanvas();
   }
   
   public void drawTile(int x, int y, int special) {
      slope = (double)(y2 - y1)/(double)(x2 - x1);
   
      int realY = Math.abs(y - max);
      sx = x - x1;
      sy = y1 + (slope * sx);
      
      if (Math.abs(realY - sy) <= 0.50 && sx >= 0 && sx <= x2) {
         System.out.print("$$");
      } else if (special == 1) { // Special tiles will be overwritten if the line intersects with them.
         System.out.print("| ");
      } else if (special == 2) {
         System.out.print("__");
      } else {
         System.out.print("  ");
      }
   }
}
