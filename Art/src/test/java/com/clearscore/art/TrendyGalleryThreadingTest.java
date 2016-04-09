package com.clearscore.art;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by alaega on 26/01/2016.
 */
public class TrendyGalleryThreadingTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(TrendyGalleryThreadingTest.class);

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
        gallery.addArt(picasso);
        gallery.addArt(michaelango);
        gallery.addArt(tapestry);
    }

    @Test
    public void shouldAddMultipleArtsToGallery() {
        int runs = ConcurrencyUtils.runConcurrently(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Art banksy = new Art(Thread.currentThread().getName() + i, ArtType.PAINTING, "The mural", getDate("01/01/2010"));
                    banksy.setAskingPrice(BigDecimal.TEN);
                    gallery.addArt(banksy);
                    List<Art> art = gallery.getAllArt();
                    LOGGER.debug("Art list size " + art.size());
                    Assert.assertNotNull(art);
                }
                return 1;
            }
        }, 13);
        List<Art> art = gallery.getAllArt();
        Assert.assertEquals(133, art.size());

    }

    @Test
    public void shouldAddAndRemoveArtToGallery() {
        Art leonardo = new Art("Mona lisa", ArtType.PAINTING, "leonardo", getDate("01/01/1904"));
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gallery.addArt(leonardo);
                Assert.assertTrue(gallery.getAllArt().contains(leonardo));
            }
        });
        Thread removeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                gallery.deleteArt(leonardo);
                Assert.assertFalse(gallery.getAllArt().contains(leonardo));
            }
        });

        addThread.start();
        removeThread.start();

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
