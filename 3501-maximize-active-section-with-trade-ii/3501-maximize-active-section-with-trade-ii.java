import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') totalOnes++;
        }
        List<Integer> type = new ArrayList<>();
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            type.add(s.charAt(i) - '0');
            start.add(i);
            end.add(j - 1);
            i = j;
        }
        int N = type.size();

        int[] posToSeg = new int[n];
        for (int i = 0; i < N; i++) {
            for (int j = start.get(i); j <= end.get(i); j++) {
                posToSeg[j] = i;
            }
        }
        int[] gain = new int[N];
        for (int i = 1; i < N - 1; i++) {
            if (type.get(i) == 1) {
                gain[i] =
                        (end.get(i - 1) - start.get(i - 1) + 1) +
                        (end.get(i + 1) - start.get(i + 1) + 1);
            }
        }
        int[] log = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            log[i] = log[i / 2] + 1;
        }
        int K = log[N] + 1;
        int[][] st = new int[K][N];
        for (int i = 0; i < N; i++) {
            st[0][i] = gain[i];
        }
        for (int j = 1; j < K; j++) {
            int len = 1 << j;
            int half = len >> 1;
            for (int i = 0; i + len <= N; i++) {
                st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + half]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int L = q[0];
            int R = q[1];
            int segL = posToSeg[L];
            int segR = posToSeg[R];
            if (segR - segL < 2) {
                ans.add(totalOnes);
                continue;
            }
            int maxGain = 0;
            maxGain = Math.max(maxGain,
                    eval(segL + 1, L, R, segL, segR,
                            type, start, end));
            maxGain = Math.max(maxGain,
                    eval(segR - 1, L, R, segL, segR,
                            type, start, end));
            if (segL + 2 <= segR - 2) {
                maxGain = Math.max(maxGain,
                        query(segL + 2, segR - 2, st, log));
            }

            ans.add(totalOnes + maxGain);
        }
        return ans;
    }
    private int query(int l, int r, int[][] st, int[] log) {
        if (l > r) return 0;
        int j = log[r - l + 1];
        return Math.max(st[j][l], st[j][r - (1 << j) + 1]);
    }
    private int eval(
            int idx,
            int L,
            int R,
            int segL,
            int segR,
            List<Integer> type,
            List<Integer> start,
            List<Integer> end) {
        if (idx <= segL || idx >= segR) return 0;
        if (type.get(idx) == 0) return 0;
        int leftLen;
        if (idx - 1 == segL) {
            leftLen = Math.max(0, end.get(idx - 1) - L + 1);
        } else {
            leftLen = end.get(idx - 1) - start.get(idx - 1) + 1;
        }
        int rightLen;
        if (idx + 1 == segR) {
            rightLen = Math.max(0, R - start.get(idx + 1) + 1);
        } else {
            rightLen = end.get(idx + 1) - start.get(idx + 1) + 1;
        }
 return leftLen + rightLen;
    }
}