class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String doubled = s + s;
        // Strip first and last characters and check if original string 's' exists inside
        return doubled.substring(1, doubled.length() - 1).contains(s);
    }
}