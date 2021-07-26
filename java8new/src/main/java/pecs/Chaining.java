package pecs;

import javafx.util.Pair;

public class Chaining {

    public static void main(String[] args) {
        Validation<Integer, String, Message> validation = (a, b, c) -> {
            return new Pair(a > 30, new Message("a should not be greater than 30"));
        };
        Validation<Integer, String, Message> validation2 = (a, b, c) -> {
            return new Pair(a > 20, new Message("a should not be greater than 20"));
        };
        Validation<Integer, String, Message> validation3 = (a, b, c) -> {
            return new Pair(a > 10, new Message("a should not be greater than 10"));
        };
        Validation<Integer, String, Message> validation4 = (a, b, c) -> {
            return new Pair(a > 5, new Message("a should not be greater than 5"));
        };

        //One way to call
        Validation<Integer, String, Message> validator = validation
                .and(validation2)
                .and(validation3)
                .and(validation4);

        System.out.println(validator.validate(4, "A00", 0));
        System.out.println(validator.validate(9, "A00", 0));
        System.out.println(validator.validate(11, "A00", 0));
        System.out.println(validator.validate(21, "A00", 0));
        System.out.println(validator.validate(31, "A00", 0));

        //Another way to call
        Pair<Boolean, Message> pair = validation
                .and(validation2)
                .and(validation3)
                .and(validation4)
                .validate(4, "String", 0);
        if(pair.getKey() == false) {
            System.out.println("All Validation successful");
        } else {
            System.out.println(pair);
        }

    }
}
