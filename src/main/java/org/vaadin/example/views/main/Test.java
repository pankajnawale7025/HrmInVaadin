package org.vaadin.example.views.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {

        Map<Integer, List<Integer>> collect = Arrays.asList(11, 22, 33, 44, 11, 22, 3, 4).stream().collect(Collectors.groupingBy(x -> x+1));

        System.out.println(collect);



    }
}
