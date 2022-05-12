import java.util.ArrayList;
import java.util.Random;


public class App {

    // Basic Java implementation of Conway's Game of Life using a 2D array.

    private static final int COLUMNS = 20;
    private static final int ROWS = 20;
    private static final boolean SHOW_GENERATION = false; 
    private static final int MILLISECS = 250;
    private static final String LIFE = "●";
    private static final String DEATH = ":";
    private static final String CORP = ".";
    private static final String NOTH = " ";

    public static void main(String... args) {
        String[][] mainGrid = generateGrid(COLUMNS, ROWS);
        int generationCounter = 0;
        boolean cloningGridAlternation = false;
        boolean isGridStuck = false;
        String[][] stuckGridClone = copyGrid(mainGrid);
        while(true) {
            clearScreen();
            printGrid(mainGrid);
            if (SHOW_GENERATION)
            	System.out.print("Gen " + (generationCounter++) + " ");
            String[][] nextGenGrid = copyGrid(mainGrid);          
            for (int i = 0; i < mainGrid.length; i++) {
                for (int j = 0; j < mainGrid[i].length; j++) {
                    int lifeForms = 0;
                    ArrayList<String> neighbors = new ArrayList<String>();
                    neighbors.add(mainGrid[Math.floorMod(i - 1, mainGrid.length)][Math.floorMod(j - 1, mainGrid[i].length)]);
                    neighbors.add(mainGrid[Math.floorMod(i - 1, mainGrid.length)][j]);
                    neighbors.add(mainGrid[Math.floorMod(i - 1, mainGrid.length)][Math.floorMod(j + 1, mainGrid[i].length)]);
                    neighbors.add(mainGrid[i][Math.floorMod(j - 1, mainGrid[i].length)]);
                    neighbors.add(mainGrid[i][Math.floorMod(j + 1, mainGrid[i].length)]);
                    neighbors.add(mainGrid[Math.floorMod(i + 1, mainGrid.length)][Math.floorMod(j - 1, mainGrid[i].length)]);
                    neighbors.add(mainGrid[Math.floorMod(i + 1, mainGrid.length)][j]);
                    neighbors.add(mainGrid[Math.floorMod(i + 1, mainGrid.length)][Math.floorMod(j + 1, mainGrid[i].length)]);
                    if (mainGrid[i][j].compareTo(CORP) == 0) {
                        nextGenGrid[i][j] = NOTH;
                    }

                    if (mainGrid[i][j].compareTo(DEATH) == 0) {
                        nextGenGrid[i][j] = CORP;
                    }
                    
                    for (int k = 0; k < neighbors.size(); k++)
                        if (neighbors.get(k).compareTo(LIFE) == 0)
                            lifeForms += 1;
    
                    if (mainGrid[i][j].compareTo(DEATH) == 0 ||
                        mainGrid[i][j].compareTo(CORP) == 0 ||
                        mainGrid[i][j].compareTo(NOTH) == 0) {
                        if (lifeForms == 3) {
                            nextGenGrid[i][j] = LIFE;
                        }
                    } else {
                        if (lifeForms < 2 || lifeForms > 3) {
                            nextGenGrid[i][j] = DEATH;
                        }
                    }
                }
            }

            if (cloningGridAlternation) {
                isGridStuck = isSameGrid(mainGrid, stuckGridClone);

                if (!isGridStuck)
                    stuckGridClone = copyGrid(mainGrid);
            }
            

            if (isGridStuck) {
                mainGrid = generateGrid(COLUMNS, ROWS);
                generationCounter = 0;
            } else {
                
                mainGrid = copyGrid(nextGenGrid);
            }

            // wait n milliseconds before repeating the loop
            try {
                Thread.sleep(MILLISECS);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            cloningGridAlternation = !cloningGridAlternation;
        }
    }

    private static boolean isSameGrid(String[][] firstGrid, String[][] secondGrid) {
        boolean sameGrid = true;

        for (int i = 0; i < firstGrid.length; i++)
            for (int j = 0; j < firstGrid[i].length; j++)
                if (firstGrid[i][j].compareTo(secondGrid[i][j]) != 0)
                    sameGrid = false;

        return sameGrid;
    }

    private static String[][] copyGrid(String[][] grid) {
        String[][] tempGrid = new String[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                tempGrid[i][j] = grid[i][j];
            }
        }

        return tempGrid;
    }

    public static String[][] generateGrid(int width, int height) {
        String[][] randomGrid = new String[height][width];
        Random rand = new Random();

        for (int i = 0; i < randomGrid.length; i++) {
            for (int j = 0; j < randomGrid[i].length; j++) {
                Boolean god = rand.nextBoolean();

                if (god)
                    randomGrid[i][j] = LIFE;
                else
                    randomGrid[i][j] = DEATH;
            }
        }

        return randomGrid;
    }
    
    private static void clearScreen() {
        for(int i = 0; i < 100; i++)
            System.out.println();
    }

    public static void printGrid(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(" " + matrix[i][j] + " ");
            }

            if ((i+1) == matrix.length && !SHOW_GENERATION)
            	System.out.print("");
            else
            	System.out.println();
        }
    }
}