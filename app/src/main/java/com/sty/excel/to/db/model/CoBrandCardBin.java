package com.sty.excel.to.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
@DatabaseTable(tableName = "co_brand_card_bin")
public class CoBrandCardBin implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ID_FIELD_NAME = "id";
    public static final String ISSUER_NAME = "issuer_name";
    public static final String ISSUER_IIN = "issuer_iin";
    public static final String CARD_LEVEL = "card_level";
    public static final String COUNTRY_CODE = "country_code";
    public static final String BIN_LENGTH = "bin_length";
    public static final String CARD_BIN = "card_bin";
    public static final String PAN_LENGTH = "pan_length";
    public static final String CARD_TYPE = "card_type";

    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private int id;
    @DatabaseField(columnName = ISSUER_NAME)
    private String issuerName;
    @DatabaseField(columnName = ISSUER_IIN)
    private String issuerIIN;
    @DatabaseField(columnName = CARD_LEVEL)
    private String cardLevel;
    @DatabaseField(columnName = COUNTRY_CODE)
    private String countryCode;
    @DatabaseField(columnName = BIN_LENGTH)
    private String binLength;
    @DatabaseField(columnName = CARD_BIN)
    private String cardBin;
    @DatabaseField(columnName = PAN_LENGTH)
    private String panLength;
    @DatabaseField(columnName = CARD_TYPE)
    private String cardType;

    private CoBrandCardBin(){} //important

    private CoBrandCardBin(Builder builder){
        this.issuerName = builder.issuerName;
        this.issuerIIN = builder.issuerIIN;
        this.cardLevel = builder.cardLevel;
        this.countryCode = builder.countryCode;
        this.binLength = builder.binLength;
        this.cardBin = builder.cardBin;
        this.panLength = builder.panLength;
        this.cardType = builder.cardType;
    }

    public int getId() {
        return id;
    }

    public String getIssuerName() {
        return issuerName;
    }


    public String getIssuerIIN() {
        return issuerIIN;
    }


    public String getCardLevel() {
        return cardLevel;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBinLength() {
        return binLength;
    }

    public String getCardBin() {
        return cardBin;
    }

    public String getPanLength() {
        return panLength;
    }

    public String getCardType() {
        return cardType;
    }

    public static class Builder{
        private String issuerName;
        private String issuerIIN;
        private String cardLevel;
        private String countryCode;
        private String binLength;
        private String cardBin;
        private String panLength;
        private String cardType;

        public Builder setIssuerName(String issuerName) {
            this.issuerName = issuerName;
            return this;
        }

        public Builder setIssuerIIN(String issuerIIN) {
            this.issuerIIN = issuerIIN;
            return this;
        }

        public Builder setCardLevel(String cardLevel) {
            this.cardLevel = cardLevel;
            return this;
        }

        public Builder setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder setBinLength(String binLength) {
            this.binLength = binLength;
            return this;
        }

        public Builder setCardBin(String cardBin) {
            this.cardBin = cardBin;
            return this;
        }

        public Builder setPanLength(String panLength) {
            this.panLength = panLength;
            return this;
        }

        public Builder setCardType(String cardType) {
            this.cardType = cardType;
            return this;
        }

        public CoBrandCardBin build(){
            return new CoBrandCardBin(this);  //build()返回目标对象
        }
    }
}
