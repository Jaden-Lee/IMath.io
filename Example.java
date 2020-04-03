import java.util.Scanner;
public class Example {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type '1' for a frustum problem and type '2' for a cone problem");
        int val = scanner.nextInt();
        if (val == 1) {
          String answer = generateFrustumProblem();
          while (answer.equals("214748.3647pi")) {
            System.out.println("Invalid Question. Generating new frustum question.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            answer = generateFrustumProblem();
          }
          System.out.println("Type anything for answer.");
          scanner.next();
          System.out.println(answer+"\n\n\n\n");
        }
        else if (val == 2) {
          String answer = generateConeProblem();
          while (answer.equals("214748.3647pi")) {
            System.out.println("Invalid Question. Generating new cone question.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            answer = generateConeProblem();
          }
          System.out.println("Type anything for answer.");
          scanner.next();
          System.out.println(answer + "\n\n\n\n");
        }
    }
    public static double s_integrate(double lower, double upper, String function) {
        String coeff = function.substring(0,function.indexOf("y"));
        String constant = function.substring(function.indexOf("y")+1);
        double double_coeff = Double.parseDouble(coeff);
        double double_constant = Double.parseDouble(constant);
        double n = (upper-lower)/100000.0;
        double area = 0;
        for (double i = lower; i < upper; i+=n) {
            area += ((double_coeff*i+double_constant)*(double_coeff*i+double_constant)*n)*i;
        }
        return round(area,0);
    }
    public static double integrate(double lower, double upper, String function) {
        String coeff = function.substring(0,function.indexOf("y"));
        String constant = function.substring(function.indexOf("y")+1);
        double double_coeff = Double.parseDouble(coeff);
        double double_constant = Double.parseDouble(constant);
        double n = (upper-lower)/10000000.0;
        double area = 0;
        for (double i = lower; i < upper; i+=n) {
            area += (double_coeff*i+double_constant)*n;
        }
        return round(area,4);
    }
    public static double round(double num, int places) {
        int temp = (int) (num*Math.pow(10,places));
        return temp/Math.pow(10,places);
    }
    public static String generateFrustumProblem() {
        int r = (int)(Math.random()*6+4);
        int R = (int)(Math.random()*5)+10;
        int h = (int)(Math.random()*15+5);
        System.out.println("A frustum tank is full of water. Find the work required to pump the water out of the spout. Use the fact that water weighs\n62.5 lb/ft3. (Assume r = " + r + " ft, R = " + R + " ft, and h = " + h + " ft.)");
        return (round(62.5*s_integrate(0,h,Double.toString((r-R)/(h*1.0))+"y"+Integer.toString(R)),4))+"pi";
    }
    public static String generateConeProblem() {
        int r = 0;
        int R = (int)(Math.random()*5)+10;
        int h = (int)(Math.random()*15+5);
        System.out.println("A cone tank is full of water. Find the work required to pump the water out of the spout. Use the fact that water weighs\n62.5 lb/ft3. (Assume R = " + R + " ft, and h = " + h + " ft.)");
        return (round(62.5*s_integrate(0,h,Double.toString((r-R)/(h*1.0))+"y"+Integer.toString(R)),4))+"pi";
    }
}