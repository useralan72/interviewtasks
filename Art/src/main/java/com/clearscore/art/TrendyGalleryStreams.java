package com.clearscore.art;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by alaega on 27/01/2016.
 */
public class TrendyGalleryStreams extends TrendyGallery {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TrendyGalleryStreams.class);

    @Override
    public List<Art> getArtByArtist(String artistName) {
        return getAllArt().stream()
                .filter(p -> p.getArtistName().equalsIgnoreCase(artistName))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Art> getRecentArt(Date creationDate) {
        return getAllArt().stream().filter(p -> p.getCreatedDate().after(creationDate)).collect(Collectors.toList());
    }

    @Override
    public List<Art> getArtByPrice(BigDecimal lowerPrice, BigDecimal upperPrice) {
        Predicate<Art> nonNullPredicate = Objects::nonNull;
        Predicate<Art> askingPriceNotNull = p -> p.getAskingPrice() != null;
        Predicate<Art> askingPriceLow = p -> p.getAskingPrice().compareTo(lowerPrice) == 1;
        Predicate<Art> askingPriceHigh = p -> p.getAskingPrice().compareTo(upperPrice) == -1;

        Predicate<Art> fullPredicate = nonNullPredicate.and(askingPriceNotNull)
                .and(askingPriceLow).and(askingPriceHigh);
        return getAllArt().stream().filter(fullPredicate)
                .collect(Collectors.toList());
    }
}
