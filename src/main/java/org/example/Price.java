package org.example;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class Price implements Comparable<Price> {

    //region Fields
    private long id;
    private String productCode;
    private int number;
    private int depart;
    private LocalDateTime begin;
    private LocalDateTime end;
    private long value;

    private boolean isNew;
    //endregion

    //region GetSetters


    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public int getDepart() {
        return depart;
    }

    public void setBegin(LocalDateTime begin) {
        this.begin = begin;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean getIsNew() {
        return this.isNew;
    }
    //endregion

    //region Constructors
    public Price(Price price, long id, LocalDateTime begin, LocalDateTime end) {
        this.id = id;
        this.productCode = price.getProductCode();
        this.number = price.getNumber();
        this.depart = price.getDepart();
        this.begin = begin;
        this.end = end;
        this.value = price.value;
        this.isNew = false;
    }

    public Price(long id, String productCode, int number, int depart, LocalDateTime begin, LocalDateTime end, long value) {
        this.id = id;
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
        this.isNew = false;
    }
    //endregion

    //region Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return number == price.number && depart == price.depart && Objects.equals(productCode, price.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, number, depart);
    }

    @Override
    public String toString() {
        return "\nPrice{" +
                "\nid=" + id +
                ", \nproductCode='" + productCode + '\'' +
                ", \nnumber=" + number +
                ", \ndepart=" + depart +
                ", \nbegin=" + begin +
                ", \nend=" + end +
                ", \nvalue=" + value +
                "}\n";
    }

    @Override
    public int compareTo(Price price) {
        return Comparator.comparing(Price::getProductCode)
                .thenComparing(Price::getNumber)
                .thenComparing(Price::getDepart)
                .thenComparing(Price::getBegin)
                .compare(this, price);
    }
    //endregion
}

