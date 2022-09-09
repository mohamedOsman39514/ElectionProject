package com.example.elections.rest.exception;

import org.springframework.stereotype.Component;

import java.sql.SQLException;


@Component
public class PSQLException {
    public String getError(Exception ex){
        String msg = ex.getMessage();
        if (ex.getCause().getCause() instanceof SQLException) {
            SQLException e = (SQLException) ex.getCause().getCause();
            if (e.getMessage().contains("Key")) {
                msg = e.getMessage().substring(e.getMessage().indexOf("Key"));
            }
        }
        return msg;
    }
}
