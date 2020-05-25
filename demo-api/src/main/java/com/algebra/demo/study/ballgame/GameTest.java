package com.algebra.demo.study.ballgame;

/**
 * @author al
 * @date 2020/5/20 14:19
 * @description
 */
public class GameTest {

    public static void testTenthFrameSpare(Game g){
        for (int i = 0; i < 9; i++) {
            g.add(10);
        }
        g.add(9);
        g.add(1);
        g.add(1);
        System.out.println(g.getItsScores());
        System.out.println(g.getItsCurrentFrame());
    }



    public static void testPerfectGame(Game g){
        for (int i = 0; i < 12; i++) {
            g.add(10);
        }
        System.out.println(g.getItsScores());
        System.out.println(g.getItsCurrentFrame());
    }


    public static void main(String[] args) {

        Game g = new Game();

        testTenthFrameSpare(g);

    }


}
