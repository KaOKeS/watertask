package com.akvelon.watertask.entity.converter;

import com.akvelon.watertask.dto.LivingBeeingDTO;
import com.akvelon.watertask.entity.LivingBeeing;
import com.akvelon.watertask.entity.LivingBeeingFactoryImpl;
import com.akvelon.watertask.entity.contract.LivingBeeingFactory;
import org.springframework.stereotype.Component;

@Component
public class LivingBeeingConverter {
    LivingBeeingFactory livingBeeingFactory = new LivingBeeingFactoryImpl();

    public LivingBeeing dtoToEntity(LivingBeeingDTO livingBeeingDTO){
        return livingBeeingFactory.createLivingBeeing(livingBeeingDTO.getLivingBeeingType(),livingBeeingDTO.getStomachMaximumVolume(),livingBeeingDTO.getStomachCurrentVolume());
    }

    public LivingBeeingDTO entityToDto(LivingBeeing livingBeeing){
        String type = livingBeeing.getClass().getSimpleName();
        return new LivingBeeingDTO(livingBeeing.getStomachMaximumVolume(),livingBeeing.getStomachCurrentVolume(),type);
    }
}
