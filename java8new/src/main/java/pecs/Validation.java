package pecs;

import javafx.util.Pair;
import java.util.Objects;

@FunctionalInterface
public interface Validation<A, B, C> {
    Pair<Boolean, C> validate(A a, B b, int count);

    default Validation<A, B, C> and(Validation<A, B, C> other) {
        Objects.requireNonNull(other);
        return (a, b, c) -> {
//            System.out.println("c=" + c);
            Pair<Boolean, C> validate = validate(a, b, c+1);
            if (validate.getKey() == true) {
                return validate;
            } else {
                //Termination condition.
                if(c == 0) {
                    Pair<Boolean, C> pair = other.validate(a, b, c);
                    if(pair.getKey() == false) {
                        return new Pair<>(false, null);
                    } else {
                        return pair;
                    }
                } else {
                    return other.validate(a, b, c);
                }
            }
        };
    }
}
