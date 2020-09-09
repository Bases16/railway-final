package edu.arf4.trains.railwayfinal.service;

import edu.arf4.trains.railwayfinal.dao.GenericTrainDao;
import edu.arf4.trains.railwayfinal.dao.PassengerDao;
import edu.arf4.trains.railwayfinal.dao.StationDao;
import edu.arf4.trains.railwayfinal.dao.TicketDao;
import edu.arf4.trains.railwayfinal.dao.TrainDao;
import edu.arf4.trains.railwayfinal.dto.TrainDto;
import edu.arf4.trains.railwayfinal.model.GenericTrain;
import edu.arf4.trains.railwayfinal.model.Passenger;
import edu.arf4.trains.railwayfinal.model.SeatsStateAtPoint;
import edu.arf4.trains.railwayfinal.model.SpecRoutePoint;
import edu.arf4.trains.railwayfinal.model.Station;
import edu.arf4.trains.railwayfinal.model.Ticket;
import edu.arf4.trains.railwayfinal.model.Train;
import edu.arf4.trains.railwayfinal.model.TrainCar;
import edu.arf4.trains.railwayfinal.model.TrainCarType;
import edu.arf4.trains.railwayfinal.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class TicketService {

//    private final GenericTrainDao genericTrainDao;
    private final TicketDao ticketDao;
    private final TrainDao trainDao;
    private final StationDao stationDao;
    private final PassengerDao passengerDao;

    @Autowired
    public TicketService(TicketDao ticketDao, TrainDao trainDao,
                         StationDao stationDao, PassengerDao passengerDao    ) {
        this.ticketDao = ticketDao;
        this.trainDao = trainDao;
        this.stationDao = stationDao;
        this.passengerDao = passengerDao;
    }

    @Transactional
    public void buyTicket(TrainDto trainDto, Long passengerId, String carType) {

        LocalDateTime departTime = Converter.convertStringToLocalDateTime(trainDto.getLocalSrcDepartDateTime());
        if (LocalDateTime.now().isAfter(departTime.minusMinutes(10))) {
            throw new RuntimeException("LESS THAN 10 MIN TO TRAIN DEPARTURE"); //todo  CREATE CUSTOM EXCEPTION 1??
        }

        Train train = this.trainDao.getTrainById(trainDto.getId());

        Set<Ticket> trainTickets = train.getTickets();
        for (Ticket ticket : trainTickets) {

            if (ticket.getPassenger().getId().equals(passengerId)) {
                throw new RuntimeException("THE PASSENGER HAS ALREADY REGISTERED ON THIS TRAIN");
                //todo  CREATE CUSTOM EXCEPTION 2??
            }

        }


        List<TrainCar> trainCars = train.getTrainCars();
        int numberOfEmptySeat = -1;

        for (TrainCar car : trainCars) {

            if (car.getType() != TrainCarType.valueOf(carType)) continue;

            List<SeatsStateAtPoint> seatsAtCar = car.getSeatsStateAtCar();

            List<Integer> availSeats = new ArrayList<>();

            // firstly we add in the list all available seats at the first station
            List<Boolean> seatsAtFirstStation = seatsAtCar.get(0).getSeatStates();
            for (int j = 0; j < seatsAtFirstStation.size(); j++) {
                Boolean seat = seatsAtFirstStation.get(j);
                if (!seat) availSeats.add(j);
            }

            // then we exclude from the list those seats which are not available at the following stations
            for (int i = 1; i < seatsAtCar.size(); i++) {

                List<Boolean> seats = seatsAtCar.get(i).getSeatStates();

                for (int j = 0; j < seats.size(); j++) {
                    Boolean seat = seats.get(j);
                    if (seat) {
                        if (availSeats.contains(j)) availSeats.remove(Integer.valueOf(j));
                    }
                }
            }

            if (availSeats.isEmpty()) continue;

            Collections.sort(availSeats);
            numberOfEmptySeat = availSeats.get(0) + 1;
            break;
        }

        if (numberOfEmptySeat == -1) {
            throw new RuntimeException("THERE ARE NO TICKETS LEFT FOR THIS CAR TYPE");
            //todo  CREATE CUSTOM EXCEPTION 3??
        }

        String[] ar = trainDto.getLocalRoute().split(" ");
        String fromName = ar[0];
        String toName = ar[2];

        Station from = this.stationDao.getStationByName(fromName);
        Station to = this.stationDao.getStationByName(toName);

        Passenger passenger = this.passengerDao.getPassengerById(passengerId);

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
        ticket.setStationFrom(from);
        ticket.setStationTo(to);
        ticket.setDepartureDateTime(Converter.convertStringToLocalDateTime(trainDto.getLocalSrcDepartDateTime()));
        ticket.setArrivalDateTime(Converter.convertStringToLocalDateTime(trainDto.getLocalDstArrivalDateTime()));
        ticket.setNumberOfSeat(numberOfEmptySeat);


        this.ticketDao.addTicket(ticket);










    }





























}
