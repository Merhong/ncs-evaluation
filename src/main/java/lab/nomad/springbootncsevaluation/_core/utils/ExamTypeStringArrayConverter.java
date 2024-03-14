package lab.nomad.springbootncsevaluation._core.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lab.nomad.springbootncsevaluation.model.ability_units.enums.ExamType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ExamTypeStringArrayConverter implements AttributeConverter<List<ExamType>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<ExamType> attribute) {
        return attribute.stream().map(String::valueOf).collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<ExamType> convertToEntityAttribute(String dbData) {
        if(dbData == null) { // JPA save는 select부터 하기 때문에, null을 체크해줘야 한다.
            return Collections.emptyList();
        } else {
            return Arrays.stream(dbData.split(SPLIT_CHAR))
                    .map(ExamType::valueOf)
                    .collect(Collectors.toList());
        }
    }
}
