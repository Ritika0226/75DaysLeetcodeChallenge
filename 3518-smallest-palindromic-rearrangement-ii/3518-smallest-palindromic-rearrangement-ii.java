class Solution {
static final long LIMIT = 1_000_001L;
   public String smallestPalindrome(String s, int k){
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        int[] half = new int[26];
        String mid = "";
        int len = 0;
        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];
            if ((freq[i] & 1) == 1)
                mid = String.valueOf((char) ('a' + i));
        }
        if (countWays(half) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + mid + right.toString();
    }

    long countWays(int[] cnt) {

        int total = 0;

        for (int x : cnt)
            total += x;

        long ans = 1;

        int rem = total;

        for (int x : cnt) {

            if (x == 0)
                continue;

            ans = multiplyCap(ans, comb(rem, x));

            if (ans >= LIMIT)
                return LIMIT;

            rem -= x;
        }

        return ans;
    }

    long comb(int n, int r) {

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            g = gcd(res, den);
            res /= g;
            den /= g;

            res *= num;

            if (res >= LIMIT)
                return LIMIT;
        }

        return res;
    }
    long multiplyCap(long a, long b) {
        if (a >= LIMIT || b >= LIMIT)
            return LIMIT;
        if (a > LIMIT / b)
            return LIMIT;
        return Math.min(LIMIT, a * b);
    }
long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}