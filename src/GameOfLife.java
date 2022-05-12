import java.util.Random;;

public class GameOfLife {

    public static void main(String[] args)  throws InterruptedException {

        gui gui = new gui();

        int [][] array = new int [71][71];

        //Field edge is getting filled up with '2'
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                    array[i][j] = 2;
            }
        }

        //Field gets filled up with '0' = dead cells
        for(int i = 1; i < array.length - 1; i++){
            for(int j = 1; j < array.length - 1; j++){
                array[i][j] = 0;
            }
        }
        
        //generates random numbers between 100 and 150
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 100;


        //generate list of 500 random numbers
        Random rand = new Random();
        int[] randomNumbers = new int[randomNumber];
        for(int i = 0; i < randomNumbers.length; i++){
            randomNumbers[i] = rand.nextInt(71);
        }

        //generate list of 500 random numbers
        int[] randomNumbers2 = new int[randomNumber];
        for(int i = 0; i < randomNumbers2.length; i++){
            randomNumbers2[i] = rand.nextInt(71);
        }

        //alive cells are getting filled up with '1'
        for(int i = 0; i < randomNumbers.length; i++){
            for(int j = 0; j < randomNumbers2.length; j++){
                array[randomNumbers[i]][randomNumbers2[j]] = 1;
            }
        }



        gui.printingOut(array);

    }

    public static void applyRules(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        for(int y = 1; y < array.length - 1; y++) {
            for(int x = 1; x < array.length - 1; x++) {
                int neighbors = neighborsCounter(array, x, y);
                if(array[x][y] == 1) {
                    if((neighbors < 2) || (neighbors > 3)) {
                        newArray[x][y] = 0;
                    }
                    if((neighbors == 2) || (neighbors == 3)) {
                        newArray[x][y] = 1;
                    }
                }
                else if(array[x][y] == 0){
                    if(neighbors == 3) {
                        newArray[x][y] = 1;
                    }
                    else {
                        newArray[x][y] = 0;
                    }
                }
            }
        }

        for(int i = 1; i < array.length - 1; i++){
            for(int j = 1; j < array.length - 1; j++) {
                array[i][j] = newArray[i][j];
            }
        }

    }

    public static int neighborsCounter(int[][] array, int x, int y) {
        int neighbors = 0;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(array[i][j] == 1) {
                    neighbors = neighbors + 1;
                }
            }
        }

        if(array[x][y] == 1) {
            neighbors = neighbors - 1;
        }
        return neighbors;
    }

}