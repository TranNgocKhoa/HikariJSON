package com.zaxxer.hikari.json.parser;

import com.zaxxer.hikari.json.JsonFactory;
import com.zaxxer.hikari.json.ObjectMapper;
import com.zaxxer.hikari.json.parser.model.Book;
import com.zaxxer.hikari.json.parser.model.BookPrimitiveId;
import com.zaxxer.hikari.json.parser.model.BookWrapperId;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleObjectParserTest
{
   private ObjectMapper objectMapper;

   @BeforeEach
   public void setup()
   {
      objectMapper = JsonFactory.create();
   }

   @Test
   @DisplayName("Test simple book serializer")
   void bookSerializerTest() throws IOException
   {
      File file = new File("src/test/resources/test-parser/book-simple.json");
      byte[] bytes = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      Book parsedBook = objectMapper.readValue(byteArrayInputStream, Book.class);
      assertNotNull(parsedBook);
   }

   @Test
   @DisplayName("Test book with primitive ID serializer")
   void primitiveIdBookSerializerTest() throws IOException
   {
      File file = new File("src/test/resources/test-parser/book-simple.json");
      byte[] bytes = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      BookPrimitiveId parsedBook = objectMapper.readValue(byteArrayInputStream, BookPrimitiveId.class);
      assertNotNull(parsedBook);
      assertEquals(1L, parsedBook.getId());
   }

   @Test
   @DisplayName("Test book with wrapper ID serializer")
   void wrapperIdBookSerializerTest() throws IOException
   {
      File file = new File("src/test/resources/test-parser/book-simple.json");
      byte[] bytes = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
      BookWrapperId parsedBook = objectMapper.readValue(byteArrayInputStream, BookWrapperId.class);
      assertNotNull(parsedBook);
      assertEquals(1L, parsedBook.getId());
   }
}
