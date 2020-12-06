package cz.iknowyou.jidelnicek.rules;

import cz.iknowyou.jidelnicek.csv.Food;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

/**
 * business rules pro jidelnicek:
 * 1. kazde (hlavni) jidlo by melo obsahovat nejake min.procento bilkovin
 * 2. nemelo by se stat, ze budou 3 jidla..olej, kureci, fazole
 * 3. minimalni "pestrost" - min.pocet podpolozek jidelnicku
 * 4. davky polozek by mely byt zalozeny na obvykle porci +/- 50% konfigurovatelne
 * 5. mozna zkusit koncept rozdiloveho jidelnicku
 * 6. celkove za den by mel jidelnicek splnit vytyceny pomer zivin
 * 7. celkove splnit za den kalorie
 *
 * vsechna zminena pravidla by mela idealne byt nastavitelna za runtime :)
 */
@Builder
public class RuleList {

    @Singular
    List<Rule> rules;

    public boolean evaluate(List<Food> subList) {
        return rules.stream()
                .map(rule -> rule.evaluate(subList))
                .reduce(Boolean::logicalAnd)
                .orElse(false);
    }
}
