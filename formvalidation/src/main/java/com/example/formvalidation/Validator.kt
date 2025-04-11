package com.example.formvalidation

class Validator {

    private val rules = mutableListOf<() -> ValidationResult>()

    fun addRule(rule: () -> ValidationResult): Validator {
        rules.add(rule)
        return this
    }

    fun validate(): ValidationResult {
        for (rule in rules) {
            val result = rule()
            if (result is ValidationResult.Error) return result
        }
        return ValidationResult.Success
    }
}
