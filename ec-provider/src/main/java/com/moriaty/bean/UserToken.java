package com.moriaty.bean;

import com.moriaty.login.storage.Token;
import lombok.Data;

@Data
public class UserToken extends Token {
    private String phone;
}
