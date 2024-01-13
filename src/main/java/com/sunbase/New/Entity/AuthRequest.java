package com.sunbase.New.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;

}
// Compare this snippet from src/main/java/com/sunbase/Entity/Customer.java: