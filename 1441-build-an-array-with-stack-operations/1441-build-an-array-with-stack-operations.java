class Solution {
    public List<String> buildArray(int[] target, int n) {
         List<String> ans = new ArrayList<>();
         int curr = 1;
        int i = 0;
         while (i < target.length) {
            if (curr == target[i]) {
                ans.add("Push");
                i++;
            } else {
                ans.add("Push");
                ans.add("Pop");
            }
            curr++;
        
         }
         return ans;
    }
}