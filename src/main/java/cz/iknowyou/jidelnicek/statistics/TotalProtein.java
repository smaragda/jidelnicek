package cz.iknowyou.jidelnicek.statistics;

import cz.iknowyou.jidelnicek.csv.Food;

import java.util.List;

public class TotalProtein implements Statistics {


    @Override
    public Result getResult(List<Food> subList) {
        float result = subList
                .stream()
                .map(food -> food.getAmount() * food.getProteins())
                .reduce(Float::sum)
                .orElse(0f);

        return Result
                .builder()
                .name("total protein")
                .result(result)
                .units("g")
                .build();
    }
}
