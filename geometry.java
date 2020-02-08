package IMath;

public class geometry {
    public static final double DELTA = 0.0001;
    public static class quadrilateral {
        private double base;
        private double height;
        private double angle;

        public quadrilateral(double base, double height) {
            this.base = base;
            this.height = height;
            this.angle = 90;
        }
        public quadrilateral(double base, double height, double angle) {
            this.base = base;
            this.height = height;
            this.angle = angle;
        }
        public double getArea() {
            return base*height;
        }
        public double getPerimeter() {
            double perimeter = (height/Math.sin(Math.toRadians(angle))+base)*2;
            if (checkDELTA(perimeter)) perimeter = Math.round(perimeter);
            return perimeter;
        }
    }
    public static class rectangle extends quadrilateral {


        public rectangle(double base, double height) {
            super(base, height);
        }
        public rectangle(double side) {
            super(side,side);
        }
    }
    public static class square extends rectangle{

        public square(double base) {
            super(base);
        }
    }

    public static class ellipse {
//        double a;
//        double b;
        algebra a;
        algebra b;
        boolean circMode;

        public ellipse (double a, double b) {
//            this.a = a;
//            this.b = b;
            this.a = new algebra(false);
            this.b = new algebra(false);
            this.a.add(1);
            this.b.add(1);
            this.a.multiply(a);
            this.b.multiply(b);
            circMode = false;
        }
        public ellipse (double a) {
            this.a = new algebra(false);
            this.a.add(1);
            this.a.multiply(a);
            circMode = true;
        }
        public String getArea() {
            algebra t1 = new algebra(a);
            if (circMode) {
                t1.multiply(t1.getTotal());
            }
            else {
                algebra t2 = new algebra(b);
                t1.multiply(t2.getTotal());
            }
            return t1.getTotal()+"π";
        }
        public String getCircumference() {
            algebra t1 = new algebra(a);
            if (circMode) t1.multiply(2);
            else {
                algebra t2 = new algebra(b);
                t1.multiply(t1.getTotal());
                t2.multiply(t2.getTotal());
                t1.add(t2.getTotal());
                t1.divide(2);
            }
            if (checkDELTA(t1.getTotal())) return (int) t1.getTotal() + "π";
            else {
                return "√("+t1.getTotal()+")*π";
            }
        }
    }
    public static class circle extends ellipse {

        public circle(double a) {
            super(a);
        }
    }

    public static boolean checkDELTA(double num) {
        return (num - DELTA < (int) num || num + DELTA > (int) num+1);
    }
}
