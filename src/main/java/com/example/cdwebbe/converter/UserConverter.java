package com.example.cdwebbe.converter;

import com.example.cdwebbe.DTO.UserDTO;
import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.model.UserInfo;
import com.example.cdwebbe.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    public User toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO toDTO(User userEntity){
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserInfo toInfo(User userEntity){
        return  modelMapper.map(userEntity, UserInfo.class);
    }

    public List<UserDTO> toDTO(List<User> userEntityList){
        List<UserDTO> userDTOList = new ArrayList<>();
        UserDTO userDTO;
        for (User userEntity: userEntityList){
            userDTO = modelMapper.map(userEntity, UserDTO.class);
            userDTO.setAmountOrder(userEntity.getOrder().size());
            userDTO.setAmountSpent(amountSpent(userEntity));
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public double amountSpent(User userEntity) {
        double spent =0;
        for(Order order: userEntity.getOrder()){
            spent = (double) (order.getTotalPriceOrder() + spent);
        }
        return spent;
    }
}
