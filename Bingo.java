import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Bingo {
  public static void main(String[] args) {

    Random rand = new Random();
    BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

    int[][]     number   = new int[5][5];
    boolean[][] hit_num  = new boolean[5][5];
    boolean[]   selected = new boolean[80];

    int[] counter = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    // counter[0] - [4] : row
    // counter[5] - [9] : column
    // counter[10],[11] : diagonal


    System.out.println("Let's start Bingo game!!\n");

    for (int i=0; i<80; i++) {
      selected[i] = false;
    }

    // -- Create Bingo card -- //
    for (int i=0; i<5; i++) {
    for (int j=0; j<5; j++) {

      while (true) {
        int rnd_num = rand.nextInt(15) + (1 + 15*j);
        if (selected[rnd_num] == true) continue;
        number[i][j] = rnd_num;
        selected[rnd_num] = true;
        break;
      }

      hit_num[i][j] = false;
    }}

    for (int i=0; i<80; i++) {
      selected[i] = false;
    }

    // Remove center
    selected[number[2][2]] = true;
    hit_num[2][2] = true;
    counter[2]++;
    counter[7]++;
    counter[10]++;
    counter[11]++;
    // ---------------------- //

    printBingoCard(number, hit_num);

    try {
      while (true) {
        String wait_push_enter_key = reader.readLine();

        int next_num = nextBingoNumber(rand, selected);
        System.out.printf("\nselected number : %d\n", next_num);

        boolean is_hit = checkHit(number, hit_num, counter, next_num);
        System.out.println(is_hit ? "Hit!" : "No hit...");
        printBingoCard(number, hit_num);

        if (isBingo(counter)) {
          System.out.println("BINGO!! Congratulation!!\n");
          break;
        }

        int last_one_num = countLastOne(counter);
        System.out.printf("The number of last one line：%d\n\n", last_one_num);
      }

    } catch (IOException e) {
      System.out.println("Input error...");
    }

  }

  static void printBingoCard(int[][] card, boolean[][] hit) {

    System.out.println("---------------------");
    System.out.println("| B | I | N | G | O |");
    System.out.println("---------------------");

    for (int i=0; i<5; i++) {
    for (int j=0; j<5; j++) {
      if (hit[i][j]) System.out.print("| ■ ");
      else System.out.printf("|%3d", card[i][j]);
    }
      System.out.println("|");
      System.out.println("---------------------");
    }
  }

  static int nextBingoNumber(Random rand, boolean[] selected) {

    int bingo_num;

    while (true) {
      bingo_num = rand.nextInt(75) + 1;
      if (selected[bingo_num]) continue;
      break;
    }

    selected[bingo_num] = true;
    return bingo_num;
  }

  static boolean checkHit(int[][] card, boolean[][] hit, int[] counter, int num) {

    boolean is_hit = false;

    for (int i=0; i<5; i++) {
    for (int j=0; j<5; j++) {
      if (card[i][j] == num) {
        hit[i][j] = true;
        counter[i]++;
        counter[j+5]++;
        if (i == j)     counter[10]++;
        if (i + j == 4) counter[11]++;
        is_hit = true;
      }
    }}

    return is_hit;
  }

  static boolean isBingo(int[] counter) {

    for (int count : counter) {
      if (count == 5) return true;
    }

    return false;
  }

  static int countLastOne(int[] counter) {

    int last_one_num = 0;
    for (int count : counter) {
      if (count == 4) last_one_num++;
    }

    return last_one_num;
  }
}
