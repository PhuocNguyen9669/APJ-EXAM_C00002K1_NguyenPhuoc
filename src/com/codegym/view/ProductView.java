package com.codegym.view;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.utils.AppUtils;
import com.codegym.utils.CSVUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    public static String path = "data/products.csv";
    private static ProductService productService;
    static Scanner scanner = new Scanner(System.in);
    static Product products = new Product();


    public ProductView() {
        productService = new ProductService();
    }

    private void removeProduct() {
        do {
            try {
                showProduct();
                productService.getItem();
                System.out.println("Nhập Id cần xóa");
                long id = Long.parseLong(scanner.nextLine());
                Product product = productService.getProductByIdd(id);
                if (product == null) {
                    System.out.println("không tìm thấy id cần xóa");
                } else {
                    System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄REMOVE COFIRM❄ ❄ ❄ ❄ ❄ ❄ ❄");
                    System.out.println("\t❄                                          ❄");
                    System.out.println("\t❄              1. Remove                   ❄");
                    System.out.println("\t❄              2. Back                     ❄");
                    System.out.println("\t❄                                          ❄");
                    System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
                    System.out.print("➲ ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            productService.remove(product);
                            System.out.println("Xóa thành công");
                            showProduct();
                            option();
                            break;
                        case 2:
                            option();
                            break;
                        default:
                            System.out.println("Chọn chức năng không đúng!");
                            option();
                    }
                }
            } catch (Exception E) {
                System.out.println("Incorrect! please try again!");
            }
        } while (true);
    }

    private void showProduct() {
        System.out.println("------------------------------------PHONE BOOK------------------------------------");
        System.out.printf("%-15s %-20s %-20s %-10s %-25s\n", "  ID", "TÊN SẢN PHẨM", "GIÁ", "SỐ LƯỢNG", "MÔ TẢ");
        List<Product> products = productService.getItem();
        for (Product product : products) {
            System.out.printf("%-15s %-20s %-20s %-10s %-25s \n", product.getProductId(), product.getNameProduct(), AppUtils.doubleToVND(product.getPriceProduct()), product.getQuantityProduct(), product.getDescription());

        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    private void addProduct() {
        boolean is = false;
        do {
            try {
                showProduct();
                System.out.println("Nhập id: ");
                System.out.print(" => ");
                long id = Long.parseLong(scanner.nextLine());
                if (productService.getProductById(id)) {
                    System.out.println("Id đã tồn tại!");
                } else {
                    System.out.println("Nhập tên sản phẩm: ");
                    System.out.print(" => ");
                    String nameProdcut = scanner.nextLine();
                    check(nameProdcut);
                    double price;
                    System.out.println("Nhập giá sản phẩm: ");
                    System.out.print(" => ");
                    price = Double.parseDouble(scanner.nextLine());
                    checkDouble(price);
                    int quantity;
                    System.out.println("Nhập số lượng: ");
                    System.out.print(" => ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    checkInterger(quantity);
                    System.out.println("Nhập mô tả: ");
                    System.out.print(" => ");
                    String desrcibe = scanner.nextLine();
                    check(desrcibe);
                    Product product = new Product(id, nameProdcut, price, quantity, desrcibe);
                    productService.add(product);
                    System.out.println("Đã thêm danh bạ thành công!");
                    showProduct();
                    is = true;
                }
            } catch (Exception e) {
                System.out.println("Bạn nhập không đúng! Vui lòng nhập lại.");
            }
        } while (!is);
    }
    private void updateProduct() {
        List<Product> productList = productService.getItem();
        showProduct();
        do {
            try {
                System.out.println("Nhập Id bạn muốn thay đổi thông tin");
                long id = Long.parseLong(scanner.nextLine());
                checkLong(id);
                if (!productService.getProductById(id)) {
                    System.out.println("ID này không có trong danh sách");
                } else {
                    for (Product product : productList) {
                        System.out.println("Cập nhật tên sản phẩm");
                        String name = scanner.nextLine();
                        check(name);
                        System.out.println("Cập nhật giá sản phẩm");
                        double price = Double.parseDouble(scanner.nextLine());
                        checkDouble(price);
                        System.out.println("Cập nhật số lượng sản phẩm");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        checkInterger(quantity);
                        System.out.println("Cập nhật description");
                        String description = scanner.nextLine();
                        check(description);
                        product.setNameProduct(name);
                        product.setPriceProduct(price);
                        product.setQuantityProduct(quantity);
                        product.setDescription(description);
                        CSVUtils.write(path, productList);
                        System.out.println("Đã cập nhật  thành công!");
                        showProduct();
                        option();
                    }
                }
            } catch (Exception e) {
                System.out.println("Incorrect! please try again!!");
            }
        } while (true);
    }
    private void searchByItemMax() {
        List<Product> productList = productService.getItem();
        int count = 0;
        showProduct();
        System.out.println();
        System.out.println("Nhập tên sản phẩm tìm kiếm");
        String search = scanner.nextLine().toLowerCase();
        System.out.println("Kết quả tìm kiếm từ khóa: " + search + " là: ");
        System.out.println("-----------------------------Product-------------------------------");
        System.out.printf("%-15s %-15s %-20s %-10s %-25s %-20s %-25s \n", "Number Phone", "Team", "FullName", "Genner", "Address", "Birtdate", "Email");
        System.out.printf("%-10s%-20s%-20s%-20s%-15s\n", "Id", "Name Product", "Quantity Product", "Price Product", "Description");
        for (Product product : productList) {
            if (product.toString().toLowerCase().contains(search)) {
                count++;
                System.out.printf("%-10d%-20s%-20d%-20s%-15s\n", product.getProductId(), product.getNameProduct(), product.getQuantityProduct(),
                        product.getPriceProduct(), product.getDescription());
            }
        }
        showReturnSearch(count);
        System.out.println();
        option();
    }

    private void showReturnSearch(int count) {
        System.out.println("có " + count + " Sản phẩm được tìm thấy!");
        char press = ' ';
        boolean isChoice;
        System.out.println();
        do {
            System.out.print("Nhấn 'R' để về menu tìm kiếm !");
            try {
                press = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                press = ' ';
            }
            switch (press) {
                case 'r':
                case 'R': {
                    option();
                    isChoice = false;
                    break;
                }
                default:
                    isChoice = true;
            }
        } while (isChoice);
    }
    public class PriceSortDES implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            if (o1.getPriceProduct() > o2.getPriceProduct()) {
                return -1;
            } else if (o1.getPriceProduct() == o2.getPriceProduct()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    private void sortBypPrice() {
        List<Product> productList = productService.getItem();
        do {
            try {
                System.out.println("1. Gia tien san pham theo thu tu tang dan\n" +
                        "2. gia tien san pham theo thu tu giam dan");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (productList.isEmpty()) {
                            System.out.println("Khong co san pham trong list");
                        } else {
                            PriceSortDES priceSortASC = new PriceSortDES();
                            System.out.println("-----------------------------------------ProductList-----------------------------------------");
                            System.out.printf("%-10s%-20s%-20s%-20s%-15s\n", "Id", "Name Product", "Quantity Product", "Price Product", "Description");
                            for (Product product : productList) {
                                System.out.printf("%-10d%-20s%-20d%-20s%-15s\n", product.getProductId(), product.getNameProduct(), product.getQuantityProduct(),
                                        product.getPriceProduct(), product.getDescription());
                            }
                        }
                        option();
                        break;
                    case 2:
                        if (productList.isEmpty()) {
                            System.out.println("Khong co san pham trong list");
                        } else {
                            PriceSortDES priceSortDES = new PriceSortDES();
                            Collections.sort(productList, priceSortDES);
                            System.out.println("-----------------------------------------ProductList-----------------------------------------");
                            System.out.printf("%-10s%-20s%-20s%-20s%-15s\n", "Id", "Name Product", "Quantity Product", "Price Product", "Description");
                            for (Product product : productList) {
                                System.out.printf("%-10d%-20s%-20d%-20s%-15s\n", product.getProductId(), product.getNameProduct(), product.getQuantityProduct(),
                                        product.getPriceProduct(), product.getDescription());
                            }
                        }
                        option();
                        break;
                    default:
                        System.out.println("nhập không đúng vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Incorrect! please try again");
            }
        } while (true);
    }

    public String check(String str) {
        try {
            while (str.isEmpty()) {
                System.out.println("Không được để trống!");
                System.out.print(" => ");
                str = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Nhập sai! Vui lòng nhập lại.");
        }
        return str;
    }

    public Double checkDouble(double dou) {
        try {
            while (dou < 0) {
                System.out.println("nhập sai vui lòng nhập lại");
                dou = Double.parseDouble(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("nhập sai vui lòng nhập lại");
        }
        return dou;
    }

    public Integer checkInterger(int intt) {
        try {
            while (intt < 0) {
                System.out.println("nhập sai vui lòng nhập lại");
                intt = Integer.parseInt(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("nhập sai vui lòng nhập lại");
        }
        return intt;
    }


    public Long checkLong(long longg) {
        try {
            while (longg < 0) {
                System.out.println("nhập sai vui lòng nhập lại");
                longg = Long.parseLong(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Incorrect! please try again");
        }
        return longg;
    }

    public void option() {

        do {
            try {
                Menu.mainMenu();
                System.out.print("Nhập Chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        showProduct();
                        break;
                    case 2:
                        addProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        removeProduct();
                        break;
                    case 5:
//                        searchByName();
                        break;
                    case 6:
                        CSVUtils.read(path);
                        break;
                    case 7:
                        CSVUtils.write(path, productService.getItem());
                        break;
                    case 8:
                        Menu.exit();
                        System.exit(0);
                    default:
                        System.out.println("\t Không có chức năng này! mới bạn nhập");
                }
            } catch (Exception e) {
                System.out.println("\t chức năng phải là 1 số!");
                Menu.mainMenu();
            }

        } while (true);
    }
}

