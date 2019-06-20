#include <fstream>
#include <iostream>
#include <string>

using namespace std;

bool A(string &str);
bool E(string &str);
bool O(string &str);
bool P(string &str);
bool U(string &str);
bool I(string &str);
bool C(string &str);
bool L(string &str);
bool D(string &str);

int current, limit;

int main(int argc, char *argv[]) {
    ifstream fin("input.txt");
    while(fin.good()) {
        string str;
        getline(fin, str);
        limit = str.length();
        if (A(str)) {
            cout << "The string \"" << str << "\" is in the language.\n";
        }
        else {
            cout << "The string \"" << str << "\" is not in the language.\n";
        }
    }
    return 0;
}

bool A(string &str) {
    if(I(str)) {
        if(current < limit && str[current] == '=') {
            current++;
            return E(str);
        }
    }
    return E(str);
}

bool E(string &str) {
    if(P(str)) {
        if(O(str)) {
            return P(str);
        }
        return true;
    }
    return false;
}

bool O(string &str) {
    if(current < limit) {
        char o = str[current];
        if(o == '+' || o == '-' || o == '/') {
            current++;
            return true;
        }
        else if(o == '*') {
            current++;
            if(current < limit && str[current] == '*') {
                current++;
                return true;
            }
            return true;
        }
    }
    return false;
}

bool P(string &str) {
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
    if(current < limit && str[current] == '(') {
        current++;
        if(E(str)) {
            if(current < limit && str[current] == ')') {
                current++;
                return true;
            }
        }
    }
    return false;
}

bool I(string &str) {
    if(current < limit && C(str)) {
        if(current < limit && I(str)) {
            return true;
        }
        return true;
    }
    return false;
}

bool L(string &str) {
    if(current < limit && D(str)) {
        if(current < limit && L(str)) {
            return true;
        }
        return true;
    }
    return false;
}

bool U(string &str) {
    if(current < limit) {
        char u = str[current];
        if(u == '+' || u == '-' || u == '!') {
            current++;
            return true;
        }
    }
    return false;
}

bool C(string &str) {
    if(current < limit) {
        char c = str[current];
        if(c >= 'a' && c <= 'z') {
            current++;
            return true;
        }
    }
    return false;
}

bool D(string &str) {
    if(current < limit) {
        char d = str[current];
        if(d >= '0' && d <= '9') {
            current++;
            return true;
        }
    }
    return false;
}
