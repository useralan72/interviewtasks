package com.clearscore.art;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by alaega on 26/01/2016.
 */
public final class Art {

    private final String name;
    private final ArtType type;
    private final String artistName;
    private volatile BigDecimal askingPrice;
    private Date createdDate;

    public Art(String name, ArtType type, String artistName, Date createdDate) {
        this.name = name;
        this.type = type;
        this.artistName = artistName;
        this.createdDate = createdDate;
    }

    public BigDecimal getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(BigDecimal askingPrice) {
        this.askingPrice = askingPrice;
    }

    public String getName() {
        return name;
    }

    public ArtType getType() {
        return type;
    }

    public String getArtistName() {
        return artistName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Art that = (Art) o;
        return Objects.equal(artistName, that.artistName) &&
                Objects.equal(name, that.name) &&
                Objects.equal(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(artistName, name, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("artistName", artistName)
                .add("name", name)
                .add("type", type)
                .toString();
    }
}
