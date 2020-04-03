package IMath;

public class algebra {
    private int intTotal;
    private double doubleTotal;
    private boolean intMode;
    public algebra(boolean intMode) {
        this.intMode = intMode;
        init();
    }
    public algebra(algebra a) {
        this.intMode = a.intMode;
        if (a.intMode) intTotal = (int) a.getTotal();
        else doubleTotal = a.getTotal();
    }
    private void init() {
        if (intMode) intTotal = 0;
        else doubleTotal = 0.0;
    }
    public void add(int value) {
        if (intMode) intTotal += value;
        else doubleTotal += value;
    }
    public void add(double value) {
        if (intMode) intTotal += (int) value;
        else doubleTotal += value;
    }
    public void subtract(int value) {
        add((-1)*value);
    }
    public void substract(double value) {
        add((-1)*value);
    }
    public void multiply(int value) {
        if (intMode) intTotal *= value;
        else doubleTotal *= value;
    }
    public void multiply(double value) {
        if (intMode) intTotal *= (int) value;
        else doubleTotal *= value;
    }
    public void divide(int value) {
        if (intMode) intTotal /= value;
        else doubleTotal /= value;
    }
    public void divide(double value) {
        multiply(1.0/value);
    }
    public void power(int exp) {
        double start = getTotal();
        if (exp < 0) exp *= -1;
        for (int i = 1; i < exp; i++) {
            multiply(start);
        }
    }
    public void power(double exp) {
        int[] frac = reduce(exp);
        power(frac[0]);
        doubleTotal = Math.pow(doubleTotal,1.0/frac[1]);
    }
    public double getTotal() {
        if (intMode) return intTotal;
        else return doubleTotal;
    }
    private int gcf(int n, int d) {
        if (n % d == 0)
            return d;
        else if (d % n == 0)
            return n;
        else
            return gcf(n % d, d % n);
    }
    private int[] convertDecToFrac(double decimal) {
        int round = (int) decimal;
        double part = decimal-round;
        return new int[] {Integer.parseInt(Double.toString(decimal).substring(0,1)),Integer.parseInt(Double.toString(decimal).substring(2)),(int) Math.pow(10,Double.toString(decimal).substring(2).length())};
    }
    private int[] reduce(double decimal) {
        int[] frac = convertDecToFrac(decimal);
        int num = frac[2]*frac[0]+frac[1];
        int denom = frac[2];
        if (num == 0) {
            return new int[] {0,1};
        }
        if (denom < 0) {
            num = -num;
            denom = -denom;
        }
        int q = gcf(Math.abs(num), denom);
        num /= q;
        denom /= q;
        return new int[] {num,denom};
    }
}
