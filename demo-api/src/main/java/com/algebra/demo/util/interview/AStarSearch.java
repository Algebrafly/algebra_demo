package com.algebra.demo.util.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2020/11/13 10:52
 * @description 【小灰的算法之旅-java版实现】- A星寻路算法
 * 图像上从A->B的最小代价
 * 以估值高低来决定搜索优先次序的方法，被称为启发式搜索
 * <p>
 * 参考（原理）：https://blog.csdn.net/zwg739424406/article/details/88579499
 * ps. 很多游戏特别是rts，rpg类游戏，都需要用到寻路。寻路算法有深度优先搜索（DFS），广度优先搜索(BFS)，A星算法等，
 * 而A星算法是一种具备启发性策略的算法，效率是几种算法中最高的，因此也成为游戏中最常用的寻路算法。
 * (零基础)https://www.cnblogs.com/zhoug2020/p/3468167.html
 * https://zhuanlan.zhihu.com/p/99195511
 */
public class AStarSearch {

    public static final int[][] MAZE = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        // 设置起点和终点
        Grid start = new Grid(2, 1);
        Grid end = new Grid(3, 5);

        // 搜索迷宫终点
        Grid resultGrid = aStarSearch(start, end);

        // 回溯迷宫路径
        List<Grid> path = new ArrayList<>();
        while (resultGrid != null) {
            path.add(new Grid(resultGrid.x, resultGrid.y));
            resultGrid = resultGrid.parent;
        }

        // 输出迷宫路径
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[0].length; j++) {
                if (containGrid(path, i, j)) {
                    System.out.print("* ");
                } else {
                    System.out.print(MAZE[i][j] + " ");
                }
            }
            System.out.println();
        }


    }


    /**
     * A星搜索
     *
     * @param start 开始位置
     * @param end   终点
     * @return 终点
     */
    public static Grid aStarSearch(Grid start, Grid end) {
        List<Grid> openList = new ArrayList<>();
        List<Grid> closeList = new ArrayList<>();

        // 把起点加入openList
        openList.add(start);

        // 主循环，每一轮检查1个当前方格节点
        while (openList.size() > 0) {
            // 【关键-1】在openList中查找 F值最小的节点，将其作为当前方格节
            Grid currentGrid = findMinGird(openList);
            // 将当前方格节点从openList中移除
            openList.remove(currentGrid);
            // 当前方个点进入closedList
            closeList.add(currentGrid);

            // 找到所有临近节点
            List<Grid> neighbors = findNeighbors(currentGrid, openList, closeList);
            for (Grid grid : neighbors) {
                if (!openList.contains(grid)) {
                    // 【关键-2】 邻近节点不在openList中，标记父节点、E、G、H，并放入openList
                    grid.initGrid(currentGrid, end);
                    openList.add(grid);
                }
            }
            // 如果终点再openList中，直接返回终点格子
            for (Grid grid : openList) {
                if ((grid.x == end.x) && (grid.y == end.y)) {
                    return grid;
                }
            }
        }

        //openList用尽，仍然找不到终点，说明终点不可到达，返回空
        return null;
    }

    /**
     * 找到集合中F最小值
     * @param openList open
     * @return F值最小的格子
     */
    private static Grid findMinGird(List<Grid> openList) {
        Grid temp = openList.get(0);
        for (Grid grid : openList) {
            if (grid.f < temp.f) {
                temp = grid;
            }
        }
        return temp;
    }

    /**
     * 找邻近格子集合
     *
     * @param grid      本格子
     * @param openList  open
     * @param closeList close
     * @return 临近格子集合
     */
    private static List<Grid> findNeighbors(Grid grid, List<Grid> openList, List<Grid> closeList) {
        List<Grid> gridList = new ArrayList<>();
        if (isValidGrid(grid.x, grid.y - 1, openList, closeList)) {
            gridList.add(new Grid(grid.x, grid.y - 1));
        }
        if (isValidGrid(grid.x, grid.y + 1, openList, closeList)) {
            gridList.add(new Grid(grid.x, grid.y + 1));
        }
        if (isValidGrid(grid.x - 1, grid.y, openList, closeList)) {
            gridList.add(new Grid(grid.x - 1, grid.y));
        }
        if (isValidGrid(grid.x + 1, grid.y, openList, closeList)) {
            gridList.add(new Grid(grid.x + 1, grid.y));
        }
        return gridList;
    }

    /**
     * 格子是否可用
     *
     * @param x         x-axis
     * @param y         y-axis
     * @param openList  open
     * @param closeList close
     * @return boolean
     */
    private static boolean isValidGrid(int x, int y, List<Grid> openList, List<Grid> closeList) {
        // 是否超过边界
        if (x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            return false;
        }
        // 是否有障碍物
        if (MAZE[x][y] == 1) {
            return false;
        }
        // 是否已经在openList中
        if (containGrid(openList, x, y)) {
            return false;
        }
        // 是否已经在closeList中
        if (containGrid(closeList, x, y)) {
            return false;
        }
        return true;
    }

    /**
     * 判断集合中是否包含某格子
     * @param grids 格子集合
     * @param x x-axis
     * @param y y-axis
     * @return boolean
     */
    private static boolean containGrid(List<Grid> grids, int x, int y) {
        for (Grid grid : grids) {
            if ((grid.x == x) && (grid.y == y)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 格子对象
     */
    static class Grid {
        public int x;
        public int y;
        public int f;
        public int g;
        public int h;

        public Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            this.parent = parent;
            if (parent != null) {
                this.g = parent.g + 1;
            } else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            this.f = this.g + this.h;
        }

    }


}
