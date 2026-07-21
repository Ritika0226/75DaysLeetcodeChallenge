class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
          int ones = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                ones++;
            }
        }
        String t = "1" + s + "1";
        int n = t.length();
        int answer = ones;
        int i = 0;
        while (i < n) {
            if (t.charAt(i) == '0') {
                i++;
                continue;
            }
            int oneStart = i;
            while (i < n && t.charAt(i) == '1') {
                i++;
            }
                        int oneEnd = i - 1;
            if (oneStart == 0 || oneEnd == n - 1) {
                continue;
            }
            if (t.charAt(oneStart - 1) != '0' || t.charAt(oneEnd + 1) != '0') {
                continue;
            }
            int leftZeros = 0;
            int p = oneStart - 1;
            while (p >= 0 && t.charAt(p) == '0') {
                leftZeros++;
                p--;
            }
            int rightZeros = 0;
            p = oneEnd + 1;
            while (p < n && t.charAt(p) == '0') {
                rightZeros++;
                p++;
            }
            answer = Math.max(answer, ones + leftZeros + rightZeros);
        }
       return answer;
    }
}