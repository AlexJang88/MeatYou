package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class SelectedProductDTO {
	private String add_m_id;
    private int shop_num;
    private String p_name;
    private String pd_p_desc;
    private String thumb;
    private int shop_quantity;
    private int p_price;
    private String p_m_id;

    // 각 필드에 대한 Getter와 Setter 메서드

    public int getShop_num() {
        return shop_num;
    }

    public void setShop_num(int shop_num) {
        this.shop_num = shop_num;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    // 나머지 필드에 대한 Getter와 Setter 메서드도 유사하게 작성
    // ...

    @Override
    public String toString() {
        return "SelectedProductDTO{" +
                "shop_num=" + shop_num +
                ", p_name='" + p_name + '\'' +
                // 나머지 필드에 대한 문자열 표현도 추가
                '}';
    }
}