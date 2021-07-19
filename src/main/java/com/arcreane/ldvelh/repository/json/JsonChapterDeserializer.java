package com.arcreane.ldvelh.repository.json;

import com.arcreane.ldvelh.model.Chapter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JsonChapterDeserializer extends JsonDeserializer<Chapter> {
    @Override
    public Chapter deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Chapter chapter = new Chapter();

        var objectCodec = jsonParser.getCodec();
        JsonNode treeNode = objectCodec.readTree(jsonParser);

        chapter.setId( treeNode.get("id").asInt());
        chapter.setText( treeNode.get("text").asText());
        chapter.setCaption( treeNode.get("caption").asText());
        JsonNode indexes = treeNode.get("options");
        for (JsonNode index :indexes ) {
            chapter.addOption(index.asInt());
        }

        chapter.setIntro( treeNode.get("intro").asBoolean());
        chapter.setEnd( treeNode.get("end").asBoolean());
        return chapter;
    }
}