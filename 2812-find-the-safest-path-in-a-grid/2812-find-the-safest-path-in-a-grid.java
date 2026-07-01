import java.util.*;

class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
    int n;

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();

        int[][] dist = computeDistance(grid);

        int lo = 0, hi = 2 * n, ans = 0;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (canReach(dist, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    // Step 1: Multi-source BFS from all thieves
    private int[][] computeDistance(List<List<Integer>> grid) {
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (dist[nx][ny] > dist[x][y] + 1) {
                        dist[nx][ny] = dist[x][y] + 1;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return dist;
    }

    // Step 2: BFS check if path exists with safeness >= mid
    private boolean canReach(int[][] dist, int mid) {
        if (dist[0][0] < mid) return false;

        boolean[][] vis = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        vis[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if (x == n - 1 && y == n - 1) return true;

            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!vis[nx][ny] && dist[nx][ny] >= mid) {
                        vis[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return false;
    }
}