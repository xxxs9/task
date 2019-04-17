package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

@Table(name = "get_reptile_id")
public class GetReptileId {

    @Column(name = "ID")
    @Id
    private Integer id;

    @Column(name = "CODE")
    private String code;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
}