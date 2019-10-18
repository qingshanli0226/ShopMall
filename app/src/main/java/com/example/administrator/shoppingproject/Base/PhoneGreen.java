package com.example.administrator.shoppingproject.Base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class PhoneGreen {
    @Id
    String phonenumber;
    String password;

    @Override
    public String toString() {
        return "PhoneGreen{" +
                "phonenumber='" + phonenumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public String getPhonenumber() {
        return this.phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
@Generated(hash = 921886488)
public PhoneGreen() {
}
@Generated(hash = 548367054)
public PhoneGreen(String phonenumber, String password) {
    this.phonenumber = phonenumber;
    this.password = password;
}
}
