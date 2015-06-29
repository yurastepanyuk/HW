package day6.DrinksMenu.Reference;


public abstract class Reference implements ReferenceMethods {

    private int id;
    private String name;

    @Override
    public void save(Reference r) {
        if (r instanceof ReferenceDrinks){
            System.out.println("Saving ");
        }else if (r instanceof ReferenceIngridients){
            System.out.println("Saving ");
        }
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
}
