package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import lombok.Data;
import lombok.NonNull;

@Data
public class Rule extends Model {
    @NonNull
    private Integer code;
    @NonNull
    private RuleType type;
}
