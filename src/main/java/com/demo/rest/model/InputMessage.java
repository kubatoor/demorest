package com.demo.rest.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class InputMessage {
    @NotEmpty
    String text;
}
