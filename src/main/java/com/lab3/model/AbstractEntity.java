package com.lab3.model;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {
    public abstract int getId();
    public abstract void setId(int id);
}