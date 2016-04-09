package com.clearscore.art;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by alaega on 26/01/2016.
 */
public class TrendyGalleryTest {

    Gallery gallery;

    Art picasso;
    Art michaelango;
    Art tapestry;

    @Before
    public void setUp() {
        gallery = new TrendyGallery();
        picasso = new Art("The old guitarist", ArtType.PAINTING, "picasso", getDate("01/01/1904"));
        michaelango = new Art("David", ArtType.SCULPTURE, "michaelangelo", getDate("01/01/1912"));
        tapestry = new Art("tapestryartist", ArtType.TAPESTRY, "tapestryguy", getDate("01/01/1995"));
        tapestry.setAskingPrice(BigDecimal.TEN);
        gallery.addArt(picasso);
        gallery.addArt(michaelango);
        gallery.addArt(tapestry);
    }

    @Test
    public void shouldAddAndDeleteArt() {
        int initialSize = gallery.getAllArt().size();
        Art picasso = new Art("picasso", ArtType.PAINTING, "The weaping woman", getDate("01/01/1937"));

        gallery.addArt(picasso);
        Assert.assertEquals(initialSize + 1, gallery.getAllArt().size());

        gallery.deleteArt(picasso);
        Assert.assertEquals(initialSize, gallery.getAllArt().size());
    }

    @Test
    public void givenGalleryShouldReturnAllArt() {
        List<Art> allArtList = gallery.getAllArt();

        Assert.assertNotNull(allArtList);
        Assert.assertEquals(3, gallery.getAllArt().size());
    }

    @Test
    public void givenGalleryAndArtistShouldReturnAllArt() {
        List<Art> allPicasso = gallery.getArtByArtist("picasso");

        Assert.assertNotNull(allPicasso);
        Assert.assertEquals(1, allPicasso.size());
        Assert.assertEquals(picasso, allPicasso.get(0));
    }

    @Test
    public void givenGalleryAndDateShouldReturnRecentArt() {
        List<Art> allrecent = gallery.getRecentArt(getDate("01/01/1993"));

        Assert.assertNotNull(allrecent);
        Assert.assertEquals(1, allrecent.size());
        Assert.assertEquals(tapestry, allrecent.get(0));
    }

    @Test
    public void givenGalleryAndPriceRangeShouldReturnArtList() {
        List<Art> allByPrice = gallery.getArtByPrice(new BigDecimal("2"), new BigDecimal("12"));

        Assert.assertNotNull(allByPrice);
        Assert.assertEquals(1, allByPrice.size());
        Assert.assertEquals(tapestry, allByPrice.get(0));
    }

    @Test
    public void givenGalleryShouldReturnAllArtistsInAlphabeticalOrder() {
        List<String> artists = gallery.getArtists();
        Assert.assertNotNull(artists);
        Assert.assertEquals(3, artists.size());
        Assert.assertTrue(artists.get(0).compareTo(artists.get(2)) < 0);
    }

    private Date getDate(String dateString) {
        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateParser.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e);
        }
        return date;
    }
}
