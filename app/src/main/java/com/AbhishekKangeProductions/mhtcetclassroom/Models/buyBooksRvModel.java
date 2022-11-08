package com.AbhishekKangeProductions.mhtcetclassroom.Models;

public class buyBooksRvModel {


    String bookImage;
    String bookName;
    String description;
    String bookUrl;
    String bookPdfUrl;
    String price;

    public buyBooksRvModel() {

    }

    public buyBooksRvModel(String bookImage, String bookName, String description, String bookUrl, String bookPdfUrl, String price) {
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.description = description;
        this.bookUrl = bookUrl;
        this.bookPdfUrl = bookPdfUrl;
        this.price = price;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getBookPdfUrl() {
        return bookPdfUrl;
    }

    public void setBookPdfUrl(String bookPdfUrl) {
        this.bookPdfUrl = bookPdfUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
