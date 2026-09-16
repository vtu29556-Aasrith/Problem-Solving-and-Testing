import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (matches(word, pattern)) {
                result.add(word);
            }
        }
        
        return result;
    }

    private boolean matches(String word, String pattern) {
        int[] pToW = new int[26];
        int[] wToP = new int[26];
        
        Arrays.fill(pToW, -1);
        Arrays.fill(wToP, -1);

        for (int i = 0; i < pattern.length(); i++) {
            int pChar = pattern.charAt(i) - 'a';
            int wChar = word.charAt(i) - 'a';

            // Check if existing mappings are consistent
            if (pToW[pChar] == -1 && wToP[wChar] == -1) {
                pToW[pChar] = wChar;
                wToP[wChar] = pChar;
            } else if (pToW[pChar] != wChar || wToP[wChar] != pChar) {
                return false;
            }
        }

        return true;
    }
}