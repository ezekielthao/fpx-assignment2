package com.zeke.fpx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Zeke on 6/20/2016.
 */

@Entity
public class Opportunity {

    @Id
    @Column(name="ID")
    private String id;

    private String name;

    private BigDecimal amount;

    @Column(name="CloseDate")
    private Date closeDate;

    @Column(name="CurrencyIsoCode")
    private String currencyIsoCode;

    private String description;

    @Column(name="IsClosed")
    private Byte[] isClosed;

    @Column(name="IsDeleted")
    private Byte[] isDeleted;

    @Column(name="IsWon")
    private Byte[] isWon;

    @Transient
    private boolean closed;

    @Transient
    private boolean deleted;

    @Transient
    private boolean won;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getCurrencyIsoCode() {
        return currencyIsoCode;
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        this.currencyIsoCode = currencyIsoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte[] getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Byte[] isClosed) {
        this.isClosed = isClosed;
    }

    public Byte[] getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte[] isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte[] getIsWon() {
        return isWon;
    }

    public void setIsWon(Byte[] isWon) {
        this.isWon = isWon;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
