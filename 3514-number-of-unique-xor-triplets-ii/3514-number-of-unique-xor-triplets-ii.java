class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;
        boolean[] present = new boolean[MAX];
        for (int num : nums) {
            present[num] = true;
        }
        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;
        for (int step = 1; step <= 3; step++) {
            for (int xorVal = 0; xorVal < MAX; xorVal++) {
                if (!dp[step - 1][xorVal]) continue;
                for (int val = 0; val < MAX; val++) {
                    if (present[val]) {
                        dp[step][xorVal ^ val] = true;
                    }
                }
            }
        }
int count = 0;
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) count++;
        }
return count;
    }
}