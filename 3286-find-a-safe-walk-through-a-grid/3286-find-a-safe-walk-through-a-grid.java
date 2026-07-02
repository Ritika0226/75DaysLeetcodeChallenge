class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] bestHealth = new int[m][n];
        for (int[] row : bestHealth) Arrays.fill(row, -1);

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) return false;

        bestHealth[0][0] = startHealth;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.offer(new int[]{0, 0, startHealth});

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], h = cur[2];

            if (h < bestHealth[r][c]) continue;

            if (r == m - 1 && c == n - 1) return h >= 1;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int nh = h - grid.get(nr).get(nc);
                if (nh <= 0) continue;

                if (nh > bestHealth[nr][nc]) {
                    bestHealth[nr][nc] = nh;
                    pq.offer(new int[]{nr, nc, nh});
                }
            }
        }

        return false;
    }
}