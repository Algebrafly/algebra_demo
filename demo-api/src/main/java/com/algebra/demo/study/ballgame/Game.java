package com.algebra.demo.study.ballgame;

/**
 * @author al
 * @date 2020/5/20 14:19
 * @description
 */
public class Game {

    /**
     * 投掷次数对应的分数数组
     */
    private int[] itsThrows = new int[21];

    /**
     * 记录投掷次数
     */
    private int itsCurrentThrow = 0;

    /**
     * 记录当前轮次
     */
    private int itsCurrentFrame = 1;

    /**
     * 标记-是否是每轮第一次投掷
     */
    private boolean firstThrowInFrame = true;

    private int ball;

//    private int firstThrow;
//
//    private int secondThrow;

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

    /**
     * 模拟选手每一次投掷得分操作
     *
     * @param pins 击中瓶子数目
     */
    public void add(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
        this.adjustCurrentFrame(pins);
    }

    /**
     * 记录游戏轮次（一般两次投掷为一轮）
     *
     * @param pins 一次投掷击倒的瓶子数目
     */
    private void adjustCurrentFrame(int pins) {
        if (firstThrowInFrame) {
            if (pins == 10) {
                // 一次全中
                itsCurrentFrame++;
            } else {
                firstThrowInFrame = false;
            }
        } else {
            firstThrowInFrame = true;
            // 最后一次投掷完成后+1
            itsCurrentFrame++;
        }
        itsCurrentFrame = Math.min(11, itsCurrentFrame);
    }

    /**
     * 计算每轮游戏的分数
     *
     * @param frame 游戏轮次
     * @return 游戏分数
     */
    public int scoreForFrame(int frame) {
        ball = 0;
        int score = 0;
        for (int currentFrame = 0; currentFrame < frame; currentFrame++) {
            if (strike()) {
                // 全中
                score += 10 + this.nextTwoBallsForStrike();
                ball++;
            } else if (spare()) {
                // 补中
                score += 10 + this.nextBallForSpare();
                ball += 2;
            } else {
                score += this.twoBallsInFrame();
                ball += 2;
            }
        }
        return score;
    }

    private boolean strike() {
        return itsThrows[ball] == 10;
    }

    private int nextTwoBallsForStrike() {
        return itsThrows[ball + 1] + itsThrows[ball + 2];
    }

    private boolean spare() {
        return (itsThrows[ball] + itsThrows[ball + 1]) == 10;
    }

    private int nextBallForSpare() {
        return itsThrows[ball+2];
    }

//    private int handleSecondThrow() {
//        int score = 0;
//        if (spare()) {
//            // 补中
//            ball += 2;
//            score += 10 + nextBall();
//        } else {
//            score += twoBallsInFrame();
//            ball += 2;
//        }
//        return score;
//    }

    private int twoBallsInFrame() {
        return itsThrows[ball] + itsThrows[ball + 1];
    }


}
