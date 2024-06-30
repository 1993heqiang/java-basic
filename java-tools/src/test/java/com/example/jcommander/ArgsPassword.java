package com.example.jcommander;

import com.beust.jcommander.Parameter;
import lombok.Data;

@Data
public class ArgsPassword {
    @Parameter(names = "-password", description = "Connection password", password = true)
    private String password;
}
