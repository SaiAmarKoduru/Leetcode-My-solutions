import java.util.*;

class Solution {
    private int index;

    public List<String> braceExpansionII(String expression) {
        index = 0;
        Set<String> result = parseExpression(expression);
        
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    private Set<String> parseExpression(String s) {
        Set<String> result = parseTerm(s);

        while (index < s.length() && s.charAt(index) == ',') {
            index++;
            result.addAll(parseTerm(s));
        }

        return result;
    }


    private Set<String> parseTerm(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> next;

            if (s.charAt(index) == '{') {
                index++;
                next = parseExpression(s);
                index++; // skip '}'
            } else {
                next = new HashSet<>();
                next.add(String.valueOf(s.charAt(index)));
                index++;
            }

            result = concatenate(result, next);
        }

        return result;
    }

    private Set<String> concatenate(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}