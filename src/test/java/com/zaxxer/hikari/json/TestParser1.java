package com.zaxxer.hikari.json;

import com.zaxxer.hikari.json.JsonFactory.Option;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser1
{
   private ByteArrayInputStream bais;

   @BeforeEach
   public void before() throws IOException
   {
      File file = new File("src/test/resources/menu.json");
      byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));
      bais = new ByteArrayInputStream(bytes);
   }

   @Test
   public void testParser01() throws Exception
   {
      ObjectMapper objectMapper = JsonFactory.create();
      MenuBar menuBar = objectMapper.readValue(bais, MenuBar.class);
      assertEquals("file", menuBar.menu.id);
      assertEquals("File", menuBar.menu.value);
      assertNotNull(menuBar.menu.popup);
      assertNotNull(menuBar.menu.popup.menuitem);
      assertTrue(menuBar.menu.popup.menuitem instanceof HashSet);
      assertSame(3, menuBar.menu.popup.menuitem.size());
   }

   @Test
   public void testParser02() throws Exception
   {
      ObjectMapper objectMapper = JsonFactory.option(Option.FIELD_ACCESS, Option.VALUES_ASCII).option(Option.COLLECTION_CLASS, LinkedList.class).create();

      MenuBar2 menuBar = objectMapper.readValue(bais, MenuBar2.class);
      assertEquals("file", menuBar.menu.id);
      assertEquals("File", menuBar.menu.value);
      assertNotNull(menuBar.menu.popup);
      assertNotNull(menuBar.menu.popup.menuitem);
      assertTrue(menuBar.menu.popup.menuitem instanceof LinkedList);
      assertSame(3, menuBar.menu.popup.menuitem.size());
   }

   @Test
   public void testParser03() throws Exception
   {
      ObjectMapper objectMapper = JsonFactory.option(Option.FIELD_ACCESS, Option.VALUES_ASCII).create();

      MenuBar2 menuBar = objectMapper.readValue(bais, MenuBar2.class);
      assertEquals("file", menuBar.menu.id);
      assertEquals("File", menuBar.menu.value);
      assertNotNull(menuBar.menu.popup);
      assertNotNull(menuBar.menu.popup.menuitem);
      assertTrue(menuBar.menu.popup.menuitem instanceof ArrayList);
      assertSame(3, menuBar.menu.popup.menuitem.size());
   }

   @Test
   public void testParser04() throws Exception
   {
      ObjectMapper objectMapper = JsonFactory.create();

      File file = new File("src/test/resources/AllTypes.json");
      try (InputStream is = new FileInputStream(file)) {
         AllType allType = objectMapper.readValue(is, AllType.class);
         assertEquals(1.2, allType.myDouble);
      }
   }

   public void loadTest() throws Exception
   {
      for (int i = 0; i < 10_000_000; i++) {
         ObjectMapper objectMapper = JsonFactory.create();
         MenuBar2 menuBar = objectMapper.readValue(bais, MenuBar2.class);
         assertEquals("file", menuBar.menu.id);
         bais.reset();
      }
   }
}
