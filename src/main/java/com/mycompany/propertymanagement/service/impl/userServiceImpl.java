package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.Address;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.AddressRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> opuserEntity = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (opuserEntity.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXIST");
            errorModel.setMessage("The Email which you are trying to register already exist");
            errorModelList.add(errorModel);
            throw  new BusinessException(errorModelList);

        }


        UserEntity userEntity =userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        Address address = new Address();
        address.setHouseNo(userDTO.getHouseNo());
        address.setCity(userDTO.getCity());
        address.setCountry(userDTO.getCountry());
        address.setPostalCode(userDTO.getPostalCode());
        address.setStreet(userDTO.getStreet());
        address.setUserEntity(userEntity);
        addressRepository.save(address);

        userDTO = userConverter.convertEntitytoDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email,password);
        if (optionalUserEntity.isPresent()){
            userDTO = userConverter.convertEntitytoDTO(optionalUserEntity.get());
        }else{
            List<ErrorModel> list = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect email or Password");
            list.add(errorModel);
            throw new BusinessException(list);
        }
        return userDTO;
    }
}
