class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int m = 0;
        for (int x : nums) m = Math.max(m, x);
        int[] f = new int[m + 1];
        for (int x : nums) f[x]++;
        long[] c = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j += i) {
                c[i] += f[j];
            }
        }
        long[] g = new long[m + 1];
        for (int i = m; i >= 1; i--) {
            g[i] = c[i] * (c[i] - 1) / 2;
            for (int j = i * 2; j <= m; j += i) {
                g[i] -= g[j];
            }
        }
        long[] p = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            p[i] = p[i - 1] + g[i];
        }
       int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;
            int l = 1, r = m;
            while (l < r) {
                int mid = (l + r) / 2;
                if (p[mid] >= k) r = mid;
                else l = mid + 1;
            }
            ans[i] = l;
        }
        return ans;
    }
}