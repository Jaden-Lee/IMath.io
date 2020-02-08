package IMath;

public class README {
    public static void main(String[] args) {
        // Initializes geometry module
        geometry geometry = new geometry();
        // geometry.ellipse has multiple ways to be constructed
        // geometry.ellipse(radius), being a circle as a result,
        // or geometry.ellipse(a, b): a being radius 1 and b being radius 2
        geometry.ellipse ellipse = new geometry.ellipse(2,3);
        geometry.circle circle = new geometry.circle(4);
        // both geometry.ellipse and geometry.circle can calculate its circumference and its area using:
        // .getCircumference() and .getArea()
        // the method outputs a string, providing an exact answer
        System.out.println("P: " + ellipse.getCircumference() + " A: " + ellipse.getArea());
        System.out.println("P: " + circle.getCircumference() + " A: " + circle.getArea());
        // geometry.parallelogram has parallelogram extending to rectangle, and rectangle extending to square
        // parallelogram can be constructed in two ways:
        // 1) geometry.parallelogram(base, height, angle)
        // this constructor is used for parallelograms where the angle is the angle between two none parallel sides
        // 2) geometry.parallelogram(base, height)
        // this constructor is used for rectangles and squares making the angle be 90 degrees by default
        geometry.parallelogram parallelogram = new geometry.parallelogram(2,2,30);
        geometry.rectangle rectangle = new geometry.rectangle(2,3);
        geometry.square square = new geometry.square(2);
        // parallelograms, rectangles, and squares all have .getPerimeter() and .getArea() method
        System.out.println("P: " + parallelogram.getPerimeter() + " A: " + parallelogram.getArea());
        System.out.println("P: " + rectangle.getPerimeter() + " A: " + rectangle.getArea());
        System.out.println("P: " + square.getPerimeter() + " A: " + square.getArea());

        // Initializes algebra module
        // input asks for intMode
        // if true, does calculations in int
        // if false, does calculations in double
        // provides faster and systematic way to do algebraic computations
        algebra intAlgebra = new algebra(true);
        algebra doubleAlgebra = new algebra(false);
        // algebra module has .getTotal() returning a double even if intMode = true;
        // algebra module has .add(int/double) .subtract(int/double) .multiply(int/double) .divide(int/double)
        System.out.println("Integer Algebra: " + intAlgebra.getTotal() + " Double Algebra: " + doubleAlgebra.getTotal());
        intAlgebra.add(1);
        doubleAlgebra.add(1.0);
        System.out.println("Integer Algebra: " + intAlgebra.getTotal() + " Double Algebra: " + doubleAlgebra.getTotal());
        intAlgebra.substract(2);
        doubleAlgebra.substract(2.0);
        System.out.println("Integer Algebra: " + intAlgebra.getTotal() + " Double Algebra: " + doubleAlgebra.getTotal());
        intAlgebra.multiply(-2);
        doubleAlgebra.multiply(-2.0);
        System.out.println("Integer Algebra: " + intAlgebra.getTotal() + " Double Algebra: " + doubleAlgebra.getTotal());
        intAlgebra.divide(2);
        doubleAlgebra.divide(2.0);
        System.out.println("Integer Algebra: " + intAlgebra.getTotal() + " Double Algebra: " + doubleAlgebra.getTotal());
        // algebra module has a copy constructor allowing temporary calculations
        algebra intTemp = new algebra(intAlgebra);
        intTemp.multiply(9);
        algebra doubleTemp = new algebra(doubleAlgebra);
        doubleTemp.multiply(9.0);
        System.out.println("Integer Algebra: " + intTemp.getTotal() + " Double Algebra: " + doubleTemp.getTotal());
        // algebra has the method .power(int/double) allowing fractional exponents with high floating point precision
        intTemp.power(2);
        doubleTemp.power(3.0/2);
        System.out.println("Integer Algebra: " + intTemp.getTotal() + " Double Algebra: " + doubleTemp.getTotal());

        // Initializes calculus module
        calculus calculus = new calculus();
        // calculus module has features of calculating infinite sums, limits, and integrals
        String linearEquation = "y=2x+1";
        // linear equations MUST be in y=ax+b format
        String powerEquation = "y=x^2+1";
        // power equations MUST be in y=ax^b+c; however, there is no need for the coefficient a.
        // If no constant a is found, the coefficient value will be 1
        String inverseEquation = "y=2/x+1";
        // inverse equations must be in the format y=a/x+b
        String exponentialEquation = "y=2^x+1";
        // exponential functions must be in the format y=a^x+b

        // .limit(double, String), the double input is where the limit is going to be evaluated
        // the equation must be in the format above
        System.out.println(linearEquation + " evaluated at x = 0 is " + calculus.limit(0,linearEquation));
        System.out.println(powerEquation + " evaluated at x = 0 is " + calculus.limit(0,powerEquation));
        // System.out.println(inverseEquation + " evaluated at x = 0 is " + calculus.limit(0,inverseEquation));
        // The inverse function at x = 0 does not have a limit so it will print out:
        // Exception in thread "main" java.lang.ArithmeticException: There is no limit for y=2/x+1 at value, 0.0.
        // instead calculating at x = 1
        System.out.println(inverseEquation + " evaluated at x = 1 is " + calculus.limit(1,inverseEquation));
        System.out.println(exponentialEquation + " evaluated at x = 0 is " + calculus.limit(0,exponentialEquation));

        // .infinteSum(double start, double ratio) uses the formula a/(1-r)
        // if absolute value of r is greater than equal to 1, an exception is thrown
        // .contAddInfSum(double start, double ratio, double iterations)
        // does not throw an exception even if the absolute value of r is greater than equal to 1 unless the infinite sum
        // exceeds value of Double.MAX_VALUE
        // this method uses start+ratio(start+ratio(start+ratio(...))) for provided number of iterations, providing a faster
        // way to calculate infinite sums than calculating each term and adding
        System.out.println(calculus.infiniteSum(1,0.5));
        System.out.println(calculus.contAddInfSum(1,0.5,1000));

        // .integral(double low, double high, String equation), low is the lower bound and high is the cap of the integral
        // equation still must follow the format above used in limits
        System.out.println(linearEquation + " integrated from x = 1 to x = 2 is " + calculus.integral(1,2,linearEquation));
        System.out.println(powerEquation + " integrated from x = 1 to x = 2 is " + calculus.integral(1,2,powerEquation));
        System.out.println(inverseEquation + " integrated from x = 1 to x = 2 is " + calculus.integral(1,2,inverseEquation));
        System.out.println(exponentialEquation + " integrated from x = 1 to x = 2 is " + calculus.integral(1,2,exponentialEquation));
    }
}
