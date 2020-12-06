package cz.iknowyou.jidelnicek.statistics;

import cz.iknowyou.jidelnicek.csv.Food;

import java.util.List;

public class TotalFat implements Statistics {


    @Override
    public Result getResult(List<Food> subList) {
        float result = subList
                .stream()
                .map(food -> food.getAmount() * food.getFats())
                .reduce(Float::sum)
                .orElse(0f);

        return Result
                .builder()
                .name("total fat")
                .result(result)
                .units("g")
                .build();
    }
}
