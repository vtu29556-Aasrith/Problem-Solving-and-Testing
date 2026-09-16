import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Map to store character -> latest index position
        HashMap<Character, Integer> map = new HashMap<>();
        
        int maxLength = 0;
        int left = 0; // Left boundary of the sliding window

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If character is already in the map and within the current window
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Update the last seen position of the character
            map.put(currentChar, right);

            // Calculate current valid window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}