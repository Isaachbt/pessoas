package com.cadastro.pessoas.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonRecordDto(@NotBlank String name,@NotBlank int age, @NotBlank double saldo,@NotBlank String cpf) {
}
