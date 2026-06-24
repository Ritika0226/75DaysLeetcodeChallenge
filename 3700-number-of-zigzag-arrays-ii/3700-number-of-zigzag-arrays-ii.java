class Solution {
    static final long MOD = 1_000_000_007L;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int sz = 2 * m;
        long[] base = new long[sz];
        for (int v = 1; v <= m; v++) {
            base[v - 1] = v - 1;
            base[m + v - 1] = m - v;
        }
        long[][] T = new long[sz][sz];
        for (int v = 1; v <= m; v++) {
            for (int u = 1; u < v; u++) {
                T[v - 1][m + u - 1] = 1;
            }
            for (int u = v + 1; u <= m; u++) {
                T[m + v - 1][u - 1] = 1;
            }
        }
        long[][] P = power(T, n - 2);
        long[] res = multiply(P, base);
        long ans = 0;
        for (long x : res) ans = (ans + x) % MOD;
        return (int) ans;
    }
    private long[] multiply(long[][] a, long[] v) {
        int n = a.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + a[i][j] * v[j]) % MOD;
            }
            res[i] = sum;
        }
        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                long val = a[i][k];
                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) continue;
                    res[i][j] = (res[i][j] + val * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }
    private long[][] power(long[][] mat, long exp) {
        int n = mat.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = multiply(res, mat);
            mat = multiply(mat, mat);
            exp >>= 1;
        }
        return res;
    }
}