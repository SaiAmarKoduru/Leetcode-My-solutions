class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder first = new StringBuilder();
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            while (freq[i] >= 2) {
                first.append((char) ('a' + i));
                freq[i] -= 2;
            }
            if (freq[i] == 1) {
                mid = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(first);
        if (mid != 0) ans.append(mid);
        ans.append(first.reverse());

        return ans.toString();
    }
}