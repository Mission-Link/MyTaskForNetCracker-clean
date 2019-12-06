package old.testies_old;

public class A {
    private int number;

    public A(int number) {
        this.number = number;
        if(number < 0){
            System.out.println("number is less than 0, exit");
            return;
        }
        int numX = - number;
        System.out.println(numX);
    }


    public static void main(String[] args) {
        A a = new A(5);
        A b = new A(-5);
        System.out.println("Unreachable message");
    }
}
