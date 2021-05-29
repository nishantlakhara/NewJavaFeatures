package pecs;


public class MyConsumerExample {

    public static void main(String[] args) {
        MyConsumer<Number> consumer = n -> System.out.println(n);
    }
}
