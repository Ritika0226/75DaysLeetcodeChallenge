class Solution {
    public int zigZagArrays(int n, int l, int r) {
        long mod = 1_000_000_007L;
        int k = r - l + 1;
        long[] a = new long[k + 1];
        long[] b = new long[k + 1];
        for (int i = 1; i <= k; i++) {
            a[i] = i - 1;
            b[i] = k - i;
        }
        for (int len = 3; len <= n; len++) {
            long[] na = new long[k + 1];
            long[] nb = new long[k + 1];
            long[] pre = new long[k + 1];
            long[] suf = new long[k + 2];
            for (int i = 1; i <= k; i++) {
                pre[i] = (pre[i - 1] + b[i]) % mod;
            }
            for (int i = k; i >= 1; i--) {
                suf[i] = (suf[i + 1] + a[i]) % mod;
            }
            for (int i = 1; i <= k; i++) {
                na[i] = pre[i - 1];
                nb[i] = suf[i + 1];
            }
            a = na;
            b = nb;
        }
        long ans = 0;
        for (int i = 1; i <= k; i++) {
            ans = (ans + a[i] + b[i]) % mod;
        }
        return (int) ans;
    }
}