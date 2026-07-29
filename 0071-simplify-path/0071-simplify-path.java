class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
         String[] parts = path.split("/");
         for (String part : parts) {
            if (part.equals("") || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder ans = new StringBuilder();

        for (String dir : stack) {
            ans.append("/").append(dir);
        }

        return ans.toString();
    }
}