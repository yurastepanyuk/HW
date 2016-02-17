package shop.documents;

import shop.reference.AutoParts;
import shop.reference.Client;

public interface MethodsDocument {

    public void save();

    public <T>T getObjectById(int id);

    public Shopping newShopping(Client client, AutoParts autoParts, int qty, float cena);

    public Sale newSale(Client client, AutoParts autoParts, int qty, float cena, boolean save);
}
