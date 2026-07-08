class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        long MOD = 1000000007;
        StringBuilder nonZero = new StringBuilder();
        int n = s.length();
        int[] count = new int[n + 1];
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
             count[i + 1] = count[i];
             sum[i + 1] = sum[i];
             char ch = s.charAt(i);
             if (ch != '0') {
                nonZero.append(ch);
                count[i + 1]++;
                sum[i + 1] += ch - '0';
            }
        }
        int m = nonZero.length();
        long[] value = new long[m + 1];
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 0; i < m; i++) {
            int digit = nonZero.charAt(i) - '0';
            value[i + 1] = (value[i] * 10 + digit) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];
            int start = count[l];
            int end = count[r + 1];
            long x = 0;
            if (start < end) {

                int len = end - start;
                x = (value[end] - value[start] * pow10[len]) % MOD;

                if (x < 0) {
                    x += MOD;
                }
            }
             long digitSum = sum[r + 1] - sum[l];
             ans[i] = (int)((x * digitSum) % MOD);
        }
        return ans;
    }
}