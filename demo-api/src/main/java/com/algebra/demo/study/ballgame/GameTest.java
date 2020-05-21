package com.algebra.demo.study.ballgame;

/**
 * @author al
 * @date 2020/5/20 14:19
 * @description
 */
public class GameTest {

    public static void testNormalSense(){
        Game g = new Game();
        g.add(3);
        g.add(7);
        g.add(3);
        System.out.println(g.getItsScores());
        System.out.println(g.scoreForFrame(1));
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

        testPerfectGame(g);

    }


}
