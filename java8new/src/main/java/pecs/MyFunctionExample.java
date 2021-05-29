package pecs;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyFunctionExample {
    public static void main(String[] args) {
//        basicFunction();
//
//        compose_AndThen_Util1();
//
//        compose_AndThen_Util2();

        compose_AndThen_Util3();
    }

//    private static void basicFunction() {
//        MyFunction<String, Integer> intFunction = s -> Integer.parseInt(s);
//        Integer out = intFunction.apply("100");
//        System.out.println(out);
//    }
//
//    public static void compose_AndThen_Util1() {
//        MyFunction<Integer, Integer> times2 = e -> e * 2;
//        MyFunction<Integer, Integer> squared1 = e -> e * e;
//        MyFunction<String, Integer> squared2 = e -> { int i = Integer.parseInt(e); return i * i; };
//        MyFunction<Object, Integer> squared3 = e -> { int i = Integer.parseInt(e.toString()); return i * i; };
//
//        System.out.println(times2.compose(squared1).apply(4));
//        System.out.println(times2.compose(squared2).apply("4"));
//        System.out.println(times2.compose(squared3).apply("4"));
//        //System.out.println(times2.andThen(squared).apply(4));
//    }

//    public static void compose_AndThen_Util2() {
//        MyFunction<Integer, EnergySource> eaterToEntertain = i -> {
//            System.out.println(i);
//            return new Leaf();};
//        MyFunction<EnergySource, Entertainment> eaterToMusic = energySource -> {
//            energySource.description();
//            return new Music(); };
//
//        eaterToEntertain.andThen(eaterToMusic).apply(10);
//        eaterToEntertain
//                .andThen(eaterToMusic)
//                .andThen((Object entertainment) -> {
//                    System.out.println(entertainment);
//                    return new Leaf();
//                })
//                .andThen((EnergySource eater) -> new Music())
//                .apply(10);
//    }

    public static void compose_AndThen_Util3() {
        //More flexibility
//        default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
//            Objects.requireNonNull(after);
//            return (T t) -> after.apply(apply(t));
//        }

        MyFunction<Integer, Integer> first = x -> x.intValue() + 1;
        MyFunction<Number, Number> second = x -> {
            if(x.intValue()%2 == 1) return x.intValue() * 2;
            else return x.intValue() * 0.2;
        };
        MyFunction<Integer, Number> andThen = first.andThen(second);
        System.out.println(andThen.apply(3) instanceof Double);
        System.out.println(andThen.apply(2) instanceof Integer);

        MyFunction<Integer, Number> third = x -> x / 3.0;
        MyFunction<Integer, Number> andThenDouble = first.andThen(third);
        System.out.println(andThenDouble.apply(3));

        //Assignability check
        MyFunction<? super Integer, ? extends Number> refFun = first;
        refFun = second;
        refFun = third;

        //Assignability - many combinations
        MyFunction<Integer, Number> integerNumberMyFunction = first.andThen(second);
        MyFunction<Integer, Object> integerNumberMyFunction1 = first.andThen(second);
        MyFunction<? extends Number, Object> integerNumberMyFunction2 = first.andThen(second);
        MyFunction<? extends Number, ? super Number> integerNumberMyFunction3 = first.andThen(second);
        //Compile error
//        MyFunction<Number, Object> integerNumberMyFunction4 = first.andThen(second);
        MyFunction<? super Integer, ? extends Number> integerNumberMyFunction5 = first.andThen(second);
    }
}
