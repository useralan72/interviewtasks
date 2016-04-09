package com.clearscore.art;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by alaega on 26/01/2016.
 */
public class TrendyGallery implements Gallery{

    protected static final Logger LOGGER = LoggerFactory.getLogger(TrendyGallery.class);

    private Set<Art> artSet = new CopyOnWriteArraySet<Art>();

    @Override
    public void addArt(Art art) {
        LOGGER.debug("Adding Art {} ", art);
        artSet.add(art);
    }

    @Override
    public void deleteArt(Art art) {
        LOGGER.debug("Deleting Art {} ", art);
        artSet.remove(art);
    }

    @Override
    public List<Art> getAllArt() {
        return Lists.newArrayList(artSet);
    }

    @Override
    public List<String> getArtists() {
        List<String> artists = new ArrayList<String>(artSet.size());
        for (Art art: artSet) {
            artists.add(art.getArtistName());
        }
        Collections.sort(artists);
        return artists;
    }

    @Override
    public List<Art> getArtByArtist(String artistName) {
        List<Art> artistArt = new ArrayList<>();
        for (Art art: artSet) {
            if (art.getArtistName().equalsIgnoreCase(artistName)) {
                artistArt.add(art);
            }
        }
        return artistArt;
    }

    @Override
    public List<Art> getRecentArt(Date creationDate) {
        List<Art> recentArt = new ArrayList<>();
        for (Art art: artSet) {
            if (art.getCreatedDate().after(creationDate)) {
                recentArt.add(art);
            }
        }
        return recentArt;
    }

    @Override
    public List<Art> getArtByPrice(BigDecimal lowerPrice, BigDecimal upperPrice) {
        List<Art> artByPriceList = new ArrayList<>();
        for (Art art: artSet) {
            if ((null != art.getAskingPrice() && art.getAskingPrice().compareTo(lowerPrice) == 1)
                    && (null != art.getAskingPrice() && art.getAskingPrice().compareTo(upperPrice) == -1)) {
                artByPriceList.add(art);
            }
        }
        return artByPriceList;
    }
}
