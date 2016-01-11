package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.Shop;
import shop.reference.AutoParts;

@RunWith(JUnit4.class)
public class ReferenceTests {

    private static Shop shop;
    private AutoParts   autoParts;

    @BeforeClass
    public static void initClass(){
        shop = new Shop();
    }

    @Before
    public void init(){
        autoParts = new AutoParts(shop);
    }

    @Test
    public void testCreateAutoPartsId(){
        Assert.assertTrue(autoParts.getId()==0);
    }

    @Test
    public void testCreateAutoPartsName(){
        Assert.assertNull(autoParts.getName());
    }

    @Test
    public void testCreateAutoPartsCatalogNumber(){
        Assert.assertNull(autoParts.getCatalogNumber());
    }

    @Test
    public void testSetAutoPartsCatalogNumber(){
        String newCatalogNumber = "550 02 02";
        autoParts.setCatalogNumber(newCatalogNumber);
        Assert.assertEquals(newCatalogNumber, autoParts.getCatalogNumber());
    }



}
