package com.example.administrator.shoppingproject.Base;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class GreenDao  {
    @Id
    String Tittle;
    String Text;

    @Override
    public String toString() {
        return "GreenDao{" +
                "Tittle='" + Tittle + '\'' +
                ", Text='" + Text + '\'' +
                '}';
    }
    public String getTittle() {
        return this.Tittle;
    }
    public void setTittle(String Tittle) {
        this.Tittle = Tittle;
    }
    public String getText() {
        return this.Text;
    }
    public void setText(String Text) {
        this.Text = Text;
    }
    @Generated(hash = 766040118)
    public GreenDao() {
    }
    @Generated(hash = 1663600914)
    public GreenDao(String Tittle, String Text) {
        this.Tittle = Tittle;
        this.Text = Text;
    }
}
