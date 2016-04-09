package com.clearscore.art;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by alaega on 26/01/2016.
 */
public interface Gallery {
    /**
     * Add a	piece	of	art	to	the	gallery,replacing	any	existing	equivalent	piece
     * @param art
     */
    void addArt(final Art art);

    /**
     * Delete an art object
     * @param art
     */
    void deleteArt(final Art art);

    /**
     *
     * @return
     */
    List<Art> getAllArt();

    /**
     * Returns	the	names	of	all	artists	with	art	currently	in	the	gallery,	in alphabetic	order
     * @return
     */
    List<String> getArtists();

    /**
     * Get all art by artist
     * @param artistName
     * @return
     */
    List<Art> getArtByArtist(final String artistName);

    /**
     * Get all art after given creationDate
     * @param creationDate
     * @return
     */
    List<Art> getRecentArt(Date creationDate);

    /**
     * Get all art by price
     * @param lowerPrice
     * @param upperPrice
     * @return
     */
    List<Art> getArtByPrice(BigDecimal lowerPrice, BigDecimal upperPrice);
}
