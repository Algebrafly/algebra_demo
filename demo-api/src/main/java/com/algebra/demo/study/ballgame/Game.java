package com.algebra.demo.study.ballgame;

/**
 * @author al
 * @date 2020/5/20 14:19
 * @description
 */
public class Game {

    /**
     * 记录当前轮次
     */
    private int itsCurrentFrame = 1;

    /**
     * 标记-是否是每轮第一次投掷
     */
    private boolean firstThrowInFrame = true;

    private Scorer itsScorer = new Scorer();

    /**
     * 获取游戏当前正在进行的轮次
     *
     * @return 轮次数目
     */
    public int getItsCurrentFrame() {
        return itsCurrentFrame;
    }

    /**
     * 获取当前总分
     *
     * @return 分数
     */
    public int getItsScores() {
        return scoreForFrame(this.getItsCurrentFrame() - 1);
    }

    public int score(){
        return scoreForFrame(itsCurrentFrame);
    }

    private int scoreForFrame(int theFrame) {
        return itsScorer.scoreForFrame(theFrame);
    }

    /**
     * 模拟选手每一次投掷得分操作
     *
     * @param pins 击中瓶子数目
     */
    public void add(int pins) {
        itsScorer.addThrow(pins);
        this.adjustCurrentFrame(pins);
    }

    /**
     * 记录游戏轮次（一般两次投掷为一轮）
     *
     * @param pins 一次投掷击倒的瓶子数目
     */
    private void adjustCurrentFrame(int pins) {
        if (firstThrowInFrame) {
            if (adjustFrameForStrike(pins)) {
                firstThrowInFrame = false;
            }
        } else {
            firstThrowInFrame = true;
            // 最后一次投掷完成后+1
            advanceFrame();
        }
    }

    private void adjustCurrentFrameV2(int pins) {
        if(lastBallInFrame(pins)){
            advanceFrameV2();
        } else {
            firstThrowInFrame = false;
        }
    }

    private boolean lastBallInFrame(int pins){
        return strike(pins) || !firstThrowInFrame;
    }

    private boolean strike(int pins){
        return (firstThrowInFrame && pins==10);
    }

    private void advanceFrame() {
        itsCurrentFrame = Math.min(11, itsCurrentFrame + 1);
    }

    private void advanceFrameV2() {
        itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);
    }

    private boolean adjustFrameForStrike(int pins){
        if (pins == 10) {
            // 一次全中
            advanceFrame();
            return true;
        }
        return false;
    }

}
