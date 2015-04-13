package com.jcas.utilerias;

/**
 * Created by MBrandle on 10/10/2014.
 */
public class Organismo {
    private int id;
    private String desc;
    private String[] organismos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getOrganismos() {
        return organismos;
    }

    public void setOrganismos(String[] organismos) {
        this.organismos = organismos;
    }
}
