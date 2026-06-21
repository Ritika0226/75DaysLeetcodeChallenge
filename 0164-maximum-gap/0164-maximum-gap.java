class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        int a = nums[0], b = nums[0];
        for (int x : nums) {
            a = Math.min(a, x);
            b = Math.max(b, x);
        }
        if (a == b) return 0;
        int n = nums.length;
        int c = Math.max(1, (b - a) / (n - 1));
        int d = (b - a) / c + 1;
        int[] e = new int[d];
        int[] f = new int[d];
        Arrays.fill(e, Integer.MAX_VALUE);
        Arrays.fill(f, Integer.MIN_VALUE);
        for (int x : nums) {
            int g = (x - a) / c;
            e[g] = Math.min(e[g], x);
            f[g] = Math.max(f[g], x);
        }
        int h = 0, i = a;
        for (int j = 0; j < d; j++) {
            if (e[j] == Integer.MAX_VALUE) continue;
            h = Math.max(h, e[j] - i);
            i = f[j];
        }
                return h;
    }
}