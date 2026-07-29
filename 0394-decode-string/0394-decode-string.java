class Solution {
    public String decodeString(String s) {
         Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();

        StringBuilder currString = new StringBuilder();
        int currNum = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {

                currNum = currNum * 10 + (ch - '0');

            } else if (ch == '[') {

                countStack.push(currNum);
                stringStack.push(currString);

                currNum = 0;
                currString = new StringBuilder();

            } else if (ch == ']') {

                int repeat = countStack.pop();
                StringBuilder prev = stringStack.pop();

                for (int i = 0; i < repeat; i++) {
                    prev.append(currString);
                }

                currString = prev;

            } else {

                currString.append(ch);
            }
        }

        return currString.toString();
    }
}