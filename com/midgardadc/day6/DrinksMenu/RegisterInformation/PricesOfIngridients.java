package day6.DrinksMenu.RegisterInformation;

/**
 * Created by stepanyuk on 24.06.2015.
 */
public class PricesOfIngridients implements RegisterInformationMethods {

    private int idIngridient;
    private float price;

    @Override
    public void save() {
        System.out.println("Saving Price of Ingridient");
    }

    @Override
    public PricesOfIngridients[] getDataBy(Object key) {
        return new PricesOfIngridients[0];
    }

    public int getIdIngridient() {
        return idIngridient;
    }

    public void setIdIngridient(int idIngridient) {
        this.idIngridient = idIngridient;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
