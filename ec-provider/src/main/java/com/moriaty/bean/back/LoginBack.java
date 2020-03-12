package com.moriaty.bean.back;

import com.moriaty.bean.db.Attach;
import lombok.Data;

@Data
public class LoginBack {
    private String phone;
    private String nickname;
    private long money;
    private Attach portrait;
    private int type;

    private String token;
}
