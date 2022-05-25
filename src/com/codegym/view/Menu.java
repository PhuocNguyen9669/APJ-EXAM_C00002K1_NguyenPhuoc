package com.codegym.view;

public class Menu {

        public static void run(){
            ProductView productView = new ProductView();
            productView.option();

        }


        public static void exit(){
            System.out.println("\t ..... Bạn vừa thoát chương trình, hẹn gặp lại!");
        }
        public static void mainMenu(){
            System.out.println("===============================================");
            System.out.println("=               Quản lý danh bạ               =");
            System.out.println("===============================================");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc vào file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
        }
}
