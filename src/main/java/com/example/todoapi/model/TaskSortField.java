package com.example.todoapi.model;

import com.example.todoapi.exception.BusinessRuleException;
import java.util.Locale;

public enum TaskSortField {
  DEADLINE;

  public static TaskSortField fromValue(String value) {
    if (value == null || value.isBlank()) {
      return null;
    }
    try {
      return valueOf(value.trim().toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException ex) {
      throw new BusinessRuleException("Invalid value '" + value + "' for parameter 'sortBy'");
    }
  }
}
