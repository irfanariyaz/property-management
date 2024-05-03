package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOToEntity(propertyDTO);
        pe = propertyRepository.save(pe);
        propertyDTO = propertyConverter.convertEntityToDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyEntities = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for(PropertyEntity pe:propertyEntities){
            PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(pe);
            propList.add(propertyDTO);
        }
        return propList;

    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        if(propertyEntity.isPresent()){
            PropertyEntity pe = propertyEntity.get();
            pe.setAddress(propertyDTO.getAddress());
            pe.setDescription(propertyDTO.getDescription());
            pe.setTitle(propertyDTO.getTitle());
            pe.setPrice(propertyDTO.getPrice());
            pe = propertyRepository.save(pe);
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }
        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        if(propertyEntity.isPresent()){
            PropertyEntity pe = propertyEntity.get();
            pe.setDescription(propertyDTO.getDescription());
            pe = propertyRepository.save(pe);
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }
        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        if(propertyEntity.isPresent()){
            PropertyEntity pe = propertyEntity.get();
            pe.setPrice(propertyDTO.getPrice());
            pe = propertyRepository.save(pe);
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }
        return propertyDTO;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        System.out.println("Inside the delete statement");
       propertyRepository.deleteById(propertyId);


    }
}
