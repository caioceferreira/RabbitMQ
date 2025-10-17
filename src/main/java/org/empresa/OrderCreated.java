package org.empresa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderCreated (
    @NotBlank String id,
    @Min(0) long amountCents)
{}
