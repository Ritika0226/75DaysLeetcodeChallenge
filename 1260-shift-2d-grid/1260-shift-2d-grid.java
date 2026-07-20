class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
    int totalElements = rows * cols;
        k = k % totalElements;
        int[][] shiftedGrid = new int[rows][cols];
          for (int row = 0; row < rows; row++) {
              for (int col = 0; col < cols; col++) {
                int currentPosition = row * cols + col;
            int shiftedPosition = (currentPosition + k) % totalElements;
        int newRow = shiftedPosition / cols;
                int newCol = shiftedPosition % cols;
                shiftedGrid[newRow][newCol] = grid[row][col];
            }
        }
        List<List<Integer>> answer = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            List<Integer> currentRow = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                currentRow.add(shiftedGrid[row][col]);
            }
            answer.add(currentRow);
        }
       return answer;
    }
}