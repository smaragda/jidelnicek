package cz.iknowyou.jidelnicek.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;


/**
 * Potravina;Bílkoviny;Uhlohydráty;Tuky;Energie
 * ;(g/100g);(g/100g);(g/100g);(kcal/100g)
 */
@Data
public class Food {

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private float proteins;

    @CsvBindByPosition(position = 2)
    private float carbs;

    @CsvBindByPosition(position = 3)
    private float fats;

    @CsvBindByPosition(position = 4)
    private float energy;

    // 50g
    private float amount = 0.5f;

    private void calculateEnergy() {
        this.energy = 9 * fats + 4 * carbs + 4 * proteins;
    }

}
