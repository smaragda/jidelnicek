package cz.iknowyou.jidelnicek;

import cz.iknowyou.jidelnicek.combiner.AllLists;
import cz.iknowyou.jidelnicek.combiner.FoodPermuter;
import cz.iknowyou.jidelnicek.csv.FoodCsvLoader;
import cz.iknowyou.jidelnicek.rules.Operator;
import cz.iknowyou.jidelnicek.rules.Rule;
import cz.iknowyou.jidelnicek.rules.RuleList;
import cz.iknowyou.jidelnicek.statistics.TotalCalories;
import cz.iknowyou.jidelnicek.statistics.TotalProtein;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FireStarter {

    @EventListener
    public void listen(ApplicationStartedEvent event) {
        log.info("JMR: started");

        val foodItems = new FoodCsvLoader().loadFrom("energtabulky.csv");
        log.debug("...loading done.");

        log.debug("setting up rules..");
        Rule calorieRule = Rule.builder()
                .statistics(new TotalCalories())
                .operator(Operator.LOWER_THAN)
                .templateResult(500f)
                .build();

        Rule proteinRule = Rule.builder()
                .statistics(new TotalProtein())
                .operator(Operator.GREATER_THAN)
                .templateResult(30f)
                .build();

        RuleList allRules = RuleList.builder()
                .rule(calorieRule)
                .rule(proteinRule)
                .build();

        FoodPermuter threePermuter = new FoodPermuter(
                AllLists.builder()
                        .list(foodItems)
                        .list(foodItems)
                        .list(foodItems)
                        .build()
        );

        threePermuter.setRuleList(allRules);
        log.debug("...rules set.");

        log.debug("finding solution #1");
        threePermuter.getSuitable().forEach(f -> log.info(f.toString()));
        threePermuter.next();
        log.debug("finding solution #2");
        threePermuter.getSuitable().forEach(f -> log.info(f.toString()));
        threePermuter.next();
        log.debug("finding solution #3");
        threePermuter.getSuitable().forEach(f -> log.info(f.toString()));

        log.debug("solution searching finished.");

    }


    /*
    private void oldCode() {
        log.info("JMR: Statistics...");
        log.info(new TotalCalories().getResult(foodItems).toString());

        val snidane = new RandomChoser().getRandomSublist(database, 4);
        log.info(snidane.toString());

        ListWrap<Food> listWrap = ListWrap.<Food>builder()
                .items(foodItems)
                .build();

        // combination of two
        AllLists allLists = AllLists.builder()
                .list(listWrap)
                .list(listWrap)
                .build();

        Permuter permuter = new Permuter(allLists);
        var totalIterations = permuter.getTotalIterations();
        log.debug(totalIterations);

        for (int i = 0; i < totalIterations; i++) {
            List<?> objects = permuter.getActual();
            if (check(objects)) {
                permuter.printActual();
                break;
            }
            permuter.next();
        }
    }



     */
}
