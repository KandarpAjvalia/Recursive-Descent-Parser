import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

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

    private static int current, limit;

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        BufferedReader input = new BufferedReader(new FileReader(file));
        String currentLine = input.readLine();
        FileWriter out = null;
        try {
            out = new FileWriter("output.txt");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter print = new PrintWriter(out);

        while (currentLine != null) {
            current = 0;
            limit = currentLine.length();


            if(A(currentLine)) {
                print.println("The string \"" + currentLine + "\" is in the language.");
            }
            else {
                print.println("The string \"" + currentLine + "\" is not in the language.");
            }
            currentLine = input.readLine();
        }
        print.close();
    }

    private static boolean A(String str) {
        if (I(str)) {
            if (current < limit && str.charAt(current) == '=') {
                current++;
                return E(str);
            }
        }
        return E(str);
    }

    private static boolean E(String str) {
        if(P(str)) {
            if(O(str)){
                return P(str);
            }
            return true;
        }
        return false;
    }

    private static boolean O(String str) {
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

    private static boolean P(String str) {
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
            else return L(str);
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

    private static boolean I(String str) {
        if(current < limit && C(str)) {
            if(current < limit && I(str)) {
                return true;
            }
            return true;
        }
        return false;
    }

    private static boolean L(String str) {
        if(current < limit && D(str)) {
            if(current < limit && L(str)) {
                return true;
            }
            return true;
        }
        return false;
    }

    private static boolean U(String str) {
        if(current < limit) {
            char u = str.charAt(current);
            if(u == '+' || u == '-' || u == '!') {
                current++;
                return true;
            }
        }
        return false;
    }

    private static boolean C(String str) {
        if(current < limit) {
            char c = str.charAt(current);
            if(c >= 'a' && c <= 'z') {
                current++;
                return true;
            }
        }
        return false;
    }

    private static boolean D(String str) {
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
