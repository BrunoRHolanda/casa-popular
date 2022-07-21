package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("selection_rule")
public class SelectionRule {
    private String ruleId;
}
