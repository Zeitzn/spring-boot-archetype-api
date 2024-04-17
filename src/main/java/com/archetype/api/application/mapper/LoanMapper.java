package com.archetype.api.application.mapper;

import com.archetype.api.application.dto.response.LoanResponse;
import com.archetype.api.domain.entity.LoanEntity;
import com.archetype.api.util.DateUtil;
import com.archetype.api.util.constant.FormatDate;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LoanMapper {
    private LoanMapper() {}
    private static final ModelMapper modelMapper = new ModelMapper();
    public static List<LoanResponse> toListDto(List<LoanEntity> data) {
        if(data == null) return Collections.emptyList();
        return data.stream().map(LoanMapper::toDto).toList();
    }
    public static LoanResponse toDto(LoanEntity data) {
        configureSpecificFields();
        return modelMapper.map(data, LoanResponse.class);
    }

    private static void configureSpecificFields() {
        modelMapper.typeMap(LoanEntity.class, LoanResponse.class)
                .addMappings(mapper -> {
                    mapper.using(dateConverter).map(LoanEntity::getAdmissionDate, LoanResponse::setAdmissionDate);
                });
    }

    private static final Converter<Date, String> dateConverter = context -> {
        if (context.getSource() == null) {
            return null;
        }
        return DateUtil.format(context.getSource(),FormatDate.DD_MM_YYYY_1);
    };
}
