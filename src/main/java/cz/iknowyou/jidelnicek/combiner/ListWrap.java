package cz.iknowyou.jidelnicek.combiner;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Getter
@Builder
public class ListWrap<T> {
    @Singular
    List<T> items;
}
