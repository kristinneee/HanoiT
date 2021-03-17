package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HanoiRunner implements CommandLineRunner {

    public static Deque<Integer> source = new ArrayDeque<>();
    public static Deque<Integer> destination = new ArrayDeque<>();
    public static Deque<Integer> spare = new ArrayDeque<>();

    public static int steps = 0;

    int disk;

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        disk = Integer.parseInt(scanner.nextLine());

        for (int i = disk; i >=1 ; i--) {
            source.push(i);
        }

        solve(disk, source, destination, spare);
    }

    private void solve(int disk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if(disk == 1){
            destination.push(source.pop());
            System.out.println("Step#" + (steps++) + ": Moved disk");
            printRods();
        } else {
            solve(disk - 1, source, spare, destination);
            solve(1, source, destination, spare);
            solve(disk - 1, spare, destination, source);
        }
    }

    public static void printRods(){
        System.out.println(String.format("Source: %s%n" +
                "Destination: %s%n" +
                "Spare: %s%n", formatRod(source), formatRod(destination), formatRod(spare)));
    }

    private static String formatRod(Deque<Integer> stack) {
        return stack.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
