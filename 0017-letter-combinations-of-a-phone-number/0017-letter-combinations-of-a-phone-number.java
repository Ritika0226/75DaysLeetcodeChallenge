class Solution {
    private List<String> result = new ArrayList<>();
     public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String[] map = {
            "", "", "abc", "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        backtrack(digits, 0, new StringBuilder(), map);
        return result;
    }
    private void backtrack(String digits, int index, StringBuilder current, String[] map) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        String letters = map[digits.charAt(index) - '0'];
        for (char ch : letters.toCharArray()) {
            current.append(ch);
            backtrack(digits, index + 1, current, map);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
}