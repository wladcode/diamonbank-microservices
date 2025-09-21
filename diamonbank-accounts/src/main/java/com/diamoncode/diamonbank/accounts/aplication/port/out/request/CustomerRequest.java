package com.diamoncode.diamonbank.accounts.aplication.port.out.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
     @NotNull(message = "Name is required")
     @Size(min = 2, max = 50)
     @Pattern(regexp = "^[a-zA-Z ]+$")
     String name,

     @NotNull(message = "Email is required")
     @NotEmpty(message = "Email is required")
     @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
     String email
){}
