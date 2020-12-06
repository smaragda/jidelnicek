package cz.iknowyou.jidelnicek.statistics;

import cz.iknowyou.jidelnicek.csv.Food;

import java.util.List;

@FunctionalInterface
public interface Statistics {
    Result getResult(List<Food> subList);
}
