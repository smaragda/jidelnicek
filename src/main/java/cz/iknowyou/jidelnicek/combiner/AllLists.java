package cz.iknowyou.jidelnicek.combiner;


import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class AllLists {
    @Singular
    List<ListWrap<?>> lists;

    public long getTotalIterations() {
        return lists
                .stream()
                .map(listWrap -> listWrap.getItems().size())
                .reduce(1, (size1, size2) -> size1 * size2);
    }
}

