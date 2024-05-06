package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    //enpoint to add a property
    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        propertyDTO = propertyService.saveProperty(propertyDTO);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }
    //Endpoint to get all the properties
    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){

        List<PropertyDTO> propertyList= propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList,HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/properties/users/{userId}")
    public ResponseEntity<List<PropertyDTO>> getAllPropertiesforUser(@PathVariable("userId") Long userId){

        List<PropertyDTO> propertyList= propertyService.getAllPropertiesforUser(userId);
        ResponseEntity<List<PropertyDTO>> responseEntity = new ResponseEntity<>(propertyList,HttpStatus.OK);
        return responseEntity;
    }
    //Endpoint to update a property
    @PutMapping("/properties/{property_id}")
    public  ResponseEntity<PropertyDTO> updateproperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long property_id){

        propertyDTO = propertyService.updateProperty(propertyDTO, property_id);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    //Endpoint to update Description
    @PatchMapping("/properties/update-description/{property_id}")
    public  ResponseEntity<PropertyDTO> updatepropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long property_id){

        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, property_id);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }
    //Endpoint to update Price
    @PatchMapping("/properties/update-price/{property_id}")
    public  ResponseEntity<PropertyDTO> updatepropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long property_id){

        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, property_id);
        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    //Delete a Property
    @DeleteMapping("/properties/{property_id}")
    public  ResponseEntity<Void> deleteProperty(@PathVariable Long property_id){

        propertyService.deleteProperty(property_id);
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        return responseEntity;
    }




}
