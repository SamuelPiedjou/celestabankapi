package com.celestabank.celestabankapi.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMapper {
    private String url;
    private String error_message;
    private Date error_time;
}
