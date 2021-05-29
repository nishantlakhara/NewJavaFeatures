package pecs.consumer;

import pecs.MyConsumer;

import java.util.List;

public class PECSExample {

    public static void main(String[] args) {
        consumerSuper();

//        Optional<String> ;
//        Predicate<String> ;
        List list;
    }

    private static void consumerSuper() {
        MyConsumer<Object> obj = o -> System.out.println(o.toString());

        //All Eater
        MyConsumer<EnergySource> eater = EnergySource::description;

        //Only Vegetable Eater
        MyConsumer<Leaf> vegEater = Leaf::description;

        //Only Bamboo Eater
        MyConsumer<Bamboo> bambooEater = Bamboo::description;

        MyConsumer<? super EnergySource> testRef = obj;
        testRef = eater;
//        Compile errors
//        testRef = vegEater;
//        testRef = bambooEater;

//        default MyConsumer<T> andThen(MyConsumer<? super T> after) {
//            Objects.requireNonNull(after);
//            return (T t) -> { accept(t); after.accept(t); };
//        }

        System.out.println("\n===================");
        eater.andThen(eater).andThen(obj).accept(new EnergySource());
//        Compile errors
//        eater.andThen(vegEater).accept(new VegetableEater());
//        eater.andThen(bambooEater).accept(new BambooEater());

        System.out.println("\n===================");
        vegEater.andThen(eater).andThen(vegEater).andThen(obj).accept(new Leaf());
//        Compile errors at bambooEater
//        vegEater.andThen(bambooEater).accept(new VegetableEater());

        System.out.println("\n===================");
        bambooEater.andThen(vegEater).andThen(eater).andThen(bambooEater).andThen(obj).accept(new Bamboo());
    }
}


