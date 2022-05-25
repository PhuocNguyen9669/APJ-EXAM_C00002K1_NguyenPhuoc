package com.codegym.view;

import com.codegym.model.Product;

import java.util.Comparator;

public class SortProductASC {

    public class SortByPriceASC implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            if (o1.getPriceProduct() > o2.getPriceProduct()){
                return -1;
            }
            if (o1.getPriceProduct() == o2.getPriceProduct()){
                return 0;
            }
            return 1;
        }
    }
}
