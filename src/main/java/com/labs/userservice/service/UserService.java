package com.labs.userservice.service;

import com.labs.userservice.entity.PgUser;
import com.labs.userservice.model.ChangeUserDto;
import com.labs.userservice.model.UserDto;
import com.labs.userservice.model.converter.UserDtoConverter;
import com.labs.userservice.repository.PgUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PgUserRepository pgUserRepository;
    private final UserServiceFeignClients userServiceFeignClients;

    @Transactional
    public String createUser(UserDto userDto) {
        Boolean exist = userServiceFeignClients.existsById(userDto.getCompanyId());
        if(!exist) {
            throw new EntityNotFoundException("Компания с id = %s не существует".formatted(userDto.getCompanyId()));
        }

        return pgUserRepository.save(UserDtoConverter.toEntity(userDto)).getId();
    }

    @Transactional
    public Boolean existsById(String id) {
        PgUser pgUser = pgUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id: " + id + " - не существует"));

        if (!pgUser.getEnabled())
            throw new EntityNotFoundException("Пользователь с id: " + id + " - не активен");

        return true;
    }

    @Transactional
    public List<UserDto> getAllUsers() {
        List<PgUser> pgUsers = pgUserRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (PgUser entity : pgUsers) {
            userDtos.add(UserDtoConverter.toDto(entity));
        }
        return userDtos;
    }

    @Transactional
    public String changeEnabled(String id) {
        PgUser pgUser = pgUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id: " + id + " - не существует"));

        UserDto userDto = UserDtoConverter.toDto(pgUser);
        if (userDto.getEnabled()) {
            userDto.setEnabled(false);
            pgUserRepository.save(UserDtoConverter.toEntity(userDto));
            return "disabled";
        } else {
            userDto.setEnabled(true);
            pgUserRepository.save(UserDtoConverter.toEntity(userDto));
            return "enabled";
        }
    }

    @Transactional
    public String changeUser(String id, ChangeUserDto changeUserDto) {
        PgUser pgUser = pgUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id: " + id + " - не существует"));

        UserDto userDto = UserDtoConverter.toDto(pgUser);
        if (!changeUserDto.getName().equals("")) {
            userDto.setName(changeUserDto.getName());
        }
        if (!changeUserDto.getEmail().equals("")) {
            userDto.setEmail(changeUserDto.getEmail());
        }
        if (!changeUserDto.getCompanyId().equals("")) {
            if(!userServiceFeignClients.existsById(changeUserDto.getCompanyId()))
                throw new EntityNotFoundException("Компания с id = %s не существует".formatted(userDto.getCompanyId()));
            userDto.setCompanyId(changeUserDto.getCompanyId());
        }
        pgUserRepository.save(UserDtoConverter.toEntity(userDto));
        return "updated";
    }
}
