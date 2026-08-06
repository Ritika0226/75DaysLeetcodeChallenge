class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 1;
        int val = 0;

        for(int i =1;i<nums.length;i++){
            if(nums[val] != nums[i]){
                val++;
                int temp = nums[val];
                nums[val] = nums[i];
                nums[i] = temp;
                count++;
            }
        }
        return count;
    }
}