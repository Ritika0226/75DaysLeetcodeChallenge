class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = -1;
        }
        long[] p = new long[n + 1];
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] + nums[i];
        }
        int len = 2 * k + 1;
        for (int i = k; i < n - k; i++) {
            long sum = p[i + k + 1] - p[i - k];
            a[i] = (int) (sum / len);
        }
        return a;
    }
}