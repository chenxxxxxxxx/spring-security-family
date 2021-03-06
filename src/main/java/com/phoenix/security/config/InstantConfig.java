package com.phoenix.security.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;

public class InstantConfig {
  public static class InstantDeserializer extends JsonDeserializer<Instant> {
    /**
     * 为了解决jackson反序列化时间对象错误问题
     *
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     */
    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
      String text = jsonParser.getText();
      Long aLong = Long.valueOf(text);
      Instant res = Instant.ofEpochMilli(aLong);
      return res;
    }
  }

  public static class InstantSerializer extends JsonSerializer<Instant> {
    /**
     * 为了解决jackson序列化时间对象错误问题
     *
     * @param instant
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(
        Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
      jsonGenerator.writeNumber(instant.toEpochMilli());
    }
  }
}
