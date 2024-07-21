package top100;

import java.util.Arrays;

public class No279_NumSquares {
    /*
    把 1, 4, 9, 16,⋯ 这些完全平方数视作物品体积，物品价值都是 1。由于每个数（物品）选的次数没有限制，所以本题是一个标准的完全背包问题
    定义 dfs(i, j) 表示从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j，最少要选的数字个数

    考虑第 i 个完全平方数 i^2 选或不选：
    不选：问题变成从前 i−1 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j，最少要选的数字个数，即 dfs(i, j) = dfs(i − 1, j)
    选：前提是 j ≥ i^2 。问题变成从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j − i^2 ，最少要选的数字个数，
    即 dfs(i, j) = dfs(i, j − i^2) + 1。注意这里是 i 而不是 i−1，因为我们可以继续选第 i 个完全平方数

    这两种情况取最小值，就得到了 dfs(i, j)，即
    dfs(i, j) = dfs(i − 1, j), j < i^2
    dfs(i, j) = min(dfs(i − 1, j), dfs(i, j − i^2) + 1), j >= i^2
    递归边界：dfs(0, 0) = 0，因为没有数可以选了，且要得到的数等于 0，那么答案为 0。如果 j > 0，那么 dfs(0, j) = ∞，这里用 ∞ 表示不合法的状态，
    从而保证上式中的 min 取到合法的状态。注意本题是一定有解的，因为 1 是完全平方数
    递归入口：由于 i^2 ≤n，所以 i ≤ ⌊Math.sqrt(n)⌋，所以递归入口为 dfs(⌊Math.sqrt(n)⌋, n)，也就是答案
    */
    private static final int[][] memo = new int[101][10001];

    static {
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
    }

    public int numSquares(int n) {
        return dfs((int) Math.sqrt(n), n);
    }

    private int dfs(int i, int left) {
        if (i == 0) {
            return left == 0 ? 0 : Integer.MAX_VALUE;
        }

        if (memo[i][left] != -1) {
            return memo[i][left];
        }

        if (left < i * i) {
            return dfs(i - 1, left);
        }

        memo[i][left] = Math.min(dfs(i - 1, left), dfs(i, left - i * i) + 1);
        return memo[i][left];
    }


    public static void main(String[] args) {
        int n = 12;
        No279_NumSquares solution = new No279_NumSquares();
        System.out.println(solution.numSquares(n));
    }
}
