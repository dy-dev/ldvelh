package com.arcreane.ldvelh.repository.json;

import com.arcreane.ldvelh.model.Chapter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonChapterSerializer extends JsonSerializer<Chapter> {
    @Override
    public void serialize(Chapter chapter, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", chapter.getId());
        jsonGenerator.writeStringField("caption", chapter.getCaption());
        jsonGenerator.writeStringField("text", chapter.getText());
        int[] indexes = chapter.getIndexes();
        jsonGenerator.writeFieldName("options");
        jsonGenerator.writeArray(indexes, 0, indexes.length);

        jsonGenerator.writeBooleanField("intro", chapter.isIntro());
        jsonGenerator.writeBooleanField("end", chapter.isEnd());
        jsonGenerator.writeEndObject();

    }
}
