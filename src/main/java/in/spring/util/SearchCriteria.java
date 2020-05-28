package in.spring.util;

import in.spring.enums.SearchOperation;
import lombok.Getter;

@Getter
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    // getters and setters, toString(), ... (omitted for brevity)
}
