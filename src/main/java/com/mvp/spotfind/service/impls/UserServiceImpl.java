package com.mvp.spotfind.service.impls;

import com.mvp.spotfind.Exceptionpack.UserNotFoundException;
import com.mvp.spotfind.dto.UserDto;
import com.mvp.spotfind.entity.User;
import com.mvp.spotfind.mapper.UserMapper;
import com.mvp.spotfind.repository.UserRepository;
import com.mvp.spotfind.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){this.userRepository =userRepository;}

    @Override
    public UserDto createUser(UserDto dto) {
        User newUser = UserMapper.toEntity(dto);
        User user = userRepository.save(newUser);
        return UserMapper.toDto(user);
    }

    @Override
    public Long login(String mobileNumber,String password) {
        User user = userRepository.findByMobileNumberAndPassword(mobileNumber, password)
                .orElseThrow(() -> new UserNotFoundException("Parking not found with mobile number: " + mobileNumber));
        return user.getId();
    }

    @Override
    public UserDto updateUser( Long id ,UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found with id: " + id));
        for(Field field: UserDto.class.getDeclaredFields()){
            field.setAccessible(true);

            try{
                Object value = field.get(dto);
                if(value!=null){
                    String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method setter = User.class.getMethod(setterName, field.getType());
                    setter.invoke(user , value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }
}