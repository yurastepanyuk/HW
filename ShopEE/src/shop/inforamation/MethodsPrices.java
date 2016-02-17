package shop.inforamation;

import shop.enums.CategoryPrice;
import shop.reference.AutoParts;

public interface MethodsPrices {

    public void addPrice(AutoParts autoParts, float prise, CategoryPrice kategoriya );

    public Prices getRecordByAutoParts(AutoParts autoParts, CategoryPrice kategoriya);

    public float getPriceByGoods(AutoParts autoParts, CategoryPrice kategoriya);

    public Prices[] getPricesByGoods(AutoParts autoParts);

}
