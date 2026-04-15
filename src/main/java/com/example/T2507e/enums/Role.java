package com.example.T2507e.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    USER(0),ADMIN(1);

    private final int value;

    public static Role fromValue(int value){
        for(Role role : values()){
            if(role.getValue() == value) return role;
        }
        throw new RuntimeException("Invalid role value");
    }
}
