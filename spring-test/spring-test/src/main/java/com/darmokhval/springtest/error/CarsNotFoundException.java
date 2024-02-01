package com.darmokhval.springtest.error;

import com.darmokhval.springtest.dto.SearchCriteriaDTO;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CarsNotFoundException extends RuntimeException{
    public CarsNotFoundException() {
        super("No cars has been found");
    }
    public CarsNotFoundException(String searchVariable) {
        super(String.format("No cars by '%s' has been found", searchVariable));
    }
    public CarsNotFoundException(Integer searchVariable) {
        super(String.format("No cars by '%d' has been found", searchVariable));
    }
//    public CarsNotFoundException(String ...searchVariable) {
//        super(String.format("No cars by '%s' has been found", String.join(", ", searchVariable)));
//    }
    public CarsNotFoundException(SearchCriteriaDTO searchCriteriaDTO) {
        super(buildSearchCriteriaDTOError(searchCriteriaDTO));
    }

    private static String buildSearchCriteriaDTOError(SearchCriteriaDTO searchCriteriaDTO) {
        List<String> fieldsAndValues = new ArrayList<>();
        Field[] fields = searchCriteriaDTO.getClass().getDeclaredFields();
        for(Field field: fields) {
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                Object value = field.get(searchCriteriaDTO);
                fieldsAndValues.add(String.format("%s='%s'", fieldName, value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return String.format("No cars by %s has been found", String.join(", ", fieldsAndValues));
    }
}
