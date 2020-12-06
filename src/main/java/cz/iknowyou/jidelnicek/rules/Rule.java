package cz.iknowyou.jidelnicek.rules;

import cz.iknowyou.jidelnicek.csv.Food;
import cz.iknowyou.jidelnicek.statistics.Statistics;
import lombok.Builder;

import java.util.List;

@Builder
public class Rule {
    Statistics statistics;
    Operator operator;
    float templateResult;

    public boolean evaluate(List<Food> sublist) {
        float result = statistics.getResult(sublist).getResult();
        return operator.evaluate(result, templateResult);
    }
}
