package com.zeke.fpx.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Zeke on 6/20/2016.
 */

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal amount;
    private Date closeDate;
    private String currencyIsoCode;
    private String description;
    private Byte[] isClosed;
    private Byte[] isDeleted;
    private Byte[] isWon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
