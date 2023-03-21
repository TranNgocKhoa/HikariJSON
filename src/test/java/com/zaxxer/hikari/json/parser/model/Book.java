package com.zaxxer.hikari.json.parser.model;

public class Book
{
   protected String title;
   protected String isbn;
   protected String publisher;
   protected String asin;
   protected String author;

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getIsbn()
   {
      return isbn;
   }

   public void setIsbn(String isbn)
   {
      this.isbn = isbn;
   }

   public String getPublisher()
   {
      return publisher;
   }

   public void setPublisher(String publisher)
   {
      this.publisher = publisher;
   }

   public String getAsin()
   {
      return asin;
   }

   public void setAsin(String asin)
   {
      this.asin = asin;
   }

   public String getAuthor()
   {
      return author;
   }

   public void setAuthor(String author)
   {
      this.author = author;
   }
}
