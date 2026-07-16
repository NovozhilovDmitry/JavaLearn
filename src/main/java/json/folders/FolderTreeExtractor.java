package json.folders;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

public class FolderTreeExtractor {
    public List<Result> extract(JsonNode root) {
        List<Result> result = new ArrayList<>();
        walk(root, result);
        return result;
    }

    private void walk(JsonNode node, List<Result> result) {
        if (node == null) return;
        result.add(new Result(
                node.path("id").asLong(),
                node.path("name").asText()
        ));
        JsonNode children = node.get("children");
        if (children != null && children.isArray()) {
            for (JsonNode child : children) {
                walk(child, result);
            }
        }
    }

    public static class Result {
        public long id;
        public String name;
        public Result(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}