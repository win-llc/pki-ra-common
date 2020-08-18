package com.winllc.acme.common.model.acme;

import com.winllc.acme.common.contants.IdentifierType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Identifier {
    //required
    private String type;
    //required
    private String value;

    public Identifier(IdentifierType type, String value) {
        this.type = type.toString();
        this.value = value;
    }

    public Identifier() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Identifier)) return false;

        Identifier that = (Identifier) o;

        return new EqualsBuilder()
                .append(getType(), that.getType())
                .append(getValue(), that.getValue())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getType())
                .append(getValue())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}