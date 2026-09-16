class Solution {
    public boolean rotateString(String s, String goal) {
        // Lengths must match, and goal must be contained in s + s
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}