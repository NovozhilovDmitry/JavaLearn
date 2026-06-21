package json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParsing {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File("/data/p1.json"));
        JsonNode productsNode = rootNode.path("products");

        for (JsonNode product : productsNode) {
            String name = product.path("name").asText();
            System.out.println("=== ТОВАР: " + name);
        }

    }
}