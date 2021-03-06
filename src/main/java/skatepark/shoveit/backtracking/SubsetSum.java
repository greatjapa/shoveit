package skatepark.shoveit.backtracking;

import java.util.ArrayList;
import java.util.Collections;
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

        Set<Set<Integer>> combinations = generateCombinations(set.size(), set.size());
        Set<Set<Integer>> result = new HashSet<>();
        Iterator<Set<Integer>> iterator = combinations.iterator();
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

    private Set<Set<Integer>> generateCombinations(int numberOfElements, int order) {
        if (order == 1) {
            return IntStream.range(0, numberOfElements)
                    .mapToObj(Collections::singleton)
                    .collect(Collectors.toSet());
        }
        Set<Set<Integer>> result = new HashSet<>();
        Set<Set<Integer>> subsets = generateCombinations(numberOfElements, order - 1);
        result.addAll(subsets);

        for (int i = 0; i < numberOfElements; i++) {
            Iterator<Set<Integer>> iterator = subsets.iterator();
            while (iterator.hasNext()) {
                Set<Integer> elem = iterator.next();
                if (elem.size() == order - 1 && !elem.contains(i)) {
                    Set<Integer> newSet = new HashSet<>(elem);
                    newSet.add(i);
                    result.add(newSet);
                }
            }
        }
        return result;
    }
}
