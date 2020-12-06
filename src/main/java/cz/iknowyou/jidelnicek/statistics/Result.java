package cz.iknowyou.jidelnicek.statistics;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Result {

    private String name;
    private float result;
    private String units;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Result{");
        sb.append("name='").append(name).append('\'');
        sb.append(", result=").append(result);
        sb.append(", units='").append(units).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
