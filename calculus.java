package IMath;

public class calculus {
    public static final double DELTA = 0.0001;
    public double limit(double val, String equation) {
        String[] eq = dissectEquation(equation);
        if (eq[3].equals("x")) {
            // linear or quad
            if (eq[4].equals("^")) {
                // quad
                double yval = Double.parseDouble(eq[2])*Math.pow(val,Double.parseDouble(eq[5]))+Double.parseDouble(eq[6]+eq[7]);
                return yval;
            }
            else {
                // linear
                double yval = Double.parseDouble(eq[2])*val+Double.parseDouble(eq[4]+eq[5]);
                return yval;
            }
        }
        else {
            // exp or inv
            if (eq[3].equals("/")) {
                //inv
                double low = val-DELTA;
                double high = val+DELTA;
                double lyval = Double.parseDouble(eq[2])/low+Double.parseDouble(eq[5]+eq[6]);
                double hyval = Double.parseDouble(eq[2])/high+Double.parseDouble(eq[5]+eq[6]);
                if (Math.abs(lyval-hyval) < DELTA*1000) {
                    double yval = (lyval+hyval)/2;
                    if (checkDELTA(yval)) {
                        if (yval < 0) {
                            yval = (int) (yval-DELTA*10);
                        }
                        else {
                            yval = (int) (yval+DELTA*10);
                        }
                    }
                    return yval;
                }
                else {
                    throw new ArithmeticException("There is no limit for " + equation + " at value, " + val + ".");
                }
            }
            else {
                //exp
                double yval = Math.pow(Double.parseDouble(eq[2]),val)+Double.parseDouble(eq[5]+eq[6]);
                return yval;
            }
        }
    }
    public String[] dissectEquation(String equation) {
        String[] ret;
        if (equation.indexOf("^") == -1) {
            if (equation.indexOf("/") == -1) {
                String[] retLinear = new String[6];
                if (!equation.substring(0,1).equals("y")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retLinear[0] = equation.substring(0,1);
                if (!equation.substring(1,2).equals("=")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retLinear[1] = equation.substring(1,2);
                if (equation.substring(2,3).equals("x")) {
                    retLinear[2] = "1";
                    retLinear[3] = "x";
                    retLinear[4] = equation.substring(3,4); // sign
                    retLinear[5] = equation.substring(4); // constant
                }
                else {
                    retLinear[2] = equation.substring(2,3);
                    retLinear[3] = equation.substring(3,4);
                    retLinear[4] = equation.substring(4,5); // sign
                    retLinear[5] = equation.substring(5); // constant
                }
                ret = retLinear;
            }
            else {
                String[] retInv = new String[7];
                if (!equation.substring(0,1).equals("y")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retInv[0] = equation.substring(0,1);
                if (!equation.substring(1,2).equals("=")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retInv[1] = equation.substring(1,2);

                retInv[2] = equation.substring(2,3);
                retInv[3] = equation.substring(3,4);
                retInv[4] = equation.substring(4,5);
                retInv[5] = equation.substring(5,6);
                retInv[6] = equation.substring(6);
                ret = retInv;
            }
        }
        else {
            if (equation.indexOf("^x") == -1) { // quadratic
                String[] retQuad = new String[8];
                if (!equation.substring(0,1).equals("y")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retQuad[0] = equation.substring(0,1);
                if (!equation.substring(1,2).equals("=")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retQuad[1] = equation.substring(1,2);

                if (equation.substring(2,3).equals("x")) { // y=x^2+3
                    retQuad[2] = "1";
                    retQuad[3] = "x";
                    retQuad[4] = "^";
                    retQuad[5] = equation.substring(4,5);
                    retQuad[6] = equation.substring(5,6);
                    retQuad[7] = equation.substring(6);
                }
                else { // y=2x^2+3
                    retQuad[2] = equation.substring(2,3);
                    retQuad[3] = equation.substring(3,4);
                    retQuad[4] = equation.substring(4,5);
                    retQuad[5] = equation.substring(5,6);
                    retQuad[6] = equation.substring(6,7);
                    retQuad[7] = equation.substring(7);
                }
                ret = retQuad;
            }
            else {
                String[] retExp = new String[8];
                if (!equation.substring(0,1).equals("y")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retExp[0] = equation.substring(0,1);
                if (!equation.substring(1,2).equals("=")) throw new IllegalArgumentException("Equations must start with \"y=\"");
                else retExp[1] = equation.substring(1,2);

                retExp[2] = equation.substring(2,3); // 2^x <- 2
                retExp[3] = equation.substring(3,4); // 2^x <- ^
                retExp[4] = equation.substring(4,5); // 2^x <- x
                retExp[5] = equation.substring(5,6); // 2^x+3 <- +
                retExp[6] = equation.substring(6); // 2^x+3 <- 3
                ret = retExp;
            }

        }

        return ret;
    }
    public double infiniteSum(double start, double ratio) {
        if (Math.abs(ratio) >= 1) throw new IllegalArgumentException("The absolute value of the ratio cannot be greater than or equal to 1.");
        double sum = start/(1-ratio);
        if (checkDELTA(sum)) {
            if (sum < 0) {
                sum = (int) (sum-DELTA*10);
            }
            else {
                sum = (int) (sum+DELTA*10);
            }
        }
        return sum;
    }
    public double contAddInfSum(double start, double ratio, int iterations) {
        double cur = start+ratio;
        for (int i = 0; i < iterations; i++) {
            cur*=ratio;
            cur+=start;
        }
        if (checkDELTA(cur)) {
            if (cur < 0) {
                cur = (int) (cur-DELTA*10);
            }
            else {
                cur = (int) (cur+DELTA*10);
            }
        }
        return cur;
    }

    public static boolean checkDELTA(double num) {
        return (num - DELTA < (int) num || num + DELTA > (int) num+1);
    }

    public double integral(double low, double high, String equation) {
        String[] eq = dissectEquation(equation);
        double dx = DELTA;
        if (eq[3].equals("x")) {
            // linear or quad
            if (eq[4].equals("^")) {
                // quad
                double total = 0;
                for (double i = low; i < high; i+=dx) {
                    total += (Double.parseDouble(eq[2])*Math.pow(i,Double.parseDouble(eq[5]))+Double.parseDouble(eq[6]+eq[7]))*dx;
                }
                if (checkDELTA(total)) {
                    if (total < 0) {
                        total = (int) (total-DELTA*10);
                    }
                    else {
                        total = (int) (total+DELTA*10);
                    }
                }
                return total;
            }
            else {
                // linear
                double total = 0;
                for (double i = low; i < high; i+=dx) {
                    total += (Double.parseDouble(eq[2])*i+Double.parseDouble(eq[4]+eq[5]))*dx;
                }
                if (checkDELTA(total)) {
                    if (total < 0) {
                        total = (int) (total-DELTA*10);
                    }
                    else {
                        total = (int) (total+DELTA*10);
                    }
                }
                return total;
            }
        }
        else {
            // exp or inv
            if (eq[3].equals("/")) {
                //inv
                double total = 0;
                for (double i = low; i < high; i+=dx) {
                    double l = i-DELTA;
                    double h = i+DELTA;
                    double lyval = Double.parseDouble(eq[2])/l+Double.parseDouble(eq[5]+eq[6]);
                    double hyval = Double.parseDouble(eq[2])/h+Double.parseDouble(eq[5]+eq[6]);
                    if (Math.abs(lyval-hyval) < DELTA*1000) {
                        double yval = (lyval+hyval)/2;
                        if (checkDELTA(yval)) {
                            if (yval < 0) {
                                yval = (int) (yval-DELTA*10);
                            }
                            else {
                                yval = (int) (yval+DELTA*10);
                            }
                        }
                        total += yval*dx;
                    }
                    else {
                        throw new ArithmeticException("There is no limit for " + equation + " at value, " + i + ".");
                    }
                }
                if (checkDELTA(total)) {
                    if (total < 0) {
                        total = (int) (total-DELTA*10);
                    }
                    else {
                        total = (int) (total+DELTA*10);
                    }
                }
                return total;
            }
            else {
                //exp
                double total = 0;
                for (double i = low; i < high; i+=dx) {
                    total += Math.pow(Double.parseDouble(eq[2]),i)+Double.parseDouble(eq[5]+eq[6])*dx;
                }
                if (checkDELTA(total)) {
                    if (total < 0) {
                        total = (int) (total-DELTA*10);
                    }
                    else {
                        total = (int) (total+DELTA*10);
                    }
                }
                return total;
            }
        }
    }
}
