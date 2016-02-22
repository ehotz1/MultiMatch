
package multimatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Ethan
 */
public class Logic {
    private ArrayList<Block> blocks;
    private GamePanel panel;
    private Timer roundTimer;
    private Score score;
    private UserInterface GUI;
    private int roundTime;
    private int tick;
    
    
    public Logic(GamePanel panel, UserInterface GUI, int rounds) {
        this.panel = panel;
        this.score = new Score(rounds);
        this.GUI = GUI;
        roundTime = 180;
        blocks = new ArrayList();
    }
    
    public void newRound() {
        newProblem();
        tick = roundTime;
        
        roundTimer = new Timer();
        roundTimer.scheduleAtFixedRate(new countdown(), 1000, 1000);
    }
    
    
    public void newProblem() {
        newBlocks();
        panel.setNewList(blocks);
        panel.drawNewBlocks(panel.getGraphics());
        
    }
    
    public void roundEnd() {
        score.nextRound();
        GUI.showScores();
    }
    
    public void gameEnd() {
        score.saveData();
    }
    
    public void newBlocks() {
        int[] array = new int[4];
        blocks.clear();
        array[0] = getOperand();
        array[1] = getOperand();
        int product = array[0] * array[1];
        if (product > 9) {
            array[2] = product/10;
            array[3] = product%10;
            for (int i = 0; i < 4; i++) {
                blocks.add(new Block(array[i], 0, 0));
            }
        } else {
            array[2] = product;
            for (int i = 0; i < 3; i++) {
                blocks.add(new Block(array[i], 0 ,0));
            }
        }
        Collections.shuffle(blocks);
    }
    
    public ArrayList<Block> getBlockList() {
        return this.blocks;
    }
    
    public Score getScore() {
        return this.score;
    }
    
    
    private int getOperand() {
        return (int)(Math.random() * 8) + 2;
    }
    
    public void checkAnswer() {
        int[] array = new int[4];
        int i = 0;
        int prod = 0;
        if (checkSnaps()) {
            for (SnapBox box : panel.getBoxes()) {
                array[i] = box.getContainingBlock().getNumber();
                i++;
            }
            if (panel.getBlocks().size() == 4) {
                String p = array[2] + "" + array[3];
                prod = Integer.parseInt(p);
            } else {
                prod = array[2];
            }
            if (checkMath(array[0], array[1], prod)) {
                newProblem();
                panel.repaint();
                panel.correct();
                score.addScore();
                GUI.setScore(score.getCurrentScore());
            } else {
                panel.incorrect();
                score.addError();
                GUI.setErrors(score.getCurrentErrors());
            }
        } else {
            //not all blocks in place
            panel.blockError();
        }
        
    }

    public boolean checkSnaps() {
        for (Block block : panel.getBlocks()) {
            if (!block.isSnapped()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkMath(int op1, int op2, int prod) {
        return (op1 * op2 == prod);
    }
    
    private class countdown extends TimerTask {
        

        @Override
        public void run() {
            tick--;
            GUI.setTime(convertClock(tick));
            if (tick == 0) {
                roundEnd();
                roundTimer.cancel();
            }
            
        }
        
        private String convertClock(int secs) {
            int minute = secs / 60;
            int seconds = secs % 60;
            String s;
            if (seconds < 10) {
                s = "0" + seconds;
            } else {
                s = seconds + "";
            }
            return minute + ":" + s;
            
        }
        
    }
}
