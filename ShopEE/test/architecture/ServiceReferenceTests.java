package architecture;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.Shop;
import shop.enums.Category;
import shop.reference.AutoParts;
import shop.reference.ServiceMethodReference;

import java.util.List;


@RunWith(JUnit4.class)
public class ServiceReferenceTests {

    private static Shop                     shop;
    private static WorkWithMySqlMock        workWithMySqlMock;
    private static ServiceMethodReference   serviceMethodReference;

    @BeforeClass
    public static void initClass(){

        shop = new Shop();

        workWithMySqlMock = new WorkWithMySqlMock();
        workWithMySqlMock.inicialisation();

        shop.setDb(workWithMySqlMock);

        serviceMethodReference = shop.getServiceMethodReference();

    }


    @Test
    public void testSaveMethod(){

        List<AutoParts> autoPartsList = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

        int oldSize = autoPartsList.size();

        AutoParts autoPart = new AutoParts(shop);
        //autoPart.setId(1);
        autoPart.setName("Akkumulyator");
        autoPart.setCatalogNumber("800 25 03");
        autoPart.setCategoriya(Category.ELECTROOBORUDOVANIE);

        serviceMethodReference.save(autoPart);

        List<AutoParts> autoPartsListNew = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

        int newSize = autoPartsListNew.size();

        assertTrue(newSize == oldSize+1);
    }

    @Test
    public void testGetReferenceObjectById(){

        AutoParts autoPart = new AutoParts(shop);
        autoPart.setName("Dunlop Winter H+ 205/75 R18");
        autoPart.setCatalogNumber("Dunlop WH+ 2016");
        autoPart.setCategoriya(Category.SHINA);

        serviceMethodReference.save(autoPart);

        int id = autoPart.getId();

        AutoParts autoPartFind = serviceMethodReference.getReferenceObjectById(id, AutoParts.class);

        assertNotNull("Method GetReferenceObjectById failed",autoPartFind);

    }

    @Test
    public void testGetReferenceObjectByName(){

        AutoParts autoPart = new AutoParts(shop);
        autoPart.setName("Mishelin 185/55 R19");
        autoPart.setCatalogNumber("Mishelin SummerTime U+");
        autoPart.setCategoriya(Category.SHINA);

        serviceMethodReference.save(autoPart);

        String name = autoPart.getName();

        AutoParts autoPartFind = serviceMethodReference.getReferenceObjectByName(name, AutoParts.class);

        assertNotNull("Method GetReferenceObjectByName failed",autoPartFind);

    }


}
