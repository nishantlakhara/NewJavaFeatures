package com.lambda.passbehaviour;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PassBehaviourExample {
	public static void main(String[] args) {
		PassBehaviourExample pbe = new PassBehaviourExample();
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

		//Without lambda
		pbe.sumAll(numbers);
		pbe.sumAllEven(numbers);
		
		//With lambda and passing behaviours
		pbe.sumAll(numbers, n -> true);
		pbe.sumAll(numbers, n -> n % 2 == 0);
		pbe.sumAll(numbers, n -> n > 3);
		
	}
	
	public int sumAll(List<Integer> numbers) {
	    int total = 0;
	    for (int number : numbers) {
	        total += number;
	    }
	    System.out.println(total);
	    return total;
	}
	
	public int sumAllEven(List<Integer> numbers) {
	    int total = 0;
	    for (int number : numbers) {
	        if (number % 2 == 0) {
	            total += number;
	        }
	    }
	    System.out.println(total);
	    return total;
	}
	
	public int sumAll(List<Integer> numbers, Predicate<Integer> p) {
	    int total = 0;
	    for (int number : numbers) {
	        if (p.test(number)) {
	            total += number;
	        }
	    }
	    System.out.println(total);
	    return total;
	}
}
