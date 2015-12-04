package shop.reference;

import shop.Shop;
import shop.inforamation.Prices;

import java.util.List;

public class ServiceMethodReference implements ServiceMethodRef {

    private Shop shop;

    public ServiceMethodReference(Shop shop) {
        this.shop = shop;
    }

    public void getResourses(Reference object){

        if (object instanceof AutoParts){

            ((AutoParts) object).setPrices(new Prices(shop).getPricesByGoods((AutoParts) object));

        }else if (object instanceof Client){

        }

    }

    public <T> T getReferenceObjectById(int id, Class<T> cls) {

        if (cls.getSimpleName().equals("AutoParts")){

            List<AutoParts> autoParts = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

            for (AutoParts element : autoParts) {
                if (element.getId() == id) {
                    //element.readResources();
                    getResourses(element);
                    return (T) element;
                }
            }

        } else if (cls.getSimpleName().equals("Client")) {

            List<Client> clients = (List<Client>) shop.getDb().getDataFromTable(Client.class);

            for (Client element : clients) {
                if (element.getId() == id) {
                    return (T) element;
                }
            }

        }

        return null;
    }

    public <T> T getReferenceObjectByName(String name, Class<T> cls){

        if (cls.getSimpleName().equals("AutoParts")) {

            List<AutoParts> autoParts = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

            for (AutoParts element : autoParts) {
                if (element.getName().indexOf(name) >= 0) {
                    //element.readResources();
                    this.getResourses(element);
                    return (T)element;
                }
            }

        } else if (cls.getSimpleName().equals("Client")) {
            List<Client> clients = (List<Client>) shop.getDb().getDataFromTable(Client.class);

            for (Client element : clients) {
                if (element.getName().indexOf(name) >= 0) {
                    return (T)element;
                }
            }

        }

        return null;
    }

    @Override
    public void save(Reference object) {

        if ((object instanceof AutoParts) || (object instanceof Client)) {

            if (object.getId() == 0) {
                if (shop.getDb().getClass().getSimpleName().equals("WorkWithLists")) {
                    int newId = shop.getDb().getNewId(object);
                    object.setId(newId);
                }
                shop.getDb().addNewRecord(object);
            } else {
                shop.getDb().updateRecord(object);
            }

        }


    }

}
