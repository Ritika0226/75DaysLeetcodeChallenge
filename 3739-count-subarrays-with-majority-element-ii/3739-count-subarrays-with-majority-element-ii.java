class Solution {

    class BIT {
        int[] bit;

        BIT(int n) {
            bit = new int[n + 2];
        }

        void update(int idx) {
            while (idx < bit.length) {
                bit[idx]++;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n + 2;

        BIT bit = new BIT(2 * n + 5);

        long ans = 0;
        int prefix = 0;

        // Prefix sum = 0 initially
        bit.update(offset);

        for (int num : nums) {
            if (num == target)
                prefix++;
            else
                prefix--;

            int idx = prefix + offset;

            ans += bit.query(idx - 1);

            bit.update(idx);
        }

        return ans;
    }
}