package com.cadastro.pessoas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PersonRecordDto(@NotBlank String name, @NotNull Integer age, @NotNull BigDecimal saldo, @NotBlank String cpf) {
}
