import java.io.*;

/*A -> I = E
        E -> P O P | P
        O -> + | - | * | / | **
        P -> I | L | UI | UL | (E)
        U -> + | - | !
        I -> C | CI
        C -> a | b | ... | y | z
        L -> D | DL
        D -> 0 | 1 | ... | 8 | 9
*/

public class RecursiveDescentParser {

    public static int current, limit;

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedReader input = new BufferedReader(new FileReader(file));
        String currentLine = input.readLine();
        while (currentLine != null) {
            current = 0;
            limit = currentLine.length();
            if(A(currentLine)) {
                System.out.println("The string \"" + currentLine + "\" is in the language.");
            }
            else {
                System.out.println("The string \"" + currentLine + "\" is not in the language.");
            }
            currentLine = input.readLine();
        }
    }

    private static boolean A(String line) {
        if (I(line)) {
            if (current < limit && line.charAt(current) == '=') {
                current++;
                if (E(line)) {
                    return true;
                }
                return false;
            }
        }
        if (E(line)) {
            return true;
        }
        return false;
    }

    public static boolean E(String str) {
        if(P(str)) {
            if(O(str)){
                if(P(str)) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean O(String str) {
        if(current < limit) {
            char o = str.charAt(current);
            if(o == '+' || o == '-' || o == '/') {
                current++;
                return true;
            }
            else if(o == '*') {
                current++;
                if(current < limit && str.charAt(current) == '*') {
                    current++;
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean P (String str) {
        if(I(str)) {
            return true;
        }
        if(L(str)) {
            return true;
        }
        if(U(str)) {
            if(I(str)) {
                return true;
            }
            else if(L(str)) {
                return true;
            }
            return false;
        }
        if(current < limit && str.charAt(current) == '(') {
            current++;
            if(E(str)) {
                if(current < limit && str.charAt(current) == ')') {
                    current++;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean I(String str) {
        if(current < limit && C(str)) {
            if(current < limit && I(str)) {
                return true;
            }
            return true;
        }
        return false;
    }

    public static boolean L(String str) {
        if(current < limit && D(str)) {
            if(current < limit && L(str)) {
                return true;
            }
            return true;
        }
        return false;
    }

    public static boolean U(String str) {
        if(current < limit) {
            char u = str.charAt(current);
            if(u == '+' || u == '-' || u == '!') {
                current++;
                return true;
            }
        }
        return false;
    }

    public static boolean C(String str) {
        if(current < limit) {
            char c = str.charAt(current);
            if(c >= 'a' && c <= 'z') {
                current++;
                return true;
            }
        }
        return false;
    }

    public static boolean D(String str) {
        if(current < limit) {
            char d = str.charAt(current);
            if(d >= '0' && d <= '9') {
                current++;
                return true;
            }
        }
        return false;
    }
}
