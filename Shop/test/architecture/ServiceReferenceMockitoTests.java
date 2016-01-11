package architecture;

//import shop.database.DB;
//import shop.database.QueryToDB;
//
//import java.beans.PropertyVetoException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import shop.Shop;
import shop.database.WorkWithMySQL;
import shop.enums.Category;
import shop.inforamation.Prices;
import shop.reference.AutoParts;
import shop.reference.Reference;
import shop.reference.ServiceMethodReference;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class ServiceReferenceMockitoTests {

    private static Shop                     shop;
    private static WorkWithMySQL            workWithMySQL;
    private static ServiceMethodReference   serviceMethodReference;

    private static List<AutoParts> products;

    private static int      idFind;
    private static String   nameFind;
    private static Object   objectAdd;
    private static Object[] objectFilter;
    private static Class    clsTypeResource;

    @BeforeClass
    public static void initClass(){

        products = new ArrayList<>();

        shop = new Shop();

        workWithMySQL = Mockito.mock(WorkWithMySQL.class);
        shop.setDb(workWithMySQL);

        serviceMethodReference = shop.getServiceMethodReference();

    }

    @Before
    public void init(){
        idFind          = 0;
        nameFind        = null;
        objectAdd       = null;
        objectFilter    = new Object[2];
        clsTypeResource = null;
    }

    @Test
    public void testSaveMethod(){
        clsTypeResource = AutoParts.class;

        Mockito.when(workWithMySQL.getDataFromTable(clsTypeResource)).thenReturn((List)products);
        List<AutoParts> autoPartsList = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

        int oldSize = autoPartsList.size();

        AutoParts autoPart = new AutoParts(shop);
        //autoPart.setId(1);
        autoPart.setName("Akkumulyator");
        autoPart.setCatalogNumber("800 25 03");
        autoPart.setCategoriya(Category.ELECTROOBORUDOVANIE);

        objectAdd = autoPart;
        Mockito.when(workWithMySQL.addNewRecord_(objectAdd)).thenReturn(addNewRecord(objectAdd));
        objectFilter    = new Object[]{((AutoParts)objectAdd).getCatalogNumber(), "catalogNumber"};
        Mockito.when(workWithMySQL.getResourceByObject(objectFilter, clsTypeResource)).thenReturn(getAutoPartsByCatalogNumber(objectFilter));
        serviceMethodReference.save((Reference)objectAdd);

        Mockito.when(workWithMySQL.getDataFromTable(clsTypeResource)).thenReturn((List)products);
        List<AutoParts> autoPartsListNew = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

        int newSize = autoPartsListNew.size();

        assertTrue(newSize == oldSize + 1);
    }

    @Test
    public void testGetReferenceObjectById(){

        AutoParts autoPart = new AutoParts(shop);
        autoPart.setName("Dunlop Winter H+ 205/75 R18");
        autoPart.setCatalogNumber("Dunlop WH+ 2016");
        autoPart.setCategoriya(Category.SHINA);

        objectAdd = autoPart;
        Mockito.when(workWithMySQL.addNewRecord_(objectAdd)).thenReturn(addNewRecord(objectAdd));
        serviceMethodReference.save(autoPart);

        int id = autoPart.getId();
        idFind          = id;
        clsTypeResource = AutoParts.class;

        Mockito.when(workWithMySQL.getDataFromTable(clsTypeResource)).thenReturn((List)products);
        Mockito.when(workWithMySQL.getDataFromTable(Prices.class)).thenReturn((List)new ArrayList<Prices>());
        AutoParts autoPartFind = serviceMethodReference.getReferenceObjectById(id, AutoParts.class);

        assertNotNull("Method GetReferenceObjectById failed",autoPartFind);

    }

    @Test
    public void testGetReferenceObjectByName(){

        AutoParts autoPart = new AutoParts(shop);
        autoPart.setName("Mishelin 185/55 R19");
        autoPart.setCatalogNumber("Mishelin SummerTime U+");
        autoPart.setCategoriya(Category.SHINA);

        objectAdd       = autoPart;
        clsTypeResource = AutoParts.class;
        Mockito.when(workWithMySQL.addNewRecord_(objectAdd)).thenReturn(addNewRecord(objectAdd));
        serviceMethodReference.save(autoPart);

        String name = autoPart.getName();
        nameFind    = name;
        Mockito.when(workWithMySQL.getDataFromTable(clsTypeResource)).thenReturn((List)products);
        Mockito.when(workWithMySQL.getDataFromTable(Prices.class)).thenReturn((List)new ArrayList<Prices>());
        AutoParts autoPartFind = serviceMethodReference.getReferenceObjectByName(name, AutoParts.class);

        assertNotNull("Method GetReferenceObjectByName failed",autoPartFind);

    }


    public static Integer addNewRecord(Object object){

        int result = 0;

        if (object instanceof AutoParts) {
            products.add((AutoParts) object);
            result = products.size();
        }

        return result;

    }

    public static AutoParts getAutoPartsByCatalogNumber(Object[] filter){

        for (AutoParts curAutoPart : products) {
            if (curAutoPart.getCatalogNumber().equals(filter[0])){
                return curAutoPart;
            }
        }

        return null;
    }

//    public static AutoParts getByIdAutoParts(int id){
//
//        for (AutoParts curAutoPart : products) {
//            if (curAutoPart.getId() == id) {
//                return curAutoPart;
//            }
//        }
//
//        return null;
//    }

}
