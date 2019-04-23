import java.applet.Applet;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.*;
import java.applet.*;
import java.awt.event.*;

public class Minesweeper extends Applet implements MouseListener{

    static int numArray[][] = new int [20][20];
    static boolean pClicked[][] = new boolean [20][20];
    static Image pic[] = new Image[12];
    static Queue<Integer> queue = new LinkedList<>();
    static int isVisited[][] = new int[20][20];
    static Integer xC, yC;
    static boolean won, lost;
    static Image pWon, pLost;
    static int numBombs = 60;
    //this array was used due to finishing most of the code and switching the image numbers of the mine and the flag
    static boolean flagged[][] = new boolean [20][20];

    public int checkBombs(int xVal, int yVal){
        int counter = 0;

        try{
            if(numArray[xVal-1][yVal-1] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal-1][yVal] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal-1][yVal+1] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal][yVal-1] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal][yVal+1] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal+1][yVal-1] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal+1][yVal] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        try{
            if(numArray[xVal+1][yVal+1] == 9){
                counter += 1;
            }
        }
        catch (Exception e){
        }
        return counter;
    }

    public void init(){
        addMouseListener(this);
        for (int x = 0; x < 12; x++) {
            pic[x] = getImage(getCodeBase(), x + ".png");
        }
        pWon = getImage(getCodeBase(), "win.jpg");
        pLost = getImage(getCodeBase(), "lost.jpg");

        int randX, randY;
        //for (int y = 0; y < 40; y ++ ){
        for (int y = 0; y < numBombs; y++){
            randX = (int) (Math.random()*20);
            randY = (int) (Math.random()*20);
            if (numArray[randX][randY] != 9) {
                numArray[randX][randY] = 9;
            }
            else{
                y -= 1;
            }
        }

        for (int a = 0; a < 20; a++){
            for (int b = 0; b < 20; b++){
                if (numArray[a][b] != 9) {
                    int numBombsOnPosition = checkBombs(a, b);
                    numArray[a][b] = numBombsOnPosition;
                }
            }
        }

    }

    public void clickReveal(Integer xNA, Integer yNA){
        //Queue<Integer> queue = new LinkedList<>();
        //Integer xC, yC;
        //List<Integer> xCList = new ArrayList<>();
        //List<Integer> yCList = new ArrayList<>();
        queue.add(xNA);
        queue.add(yNA);

        while(!queue.isEmpty()) {
            xC = queue.remove();
            yC = queue.remove();

            //LOGIC ERROR WITH xCList and yCList
            //if ((numArray[xC][yC] < 9) && !((xCList.contains(xC)) && (yCList.contains(yC)))){
            //if ((numArray[xC][yC] < 9) && (isVisited[xC][yC] != 1) ){
            //xCList.add(xC);
            //yCList.add(yC);

            if ((numArray[xC][yC] == 0) && (isVisited[xC][yC] != 1) ){
                pClicked[xC][yC] = true;



                if ((xC == 0) && (yC == 0)){
                    addBottom();
                    addRight();
                    addBottomRight();
                }
                else if ((xC == 0) && (yC == 19)){
                    addUp();
                    addRight();
                    addUpRight();

                }
                else if ((xC == 19) && (yC == 0)){
                    addLeft();
                    addBottom();
                    addBottomLeft();

                }
                else if ((xC == 19) && (yC == 19)){
                    addLeft();
                    addUp();
                    addUpLeft();

                }
                else if (xC == 0){
                    addRight();
                    addUp();
                    addBottom();
                    addUpRight();
                    addBottomRight();
                }
                else if (xC == 19){
                    addLeft();
                    addUp();
                    addBottom();
                    addUpLeft();
                    addBottomLeft();
                }
                else if (yC == 0){
                    addRight();
                    addLeft();
                    addBottom();
                    addBottomRight();
                    addBottomLeft();

                }
                else if (yC == 19){
                    addRight();
                    addLeft();
                    addUp();
                    addUpLeft();
                    addUpRight();
                }
                else{
                    addRight();
                    addLeft();
                    addUp();
                    addBottom();
                    addUpLeft();
                    addUpRight();
                    addBottomLeft();
                    addBottomRight();
                }

                //if(numArray[xC][yC] == 0){
                numArray[xC][yC] = 11;
                //}

                isVisited[xC][yC] = 1;


            }
            else if ((numArray[xC][yC] < 9) && (isVisited[xC][yC] != 1) ) {
                pClicked[xC][yC] = true;
                isVisited[xC][yC] = 1;
            }

        }
    }

    public void addLeft(){
        queue.add(xC - 1);
        queue.add(yC);
    }
    public void addRight(){
        queue.add(xC + 1);
        queue.add(yC);
    }
    public void addBottom(){
        queue.add(xC);
        queue.add(yC + 1);
    }
    public void addUp(){
        queue.add(xC);
        queue.add(yC - 1);
    }
    public void addUpLeft(){
        queue.add(xC - 1);
        queue.add(yC - 1);
    }
    public void addUpRight(){
        queue.add(xC + 1);
        queue.add(yC - 1);
    }
    public void addBottomLeft(){
        queue.add(xC - 1);
        queue.add(yC + 1);
    }
    public void addBottomRight(){
        queue.add(xC + 1);
        queue.add(yC + 1);
    }



    public void mouseClicked(MouseEvent e){

        int mouseX = e.getX() / 16;
        int mouseY = e.getY() / 16;

        if(e.getButton() == MouseEvent.BUTTON1) {

            pClicked[mouseX][mouseY] = true;

            Integer xInt = new Integer(mouseX);
            Integer yInt = new Integer(mouseY);

            clickReveal(xInt, yInt);

            if (numArray[mouseX][mouseY] == 0) {
                numArray[mouseX][mouseY] = 11;
            }
            if (numArray[mouseX][mouseY] == 9) {
                lost = true;
            }

            //Alternatively, we can index the mine positions when they were initialized.
            int counter = 0;
            int winCondition = numArray.length * numArray[0].length - numBombs;
            for (int xCheck = 0; xCheck < 20; xCheck++){
                for (int yCheck = 0; yCheck < 20; yCheck++) {
                    if (numArray[xCheck][yCheck] != 9){
                        if (pClicked[xCheck][yCheck] == true){
                            counter += 1;
                        }
                    }
                }
            }
            if (counter == winCondition){
                won = true;
            }

        }
        else if (e.getButton() == MouseEvent.BUTTON3){
            flagged[mouseX][mouseY] = !flagged[mouseX][mouseY];
        }

        repaint();
    }

    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

    public void paint(Graphics g){
        for (int xV = 0; xV < 20; xV++){
            for (int yV = 0; yV < 20; yV++){
                if (pClicked[xV][yV]) {
                    g.drawImage(pic[numArray[xV][yV]], xV * 16, yV * 16, 16, 16, this);
                }
                else {
                    g.drawImage(pic[0], xV*16, yV*16, 16, 16, this);
                }
                if (flagged[xV][yV]){
                    g.drawImage(pic[10], xV*16, yV*16, 16, 16, this);
                }
            }
        }
        if (won){
            g.drawImage(pWon, 0, 0, 400, 400, this);
        }
        if (lost){
            g.drawImage(pLost, 0, 0, 400, 400, this);
        }
    }
}