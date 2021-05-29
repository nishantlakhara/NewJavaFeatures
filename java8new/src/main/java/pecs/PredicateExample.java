package pecs;

import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Number> pred = n -> n.intValue() > 2;
        Predicate<Integer> predInt = n -> n > 3;
        //Compile error
        //pred.and(predInt);

        Predicate<Object> predObj = o -> Integer.parseInt(o.toString()) > 4;
        pred.and(predObj); //Valid statement

        Number n = new Integer(100);
        System.out.println(pred.and(predObj).test(10));
        System.out.println(predInt.and(pred).test(10));
        //Compile error
        //System.out.println(pred.and(predInt).test(10));
    }
}
