package com.algebra.demo.study.ballgame;

/**
 * @projectName: algebra_demo
 * @author: Algebra
 * @description: 计分类
 * @date: 2020/5/25 22:16
 * @version: 1.0
 */
public class Scorer {

    private int ball;

    /**
     * 投掷次数对应的分数数组
     */
    private int[] itsThrows = new int[21];

    /**
     * 记录投掷次数
     */
    private int itsCurrentThrow = 0;

    /**
     * 模拟选手每一次投掷得分操作
     *
     * @param pins 击中瓶子数目
     */
    public void addThrow(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
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
