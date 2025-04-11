package com.example.formvalidation

import android.widget.CheckBox

object ValidationRules {

    fun isNotEmpty(
        value: String,
        errorMessage: String = "This field is required"
    ): ValidationResult {
        return if (value.trim().isEmpty()) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun isEmail(value: String, errorMessage: String = "Invalid email"): ValidationResult {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return if (!emailRegex.matches(value)) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun minLength(
        value: String,
        length: Int,
        errorMessage: String = "Minimum length required: $length"
    ): ValidationResult {
        return if (value.length < length) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun maxLength(
        value: String,
        length: Int,
        errorMessage: String = "Maximum length allowed: $length"
    ): ValidationResult {
        return if (value.length > length) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun isNumber(
        value: String,
        errorMessage: String = "Only numeric input allowed"
    ): ValidationResult {
        return if (value.toDoubleOrNull() == null) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun isChecked(
        checkbox: CheckBox,
        errorMessage: String = "This field must be checked"
    ): ValidationResult {
        return if (!checkbox.isChecked) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun matches(
        value1: String,
        value2: String,
        errorMessage: String = "Fields do not match"
    ): ValidationResult {
        return if (value1 != value2) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun isPhoneNumber(
        value: String,
        errorMessage: String = "Invalid phone number"
    ): ValidationResult {
        val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        return if (!phoneRegex.matches(value)) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun isWithinRange(
        value: String,
        min: Int,
        max: Int,
        errorMessage: String = "Value must be between $min and $max"
    ): ValidationResult {
        val intValue = value.toIntOrNull()
        return if (intValue == null || intValue < min || intValue > max) {
            ValidationResult.Error(errorMessage)
        } else {
            ValidationResult.Success
        }
    }

    fun isAlpha(value: String, errorMessage: String = "Only alphabets allowed"): ValidationResult {
        return if (!value.matches(Regex("^[a-zA-Z]+$"))) ValidationResult.Error(errorMessage)
        else ValidationResult.Success
    }

    fun isAlphaNumeric(value: String, errorMessage: String = "Only letters and numbers allowed"): ValidationResult {
        return if (!value.matches(Regex("^[a-zA-Z0-9]+$"))) ValidationResult.Error(errorMessage)
        else ValidationResult.Success
    }

    fun containsSpecialChar(value: String, errorMessage: String = "Must contain at least one special character"): ValidationResult {
        return if (!value.matches(Regex(".*[^a-zA-Z0-9 ].*"))) ValidationResult.Error(errorMessage)
        else ValidationResult.Success
    }

    fun startsWith(value: String, prefix: String, errorMessage: String = "Must start with $prefix"): ValidationResult {
        return if (!value.startsWith(prefix)) ValidationResult.Error(errorMessage)
        else ValidationResult.Success
    }

    fun endsWith(value: String, suffix: String, errorMessage: String = "Must end with $suffix"): ValidationResult {
        return if (!value.endsWith(suffix)) ValidationResult.Error(errorMessage)
        else ValidationResult.Success
    }

    fun custom(condition: Boolean, errorMessage: String): ValidationResult {
        return if (!condition) ValidationResult.Error(errorMessage)
        else ValidationResult.Success
    }

}
