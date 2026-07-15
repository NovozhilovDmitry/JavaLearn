package json.folders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class FolderTreeParser {
    private final ObjectMapper mapper = new ObjectMapper();
    private final FolderTreeExtractor extractor = new FolderTreeExtractor();

    public List<FolderTreeExtractor.Result> parse(String path) throws Exception {
        JsonNode root = mapper.readTree(new File(path));
        return extractor.extract(root);
    }
}