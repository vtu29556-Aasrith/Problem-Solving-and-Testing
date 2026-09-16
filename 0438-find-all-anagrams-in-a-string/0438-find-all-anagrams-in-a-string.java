import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        // Anagram cannot exist if s is shorter than p
        if (sLen < pLen) {
            return result;
        }

        int[] pFreq = new int[26];
        int[] sFreq = new int[26];

        // Fill initial frequency map for p and the first window of s
        for (int i = 0; i < pLen; i++) {
            pFreq[p.charAt(i) - 'a']++;
            sFreq[s.charAt(i) - 'a']++;
        }

        // Check the first window
        if (Arrays.equals(pFreq, sFreq)) {
            result.add(0);
        }

        // Slide the window across string s
        for (int right = pLen; right < sLen; right++) {
            // Add current character to window
            sFreq[s.charAt(right) - 'a']++;
            
            // Remove character that left the window on the left
            sFreq[s.charAt(right - pLen) - 'a']--;

            // Compare frequency arrays
            if (Arrays.equals(pFreq, sFreq)) {
                result.add(right - pLen + 1);
            }
        }

        return result;
    }
}