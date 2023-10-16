package com.example.rentbooks.entity;

public class Books {
    private int IdBooks;
    private int IdCategory;
    private String NameBook;
    private String Author;
    private String ImageBook;
    private String CategoryBooks;
    private int Price;
    private int Total;

    public int getIdBooks() {
        return IdBooks;
    }

    public void setIdBooks(int idBooks) {
        IdBooks = idBooks;
    }

    public int getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(int idCategory) {
        IdCategory = idCategory;
    }

    public String getCategoryBooks() {
        return CategoryBooks;
    }

    public void setCategoryBooks(String categoryBooks) {
        CategoryBooks = categoryBooks;
    }

    public String getNameBook() {
        return NameBook;
    }

    public void setNameBook(String nameBook) {
        NameBook = nameBook;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getImageBook() {
        return ImageBook;
    }

    public void setImageBook(String imageBook) {
        ImageBook = imageBook;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public boolean isCheckEmpty() {
        if (getImageBook().isEmpty() || getNameBook().isEmpty() || getCategoryBooks().isEmpty() || getAuthor().isEmpty() || getPrice() == 0 ||
                getTotal() == 0
        ) {
            return false;
        }

        return true;
    }
}
