package FifteenPuzzleGame.GameLogic;
import javax.swing.*;
import java.util.Random;
import static FifteenPuzzleGame.Constants.*;

/**
 * Created by Oscar Norman & Bali Harko <br>
 * Date: 2020-10-23   <br>
 * Time: 12:02   <br>
 * Project: GameWindow.java <br>
 */

public abstract class GameLogic {

    // Compares given array with winning array
    public static boolean checkWinCon(JButton[][] buttons) {
        boolean isWin = true;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!buttons[i][j].getText().equals(createWinArray()[i][j].getText()))
                    isWin = false;
            }
        }
        return isWin;
    }

    public static JButton[][] createWinArray() {
        JButton[][] out = new JButton[ROWS][COLUMNS];

        int itr = 1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {

                if (itr == ROWS * COLUMNS) {
                    JButton noButton = new JButton(EMPTY_BUTTON_TEXT);
                    noButton.setVisible(false);
                    out[i][j] = noButton;
                    break;
                }

                out[i][j] = new JButton("" + itr++);
            }
        }
        return out;
    }

    public static boolean checkMovable(JButton[][] buttons, Coordinate position) {
        return buttons[position.getnNorth().getRow()][position.getnNorth().getColumn()].getText().equals(EMPTY_BUTTON_TEXT)
                || buttons[position.getnEast().getRow()][position.getnEast().getColumn()].getText().equals(EMPTY_BUTTON_TEXT)
                || buttons[position.getnSouth().getRow()][position.getnSouth().getColumn()].getText().equals(EMPTY_BUTTON_TEXT)
                || buttons[position.getnWest().getRow()][position.getnWest().getColumn()].getText().equals(EMPTY_BUTTON_TEXT);
    }

    public static JButton[][] move(JButton[][] buttons, Coordinate clickedButton) {
        Coordinate empty = Coordinate.getEmptyButton(buttons);
        String buttonText = buttons[clickedButton.getRow()][clickedButton.getColumn()].getText();

        buttons[clickedButton.getRow()][clickedButton.getColumn()].setText(EMPTY_BUTTON_TEXT);
        buttons[clickedButton.getRow()][clickedButton.getColumn()].setVisible(false);

        buttons[empty.getRow()][empty.getColumn()].setText(buttonText);
        buttons[empty.getRow()][empty.getColumn()].setVisible(true);

        return buttons;
    }

    // Checks if buttons is in its correct place
    public static boolean isCorrect(JButton[][] buttons,Coordinate position) {
        JButton[][] winArray = createWinArray();
        int r = position.getRow();
        int c = position.getColumn();
        return buttons[r][c].getText().equals(winArray[r][c].getText());
    }

    // Reads from Constants class and creates a 2d array with buttons,
    // and assigns each a random number
    public static JButton[][] buttonGridFill() {
        boolean[] isUsed = new boolean[ROWS * COLUMNS];
        JButton[][] out = new JButton[ROWS][COLUMNS];
        Random rand = new Random();
        int num;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {

                num = rand.nextInt(ROWS * COLUMNS);

                while (isUsed[num]) {
                    num = rand.nextInt(ROWS * COLUMNS);
                }
                isUsed[num] = true;

                if (num + 1 == ROWS * COLUMNS) {
                    JButton noButton = new JButton(EMPTY_BUTTON_TEXT);
                    noButton.setVisible(false);
                    out[i][j] = noButton;
                    continue;
                }
                out[i][j] = new JButton("" + (num + 1));
            }
        }
        return out;
    }
}


