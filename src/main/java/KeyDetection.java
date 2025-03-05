import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class KeyDetection {

    final int FREE_MODE = 1, PLACE_MODE = 2, ERASE_MODE = 3;
    int mode;
    Terminal terminal;
    LineReader lineReader;
    int[] curr = new int[2];
    AlgoViz algoViz;

    public KeyDetection(AlgoViz algoViz) throws IOException {

        this.algoViz = algoViz;

        terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();

        mode = FREE_MODE;

    }

    public void start(int x, int y) throws IOException {

        this.curr[0] = x;
        this.curr[1] = y;

        algoViz.markCursor(curr);
        algoViz.printMap(algoViz.map);

        terminal.enterRawMode(); // Enable raw mode

        while (true) {

            int c = terminal.reader().read(); // Read a single character

            if (c == 'q' || c == 3) { // 'q' or CTRL+C to quit
                break;
            }

            if (c == 'e') {
                toggleErase();
            }

            if (c == 13 || c == 10) { // Check for Enter key (CR or LF)
                toggleMode();
//                System.out.println("Enter key pressed");
            }

            //Example of handling arrow keys.
            if (c == 27) { //escape character
                int second = terminal.reader().read();
                int third = terminal.reader().read();
                if (second == '[') {
                    switch (third) {
                        case 'A':
                            moveUp();
                            algoViz.markCursor(curr);
                            break;
                        case 'B':
                            moveDown();
                            break;
                        case 'C':
                            moveRight();
                            break;
                        case 'D':
                            moveLeft();
                            break;
                        default:
                            break;
                    }
                }

            }

            algoViz.markCursor(curr);
            algoViz.printMap(algoViz.map);
            printMode();
            printControls();

        }

        terminal.close(); // Restore terminal settings

    }

    private void printControls() {
        System.out.println("Press e to enable/disable Erase mode");
        System.out.println("Press Enter to toggle between Free & Place Mode");

    }

    private void printMode() {
        System.out.println();
        System.out.print("MODE : ");
        if (mode == PLACE_MODE) {
            System.out.println("Place Wall");
        } else if (mode == ERASE_MODE) {
            System.out.println("Erase Wall");
        } else {
            System.out.println("NONE");
        }
    }

    private void moveLeft() {

        int[] left = new int[]{curr[0], curr[1] - 1};

        if (!isValid(left)) {
            return;
        }

        if (mode == PLACE_MODE) {
            // mark the place
            algoViz.markWall(curr);
        } else if (mode == ERASE_MODE || algoViz.getCharAt(curr) != algoViz.WALL) {
            // erase
            algoViz.markSpace(curr);
        }

        // move left
        curr = left;

    }

    private void moveRight() {

        int[] right = new int[]{curr[0], curr[1] + 1};

        if (!isValid(right)) {
            return;
        }

        if (mode == PLACE_MODE) {
            // mark the place
            algoViz.markWall(curr);
        } else if (mode == ERASE_MODE || algoViz.getCharAt(curr) != algoViz.WALL) {
            // erase
            algoViz.markSpace(curr);
        }

        // move right
        curr = right;

    }

    private void moveDown() {

        int[] down = new int[]{curr[0] + 1, curr[1]};

        if (!isValid(down)) {
            return;
        }

        if (mode == PLACE_MODE) {
            // mark the place
            algoViz.markWall(curr);
        } else if (mode == ERASE_MODE || algoViz.getCharAt(curr) != algoViz.WALL) {
            // erase
            algoViz.markSpace(curr);
        }

        // move down
        curr = down;
    }

    private void moveUp() {

        int[] up = new int[]{curr[0] - 1, curr[1]};

        if (!isValid(up)) {
            return;
        }

        if (mode == PLACE_MODE) {
            // mark the place
            algoViz.markWall(curr);
        } else if (mode == ERASE_MODE || algoViz.getCharAt(curr) != algoViz.WALL) {
            // erase
            algoViz.markSpace(curr);
        }

        // move up
        curr = up;

    }

    private void toggleMode() {
        if (mode == FREE_MODE) {
            mode = PLACE_MODE;
        } else {
            mode = FREE_MODE;
        }
    }

    private void toggleErase() {

        if (mode == ERASE_MODE) {
            mode = FREE_MODE;
        } else {
            mode = ERASE_MODE;
        }

    }

    private boolean isValid(int[] coordinate) {
        return coordinate[0] < algoViz.N && coordinate[0] >= 0 && coordinate[1] < algoViz.N && coordinate[1] >= 0;
    }

}