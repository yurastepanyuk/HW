package com.dreamshop.entity.catalogs;


public abstract class Catalog {
    protected long    id;
    protected String  name;

    public Catalog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
