package json.folders;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class FolderTreeParser {
    private final ObjectMapper mapper = new ObjectMapper();
    private final FolderTreeExtractor extractor = new FolderTreeExtractor();

    public List<FolderTreeExtractor.Result> parse(String json) throws Exception {
        return extractor.extract(mapper.readTree(json));
    }
}