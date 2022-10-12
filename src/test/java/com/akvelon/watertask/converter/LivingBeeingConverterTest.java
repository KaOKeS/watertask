package com.akvelon.watertask.converter;

import com.akvelon.watertask.dto.LivingBeeingDTO;
import com.akvelon.watertask.entity.Human;
import com.akvelon.watertask.entity.LivingBeeing;
import com.akvelon.watertask.entity.converter.LivingBeeingConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class LivingBeeingConverterTest {
    LivingBeeingConverter livingBeeingConverter = new LivingBeeingConverter();

    @Test
    void checkConversionOfLivingBeeingEntityToDto(){
        LivingBeeing livingBeeing = new Human(2,0);
        LivingBeeingDTO livingBeeingDTO = livingBeeingConverter.entityToDto(livingBeeing);
        Assertions.assertEquals(livingBeeing.getStomachCurrentVolume(),livingBeeingDTO.getStomachCurrentVolume());
        Assertions.assertEquals(livingBeeing.getStomachMaximumVolume(),livingBeeingDTO.getStomachMaximumVolume());
        Assertions.assertEquals(livingBeeingDTO.getLivingBeeingType().toLowerCase(),livingBeeing.getClass().getSimpleName().toLowerCase());
    }

    @Test
    void checkConversionOfLivingBeeingDtoToEntity(){
        LivingBeeingDTO livingBeeingDTO = new LivingBeeingDTO(2,1,"human");
        LivingBeeing livingBeeing = livingBeeingConverter.dtoToEntity(livingBeeingDTO);
        Assertions.assertEquals(livingBeeing.getStomachCurrentVolume(),livingBeeingDTO.getStomachCurrentVolume());
        Assertions.assertEquals(livingBeeing.getStomachMaximumVolume(),livingBeeingDTO.getStomachMaximumVolume());
        Assertions.assertEquals(livingBeeingDTO.getLivingBeeingType().toLowerCase(),livingBeeing.getClass().getSimpleName().toLowerCase());
    }
}
