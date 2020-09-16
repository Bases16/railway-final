package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.PassengerDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.PassengerDto;
import edu.arf4.trains.railwayfinal.model.Passenger;
import edu.arf4.trains.railwayfinal.model.Ticket;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PassengerService {

    private final PassengerDao passengerDao;
    private final TrainDao trainDao;


    @Autowired
    public PassengerService(PassengerDao passengerDao, TrainDao trainDao) {
        System.out.println(this.getClass().getSimpleName() + " WAS CREATED");
        this.passengerDao = passengerDao;
        this.trainDao = trainDao;
    }

    @Transactional
    public Long createPassenger(PassengerDto dto) {

        Passenger passenger = new Passenger();
        passenger.setFirstName(dto.getFirstName());
        passenger.setLastName(dto.getLastName());
        passenger.setDateOfBirth(Converter.convertStringToLocalDate(dto.getDateOfBirth()));

        return this.passengerDao.addPassenger(passenger);
    }

    // this propagation 'cause not so frequently and important operations
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<PassengerDto> getPassengerDTOsByTrainId(Long trainId) {

        List<PassengerDto> dtoList = new ArrayList<>();

        Train train = trainDao.getTrainById(trainId);

        Set<Ticket> tickets = train.getTickets();

        for (Ticket ticket : tickets) {
            Passenger passenger = ticket.getPassenger(); // batch here
            PassengerDto dto = new PassengerDto();
            dto.setFirstName(passenger.getFirstName());
            dto.setLastName(passenger.getLastName());
            dto.setDateOfBirth(Converter.convertLocalDateToString(passenger.getDateOfBirth()));
            dtoList.add(dto);
        }
        return dtoList;
    }




}
