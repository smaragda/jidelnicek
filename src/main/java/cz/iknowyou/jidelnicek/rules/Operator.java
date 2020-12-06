package cz.iknowyou.jidelnicek.rules;

public enum Operator {

    GREATER_THAN, LOWER_THAN, ROUGHLY_EQUALS;

    public boolean evaluate(float result, float templateResult) {
        switch (this) {
            case LOWER_THAN:
                return result < templateResult;
            case GREATER_THAN:
                return result > templateResult;
            case ROUGHLY_EQUALS:
                return result == templateResult; // TODO percent equality
            default:
                throw new IllegalArgumentException("unknown operator");
        }
    }
}
