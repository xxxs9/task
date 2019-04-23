package com.gameloft9.demo.dataaccess.model.user;

import javax.persistence.*;

/**
 * 英雄出装
 */
@Table(name = "hero_equip")
public class HeroEquip {

    @Column(name = "ID")
    @Id
    private  Integer id;

    @Column(name = "RECORD_ID")
    private String recordId;

    /**
     * 出门装
     */
    @Column(name = "PRE_CZ")
    private String preCz;

    /**
     * 出门装思路
     */
    @Column(name = "PRE_EXPLAIN")
    private String preExplain;

    /**
     * 中期核心装
     */
    @Column(name = "MID_CZ")
    private String midCz;

    /**
     * 核心装思路
     */
    @Column(name = "MID_EXPLAIN")
    private String midExplain;

    /**
     * 顺风装
     */
    @Column(name = "END_CZ")
    private String endCz;

    /**
     * 顺风装思路
     */
    @Column(name = "END_EXPLAIN")
    private String endExplain;

    /**
     * 逆风装
     */
    @Column(name = "NF_CZ")
    private String nfCz;

    /**
     * 逆风装思路
     */
    @Column(name = "NF_EXPLAIN")
    private String nfExplain;

    /**
     * @return RECORD_ID
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * @return PRE_CZ
     */
    public String getPreCz() {
        return preCz;
    }

    /**
     * @param preCz
     */
    public void setPreCz(String preCz) {
        this.preCz = preCz;
    }

    /**
     * @return PRE_EXPLAIN
     */
    public String getPreExplain() {
        return preExplain;
    }

    /**
     * @param preExplain
     */
    public void setPreExplain(String preExplain) {
        this.preExplain = preExplain;
    }

    /**
     * @return MID_CZ
     */
    public String getMidCz() {
        return midCz;
    }

    /**
     * @param midCz
     */
    public void setMidCz(String midCz) {
        this.midCz = midCz;
    }

    /**
     * @return MID_EXPLAIN
     */
    public String getMidExplain() {
        return midExplain;
    }

    /**
     * @param midExplain
     */
    public void setMidExplain(String midExplain) {
        this.midExplain = midExplain;
    }

    /**
     * @return END_CZ
     */
    public String getEndCz() {
        return endCz;
    }

    /**
     * @param endCz
     */
    public void setEndCz(String endCz) {
        this.endCz = endCz;
    }

    /**
     * @return END_EXPLAIN
     */
    public String getEndExplain() {
        return endExplain;
    }

    /**
     * @param endExplain
     */
    public void setEndExplain(String endExplain) {
        this.endExplain = endExplain;
    }

    /**
     * @return NF_CZ
     */
    public String getNfCz() {
        return nfCz;
    }

    /**
     * @param nfCz
     */
    public void setNfCz(String nfCz) {
        this.nfCz = nfCz;
    }

    /**
     * @return NF_EXPLAIN
     */
    public String getNfExplain() {
        return nfExplain;
    }

    /**
     * @param nfExplain
     */
    public void setNfExplain(String nfExplain) {
        this.nfExplain = nfExplain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}