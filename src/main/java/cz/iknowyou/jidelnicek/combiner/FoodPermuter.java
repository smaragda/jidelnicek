package cz.iknowyou.jidelnicek.combiner;

import cz.iknowyou.jidelnicek.csv.Food;
import cz.iknowyou.jidelnicek.rules.RuleList;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * changed to same type.
 */
@Slf4j
public class FoodPermuter {

    private final AllLists allLists;

    @Setter
    private RuleList ruleList;

    /**
     * key refers to key-th List of objects,
     * value refers to current iteration (value-th item) of key-th list.
     */
    private final Map<Integer, Integer> pointers = new LinkedHashMap<>();

    public FoodPermuter(AllLists allLists) {
        this.allLists = allLists;
        IntStream.range(0, allLists.getLists().size())
                .forEach(i -> pointers.put(i, 0));
    }

    public List<Object> getActual() {
        val result = new ArrayList<>();
        pointers.forEach((key, value) -> result.add(allLists.getLists().get(key).getItems().get(value)));
        return result;
    }

    public <T> List<T> getActualIfSame() {
        val result = new ArrayList<T>();
        pointers.forEach((key, value) -> result.add((T) allLists.getLists().get(key).getItems().get(value)));
        return result;
    }

    public List<Food> getSuitable() {
        Assert.notNull(ruleList, "list of rules should be defined!");
        //pointers.forEach((key, value) -> log.info("Kolo:value...{}:{}", key, value));

        List<Food> foodList = getActualIfSame();
        if (ruleList.evaluate(foodList)) {
            return foodList;
        }
        next();
        return getSuitable();
    }


//    public List<Object> getRandom() {
//        val result = new ArrayList<>();
//        allLists.getLists().forEach(listWrap -> {
//            int totalItems = listWrap.getItems().size();
//            int randomItemIndex = (int) (Math.random() * totalItems);
//            result.add(listWrap.getItems().get(randomItemIndex));
//        });
//        return result;
//    }


//    public void printActual() {
//        getActual().forEach(o -> log.debug(o.toString() + " | "));
//        log.debug("");
//    }
//
//    public void printRandom() {
//        getRandom().forEach(o -> log.debug(o.toString() + " | "));
//        log.debug("");
//    }

    public void next() {
        increment(0);
    }

    public long getTotalIterations() {
        return allLists.getTotalIterations();
    }

    private void increment(int kolo) {
        if (kolo >= pointers.keySet().size()) {
            log.debug("Overflown, starting from the beginning..");
            throw new OverflowException();
        }
        int newPointerValue = pointers.get(kolo) + 1;
        if (newPointerValue < allLists.getLists().get(kolo).getItems().size()) {
            pointers.put(kolo, newPointerValue);
        } else {
            pointers.put(kolo, 0);
            increment(kolo + 1);
        }
    }
}
