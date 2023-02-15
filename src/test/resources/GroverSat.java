import java.util.Arrays;

public class GroverSat {

    /*@ spec_public*/ static int maxNumLiterals = 2;
    /*@ spec_public*/ static int maxNumClauses = 3;

    private /*@ spec_public */static /*@ pure */ boolean isSolution(boolean[] guesses, int[][] cnf) {
        boolean res = true;
        for(int i = 0; i < cnf.length; ++i) {
            boolean clauseRes = false;
            for(int j = 0; j < cnf[i].length; ++j) {
                int literal = (cnf[i][j] > 0 ? cnf[i][j] : -cnf[i][j]) - 1;
                clauseRes |= cnf[i][j] > 0 ? guesses[literal] : !guesses[literal];
            }
            res &= clauseRes;
        }
        return res;
    }

    static private boolean contains(int[] array, int val) {
        for(int i = 0; i < array.length; ++i) {
            if(array[i] == val) {
                return true;
            }
        }
        return false;
    }

    /*@ requires cnf != null && (\forall int i; 0 <= i < cnf.length; cnf[i] != null);
      @ requires cnf.length == maxNumClauses && (\forall int i; 0 <= i < cnf.length; cnf[i].length == maxNumLiterals);
      @ ensures isSolution(\result, cnf);
     */
    public static boolean[] solve(int[][] cnf) {
        //cnf = new int[][]{new int[]{1, 2}, new int[]{-1, -2}, new int[]{-1, 2}};
        int numQubits = maxNumClauses + maxNumLiterals + 1;
        CircuitMock c = new CircuitMock(maxNumClauses + maxNumLiterals + 1);
        for (int i = 0; i < maxNumLiterals; i++) {
            c.h(i);
        }

        c.h(maxNumLiterals + maxNumClauses);
        c.z(maxNumLiterals + maxNumClauses);

        //Oracle
        for(int i = 0; i < maxNumClauses; i++) {
            if(i < cnf.length) {
                for (int j = 0; j < maxNumLiterals; ++j) {
                    if(j < cnf[i].length && contains(cnf[i], j + 1)) {
                        c.x(j);
                    }
                }
                c.ccx(0, 1, maxNumLiterals + i);
                c.x(maxNumLiterals + i);
                for (int j = 0; j < maxNumLiterals; ++j) {
                    if(j < cnf[i].length && contains(cnf[i], j + 1)) {
                        c.x(j);
                    }
                }
            }
        }
        c.cccx(maxNumClauses + maxNumLiterals + 1 - 4, maxNumClauses + maxNumLiterals + 1 - 3, maxNumClauses + maxNumLiterals + 1 - 2, maxNumClauses + maxNumLiterals + 1 - 1);
        for(int i = maxNumClauses - 1; i >= 0; i--) {
            if(i < cnf.length) {
                for (int j = 0; j < maxNumLiterals; ++j) {
                    if(j < cnf[i].length && contains(cnf[i], j + 1)) {
                        c.x(j);
                    }
                }
                c.x(maxNumLiterals + i);
                c.ccx(0, 1, maxNumLiterals + i);
                for (int j = 0; j < maxNumLiterals; ++j) {
                    if(j < cnf[i].length && contains(cnf[i], j + 1)) {
                        c.x(j);
                    }
                }
            }
        }


        //Diffuser
        for (int i = 0; i < maxNumLiterals; i++) {
            c.h(i);
        }
        for (int i = 0; i < maxNumLiterals; i++) {
            c.x(i);
        }
        c.ccx(0, 1, maxNumClauses + maxNumLiterals + 1 - 1);
        for (int i = 0; i < maxNumLiterals; i++) {
            c.x(i);
        }
        for (int i = 0; i < maxNumLiterals; i++) {
            c.h(i);
        }

        boolean[] res = new boolean[maxNumLiterals];
        for (int i = 0; i < 2; i++) {
            res[i] = c.measure(i);
        }
        return res;
    }

    private static void bruteForce() {
        int[maxNumClauses][maxNumLiterals]  cnf = null;
        for(int i = 0; i < 1 << (maxNumLiterals * maxNumClauses); i++) {
            cnf = new int[maxNumClauses][maxNumLiterals];
            for(int j = 0; j < maxNumClauses; j++) {
                for(int k = 0; k < maxNumLiterals; k++) {
                    cnf[j][k] = k + 1;
                    int mask = 1 << (j * maxNumLiterals + k);
                    if ((i & mask) != 0) {
                        cnf[j][k] *= -1;
                    }
                }
            }
            if (!isSolution(GroverSat.solve(cnf), cnf)) {
                System.out.println(Arrays.deepToString(cnf));
                System.out.println(Arrays.toString(GroverSat.solve(cnf)));
                System.out.println(isSolution(GroverSat.solve(cnf), cnf));
            }
        }
    }



    public static void main(String[] args) {
        int[][] cnf = new int[][]{new int[]{1, 2}, new int[]{-1, -2}, new int[]{-1, 2}};
        //System.out.println(Arrays.toString(GroverSat.solve(cnf)));
        //System.out.println(isSolution(GroverSat.solve(cnf), cnf));
        bruteForce();
    }
}