package shop;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import shop.database.DB;
import shop.database.WorkWithDerby;
import shop.enums.Category;
import shop.reference.AutoParts;
import shop.reference.Client;

import java.sql.SQLException;
import java.util.List;

@RunWith(JUnit4.class)
public class ShopTests {

    private static Shop shop;

    @BeforeClass
    public static void initClass() throws SQLException {
        shop = new Shop();
        DB dbStorageService = new WorkWithDerby(shop);
        shop.setDb(dbStorageService);
        dbStorageService.inicialisation();

    }

    @Test
    public void testInitAutoParts(){
        List<AutoParts> autoPartsList = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

        Assert.assertTrue(autoPartsList.size() > 0);
    }

    @Test
    public void testInitClients(){
        List<Client> clientsList = (List<Client>) shop.getDb().getDataFromTable(Client.class);

        Assert.assertTrue(clientsList.size() > 0);
    }

    @Test
    public void testCreateAutoPart(){

        AutoParts autoPart = new AutoParts(shop);
        autoPart.setName("Akkumulyator TEST 3");
        autoPart.setCatalogNumber("800 25 03");
        autoPart.setCategoriya(Category.ELECTROOBORUDOVANIE);
        autoPart.save();

        AutoParts saveAutoPart = shop.getServiceMethodReference().getReferenceObjectByName(autoPart.getName(), AutoParts.class);

        Assert.assertEquals(saveAutoPart, autoPart);
    }

    @Test
    public void testCreateClient(){

        Client client = new Client(shop);
        client.setName("FOP Roga i Koputa");
        client.save();

        Client saveClient = shop.getServiceMethodReference().getReferenceObjectByName(client.getName(), Client.class);

        Assert.assertEquals(saveClient, client);
    }

}
