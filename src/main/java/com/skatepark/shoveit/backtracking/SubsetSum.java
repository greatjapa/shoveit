package com.skatepark.shoveit.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubsetSum {

    public Set<Set<Integer>> calc(Set<Integer> set) {
        if (set == null || set.isEmpty()) {
            return new HashSet<>();
        }
        List<Integer> list = new ArrayList<>(set);

        Set<Set<Integer>> all = getSubsets(set.size(), set.size());
        Set<Set<Integer>> result = new HashSet<>();
        Iterator<Set<Integer>> iterator = all.iterator();
        while (iterator.hasNext()) {
            Set<Integer> subset = iterator.next();

            int sum = subset.stream()
                    .map(list::get)
                    .mapToInt(Integer::intValue)
                    .sum();

            if (sum == 0) {
                result.add(subset.stream()
                        .map(list::get)
                        .collect(Collectors.toSet()));
            }
        }
        return result;
    }

    private Set<Set<Integer>> getSubsets(int numberOfElements, int order) {
        if (order == 1) {
            return IntStream.range(0, numberOfElements)
                    .mapToObj(i -> {
                        Set<Integer> set = new HashSet<>();
                        set.add(i);
                        return set;
                    }).collect(Collectors.toSet());
        }
        Set<Set<Integer>> result = new HashSet<>();
        Set<Set<Integer>> subsets = getSubsets(numberOfElements, order - 1);
        Iterator<Set<Integer>> iterator = subsets.iterator();
        while (iterator.hasNext()) {
            Set<Integer> base = iterator.next();
            Iterator<Set<Integer>> nestedIterator = subsets.iterator();
            while (nestedIterator.hasNext()) {
                Set<Integer> elem = nestedIterator.next();
                Set<Integer> newSet = new HashSet<>();
                newSet.addAll(base);
                newSet.addAll(elem);
                result.add(newSet);
            }
        }
        return result;
    }
}
