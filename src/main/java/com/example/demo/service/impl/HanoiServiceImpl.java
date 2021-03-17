package com.example.demo.service.impl;

import com.example.demo.service.HanoiService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HanoiServiceImpl implements HanoiService {

    private static Deque<Integer> source = new ArrayDeque<>();
    private static Deque<Integer> destination = new ArrayDeque<>();
    private static Deque<Integer> spare = new ArrayDeque<>();

    private static int steps = 0;

    private List<String> list = new LinkedList<>();

    @Override
    public List<String> hanoi(int disk) {

        for (int i = disk; i >=1 ; i--) {
            source.push(i);
        }

        solve(disk, source, destination, spare);

        return list;
    }


    private void solve(int disk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if(disk == 1){
            destination.push(source.pop());
            list.add("Step#" + (steps++) + ": Moved disk");
            list.add(System.lineSeparator());
            printRods();
        } else {
            solve(disk - 1, source, spare, destination);
            solve(1, source, destination, spare);
            solve(disk - 1, spare, destination, source);
        }
    }

    private void printRods(){
        list.add(String.format("Source: %s%n" +
                "Destination: %s%n" +
                "Spare: %s%n", formatRod(source), formatRod(destination), formatRod(spare)));
        list.add(System.lineSeparator());
    }

    private String formatRod(Deque<Integer> stack) {
        return stack.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
