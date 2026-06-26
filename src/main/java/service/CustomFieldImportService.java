package service;

import json.getterFields.CustomField;
import json.getterFields.Option;
import repository.CustomFieldRepository;
import repository.OptionRepository;
import java.sql.Connection;
import java.util.List;

public class CustomFieldImportService {
    private final CustomFieldRepository fieldRepository = new CustomFieldRepository();
    private final OptionRepository optionRepository = new OptionRepository();

    public void importFields(Connection connection, List<CustomField> fields) throws Exception {
        for(CustomField field : fields){
            fieldRepository.save(connection, field);
            List<Option> options = field.getOptions();
            if(options != null){
                for(Option option : options){
                    optionRepository.save(connection, field.getId(), option);
                }
            }
        }
    }
}