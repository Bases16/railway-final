package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.config.JtaDatabaseConfig;
import edu.arf4.trains.railwayfinal.dao.PassengerDao;
import edu.arf4.trains.railwayfinal.dto.PassengerDto;
import edu.arf4.trains.railwayfinal.model.Passenger;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(classes = JtaDatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PassengerServiceTest {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private PassengerDao passengerDao;

    @Test
    @Transactional
    public void createPassenger() {

        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setFirstName("Serge");
        passengerDto.setLastName("Ibaka");
        passengerDto.setDateOfBirth("1899-01-01");
        Long newPassengerId = passengerService.createPassenger(passengerDto);

        Passenger passenger = passengerDao.getPassengerById(newPassengerId);
        assertNotNull(passenger);
        assertEquals(passenger.getFirstName(), passengerDto.getFirstName());
        assertEquals(passenger.getLastName(), passengerDto.getLastName());
        assertEquals(passenger.getDateOfBirth(),
                Converter.convertStringToLocalDate(passengerDto.getDateOfBirth())
        );
    }

    @Test
    @Transactional(readOnly = true)
    public void getPassengerDTOsByTrainId() {

        List<PassengerDto> dtoList = passengerService.getPassengerDTOsByTrainId(1L);

        assertNotNull(dtoList);
        assertEquals(6, dtoList.size());

    }














}