package com.cadastro.pessoas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRecordDto(@NotBlank String name, @NotNull Integer age, @NotNull Double saldo, @NotBlank String cpf) {
}
