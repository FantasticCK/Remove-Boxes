package com.CK;

public class Main {

    public static void main(String[] args) {
        new Solution().removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1});
    }
}

class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        if (n == 0)
            return 0;
        int[][][] dp = new int[n][n][100];

        int res = dfs(boxes, 0, n - 1, 0, dp);
        return res;
    }

    private int dfs(int[] boxes, int st, int ed, int k, int[][][] dp) {
        if (st > ed)
            return 0;

        if (dp[st][ed][k] != 0)
            return dp[st][ed][k];

        while (ed > st && boxes[ed] == boxes[ed - 1]) {
            ed--;
            k++;
        }

        dp[st][ed][k] = dfs(boxes, st, ed - 1, 0, dp) + (k + 1) * (k + 1);
        for (int i = ed - 1; i >= st; i--) {
            if (boxes[i] != boxes[ed])
                continue;

            dp[st][ed][k] = Math.max(dp[st][ed][k],
                    dfs(boxes, st, i, k + 1, dp) + dfs(boxes, i + 1, ed - 1, 0, dp));
        }

        return dp[st][ed][k];
    }
}