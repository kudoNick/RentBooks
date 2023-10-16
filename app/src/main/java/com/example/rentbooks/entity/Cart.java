package com.example.rentbooks.entity;

public class Cart extends Books {
    private int idCart;
    private int idUser;
    private int soLuong;
    private int checkBuy;
    private int priceFinal;

    public int getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(int priceFinal) {
        this.priceFinal = priceFinal;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getCheckBuy() {
        return checkBuy;
    }

    public void setCheckBuy(int checkBuy) {
        this.checkBuy = checkBuy;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", idUser=" + idUser +
                ", soLuong=" + soLuong +
                ", checkBuy=" + checkBuy +
                ", priceFinal=" + priceFinal +
                '}';
    }
}
