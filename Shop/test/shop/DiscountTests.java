package shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.documents.Sale;
import shop.enums.Category;
import shop.reference.AutoParts;
import shop.reference.Client;

@RunWith(JUnit4.class)
public class DiscountTests {

    private static Shop shop;

    private AutoParts   autoPart;
    private Client      client;


    @BeforeClass
    public static void initClass(){
        shop = new Shop();
    }

    @Before
    public void init(){
        autoPart = new AutoParts(shop);
        client   = new Client(shop);

        autoPart.setId(1);
        autoPart.setName("Akkumulyator");
        autoPart.setCatalogNumber("800 25 03");
        autoPart.setCategoriya(Category.ELECTROOBORUDOVANIE);

        client.setId(1);
        client.setName("FOP Roga i Koputa");
    }

    @Test
    public void testDiscount0(){

        float summa = 499.99f;

        float percent = shop.getServiceMethodDocument().getDiscountPercentForSummaSale(summa);

        assertTrue ("Percent discount must be 0", percent == 0f);
    }

    @Test
    public void testDiscount5(){

        float summa = 999f;

        float percent = shop.getServiceMethodDocument().getDiscountPercentForSummaSale(summa);

        assertTrue("Percent discount must be 5", percent == 5f);
    }

    @Test
    public void testDiscount10(){

        float summa = 1000f;

        float percent = shop.getServiceMethodDocument().getDiscountPercentForSummaSale(summa);

        assertTrue("Percent discount must be 10", percent == 10f);
    }

}
