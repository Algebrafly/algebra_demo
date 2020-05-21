package com.algebra.demo.study.ballgame;

/**
 * @author al
 * @date 2020/5/20 14:19
 * @description
 */
public class Game {

    /**
     * 当前分数
     */
    private int itsScores = 0;

    /**
     * 投掷次数对应的分数
     */
    private int[] itsThrows = new int[21];

    private int itsCurrentThrow = 0;

    private int itsCurrentFrame = 1;

    private boolean firstThrow = true;

    public int getItsCurrentFrame() {
        return itsCurrentFrame;
    }

    public int getItsScores() {
        return scoreForFrame(getItsCurrentFrame() - 1);
    }

    /**
     * 模拟选手每一次投掷得分操作
     * @param pins 集中瓶子数目
     */
    public void add(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
        itsScores += pins;
        adjustCurrentFrame(pins);
    }

    private void adjustCurrentFrame(int pins) {
        if (firstThrow) {
            if (pins == 10) {
                // 一次全中
                itsCurrentFrame++;
            } else {
                firstThrow = false;
            }
        } else {
            firstThrow = true;
            // 最后一次投掷完成后+1
            itsCurrentFrame++;
        }
        itsCurrentFrame = Math.min(11,itsCurrentFrame);
    }

    /**
     * 计算每轮游戏的分数
     *
     * @param frame 游戏轮次
     * @return 游戏分数
     */
    public int scoreForFrame(int frame) {
        int ball = 0;
        int score = 0;
        for (int currentFrame = 0; currentFrame < frame; currentFrame++) {
            int firstThrow = itsThrows[ball++];
            if (firstThrow == 10) {
                // 全中
                score += 10 + itsThrows[ball] + itsThrows[ball + 1];
            } else {
                int secondThrow = itsThrows[ball++];
                int frameScore = firstThrow + secondThrow;
                if (frameScore == 10) {
                    // 补中
                    score += frameScore + itsThrows[ball];
                } else {
                    score += frameScore;
                }
            }
        }
        return score;
    }


}
