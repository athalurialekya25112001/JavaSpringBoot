import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A Stream in Java - sequence of elements from a source.
 *
 * Intermediate Operations - filter, map , flatmap
 * Terminal Operations - foreach , collect , reduce
 */
public class JavaStreams {
    public static void main(String args[]) {

        // 1. Find the sum of all elements in a List using streams.
        List<Integer> nums  = Arrays.asList(1,2,3,4,5);
        int sum = nums.stream().reduce(0, (a,b)-> a+b);
        int sum1 = nums.stream().reduce(0, Integer::sum);
        int sum2 = nums.stream().mapToInt(Integer::intValue).sum();
        System.out.printf("The sum of nums is %d %d %d%n", sum, sum1, sum2 );

        //2. Given a List of Integers, write a program to find the maximum elements using streams.
        int max = nums.stream().max(Comparator.naturalOrder()).orElse(0);
        int max1 = nums.stream().max(Comparator.naturalOrder()).get();
        int max2 = nums.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.printf("The maximum value of nums is %d %d %d%n", max, max1, max2);

        //3. Given a List of strings, write a program to count the number of strings that start with a specific character using streams
        List<String> countries = Arrays.asList("Asia", "Australia", "Pillipines", "Afghanistan", "Switzerland", "France", "Paris", "UK", "Kuwait", "Dubai");
        long count =  countries.stream().filter(c -> c.startsWith("A")).count();
        System.out.println("The count of countries that starts with 'A' are "+ count);

        //4. Convert a List of strings to uppercase using streams.
        List<String> countriesInUpperCase = countries.stream().map( c-> c.toUpperCase()).collect(Collectors.toList());
        List<String> countriesInUpperCase1 = countries.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.printf("Countries in UpperCase %s%n %s%n",countriesInUpperCase, countriesInUpperCase1);
        countries.stream().map(String::toUpperCase).forEach(x -> System.out.println(x));


        //5. Given a List of integers , write a program to filter out the even numbers using streams.
        //Count the number of elements in a list that satisfy a specific condition using streams.
        long countOfEvenNumbers = nums.stream().filter( n -> (n%2==0)).count();
        System.out.println("The count of even numbers : "+ countOfEvenNumbers);

        //6. Write a program to find the average of a List of floating-point numbers using streams.
        List<Double> floatNums  = Arrays.asList(1.1,2.2,3.3,4.4,5.5);
        double average = floatNums.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
        System.out.println("The average of floating numbers : "+ average);

        //7. Given a List of strings, write a program to concatenate all the strings using streams.
        String concatenatedCountries = countries.stream().collect(Collectors.joining(","));
        System.out.printf("The concatenation all the strings is %s%n", concatenatedCountries );

        //8. Write a program to remove duplicates elements from a List using streams
        List<Integer> duplicates = Arrays.asList(1, 2, 3, 4, 3, 1, 8);
        List<Integer> uniqueElements = duplicates.stream().distinct().collect(Collectors.toList());
        System.out.printf("The unique elements are - %s%n" , uniqueElements);


        //9. Given a list of objects, write a program to sort the objects based on a specific attribute using streams
        List<Person> personList =  Arrays.asList(
                new Person("Alekya", 23),
                new Person("Rohini", 21),
                new Person("Kumari", 42),
                new Person("Mahesh", 55));
        Collections.sort(personList, Comparator.comparing(Person::getAge));
        ListIterator<Person> iterator = personList.listIterator();
        System.out.printf("The sorted Persons List are - \n");
        while(iterator.hasNext()) {
            Person p = iterator.next();
            System.out.println(p.getAge() +" " + p.getName());
        }

       personList.stream().sorted(Comparator.comparing(Person::getAge)).forEach(p -> System.out.println(p.getAge()));

        //10. Write a program to check if all elements in a List satisfy a given condition using streams.

        List<Integer> evens  = Arrays.asList(2,4,6,8,9);
        boolean allEvenElements = evens.stream().allMatch(element -> element%2==0);
        System.out.println("Does the list contains all even elements - "+ allEvenElements);

        boolean anyEvenElement = evens.stream().anyMatch(element -> element%2==0);
        System.out.println("Does the list contains at least one even elements - "+ anyEvenElement);


        // Advanced Streams
        //1. Given a sentence, find and print the frequency of each word.
        String sentence = "Java is a programming language. Java is versatile.";

        Map<String, Long> wordFreqMap = Arrays.stream(sentence.split("\\s+")).collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        System.out.println(wordFreqMap);
        //2. Given a list of integers, find out all the numbers starting with 1.

        List<Integer> numbers = Arrays.asList(12, 13, 18, 21, 90, 11);
       List<Integer> numsStartsWith1 = numbers.stream().filter(n -> String.valueOf(n).startsWith("1")).collect(Collectors.toList());
        System.out.println(numsStartsWith1);


        //3. Given a list of names, group them by their first letter, and then count the number of names in each group.

        String[] names = {"Alice", "Bob", "Charlie", "Amy", "Bill", "Anna"};
        Map<Character, Long> namesMap = Arrays.stream(names).collect(Collectors.groupingBy( s -> s.charAt(0),Collectors.counting()));
        System.out.println(namesMap);


        // 4. Find and print duplicate numbers in an array if it contains multiple duplicates?

        int[] arr = {2,4,2,3,1,5, 5,78,3,1,5};

        Arrays.stream(arr).boxed()
                .collect(Collectors.groupingBy( e -> (e) , Collectors.counting()))
                .entrySet().stream().filter( entry -> entry.getValue() > 1)
                .map(Map.Entry :: getKey)
                .forEach(System.out::println);



        // 5. How are duplicates removed from a given array in Java?

        int[] arr2 = {1, 5, 8, 9, 5, 6, 4, 8, 10};
        int[] newarr2 = Arrays.stream(arr2).distinct().toArray();
        System.out.println(Arrays.toString(newarr2));


        //6. Given a list of words, filter and print the palindromes
        List<String> strings = Arrays.asList("level", "hello", "radar", "world", "deed");
        List<String> palindromes = strings.stream().filter( s -> s.equals(new StringBuilder(s).reverse().toString())).collect(Collectors.toList());
        System.out.println(palindromes);

        // 7. How do you merge two sorted arrays into a single sorted array?
        int[] array1 = {1, 3,32, 5, 7};
        int[] array2 = {2, 4, 6,62, 8};

        int[] sortedArray = IntStream.concat(Arrays.stream(array1), Arrays.stream(array2)).sorted().toArray();
        System.out.println(Arrays.toString(sortedArray));


        //8. Given two lists of strings, concatenate them and remove duplicates.

        List<String> list1 = Arrays.asList("apple", "banana", "orange");
        List<String> list2 = Arrays.asList("banana", "kiwi", "grape");

        List<String> uniqueList= Stream.concat(list1.stream(), list2.stream()).distinct().collect(Collectors.toList());
        System.out.println(uniqueList);

        // 9. Student Grade Classification - 70 and above pass
        List<Student> students = Arrays.asList(
                new Student("Alice", 85),
                new Student("Bob", 60),
                new Student("Charlie", 75),
                new Student("David", 90)
        );

        Map<String, List<Student>> studentMap =
        students.stream()
                .collect(Collectors.groupingBy(s -> s.getMarks() >70 ? "Pass" : "Fail"));

        studentMap.forEach((key, value) -> {
            System.out.println(key + ": ");
            value.forEach(student -> System.out.println("    " + student.getName() + " " + student.getMarks()));
        });

//        students.stream()
//                        .collect(Collectors.groupingBy(s -> s.getMarks() >70 ? "Pass" : "Fail"))
//                .forEach((key, value) -> {
//            System.out.println(key + ": ");
//            value.forEach(student -> System.out.println("    " + student.getName() + " " + student.getMarks()));
//        });


        //10. Given a list of strings, sort them according to increasing order of their length.

        List<String> fruits = Arrays.asList("Mango","pear" ,"Apple", "Banana", "Pineapple", "Kiwi");

        fruits.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
       // fruits.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);


        //12.Partition a list of numbers into two groups: even and odd, using a custom predicate.
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers1.stream().collect(Collectors.partitioningBy( n -> n%2==0)).entrySet().stream().forEach(System.out::println);

        Map<Boolean, List<Integer>> partitionedNumbers = numbers1.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println(partitionedNumbers);


        //13. Find the squares of the first three even numbers in a list.
        List<Integer> numbs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> squaresOfFirstThreeNumbers = numbs.stream().map( n -> n*n).limit(3).collect(Collectors.toList());

        System.out.println(squaresOfFirstThreeNumbers);

        // 14. Flatten a list of lists
        List<List<Integer>> listOfLists = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));

        List<Integer> flattenedList = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());

        System.out.println(flattenedList);




    }
}

class Person {
    String name;
    int age;

    public Person(String name , int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


class Student {
    String name;
    int marks;

    public Student(String name , int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
