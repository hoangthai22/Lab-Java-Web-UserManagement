/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.dtos;

import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class PromotionObj {

    private HashMap<String, Float> promotionList;

    public PromotionObj() {
    }

    public PromotionObj(HashMap<String, Float> promotionList) {
        this.promotionList = promotionList;
    }

    public HashMap<String, Float> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(HashMap<String, Float> promotionList) {
        this.promotionList = promotionList;
    }

    public void AddToPromotionList(String userId) {
        if (promotionList == null) {
            this.promotionList = new HashMap<String, Float>();
        }
        float rank = 5;
        this.promotionList.put(userId, rank);
    }

    public boolean removeDeviceInCart(String userId) {
        if (this.promotionList.containsKey(userId)) {
            this.promotionList.remove(userId);
            return true;
        }
        return false;
    }

    public boolean updateCart(String userId, float rank) {
        boolean result = false;

        if (this.promotionList.containsKey(userId)) {
            this.promotionList.replace(userId, rank);
            result = true;
        }
        return result;
    }
}
