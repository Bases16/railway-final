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
import java.util.List;
import java.util.Set;

@Service
public class TicketService {

    private final GenericTrainDao genericTrainDao;
    private final TrainDao trainDao;
    private final StationDao stationDao;
    private final PassengerDao passengerDao;
//    private final TicketDao ticketDao;

    @Autowired
    public TicketService(GenericTrainDao genericTrainDao, TrainDao trainDao,
                         StationDao stationDao, PassengerDao passengerDao    ) {
        this.genericTrainDao = genericTrainDao;
        this.trainDao = trainDao;
        this.stationDao = stationDao;
        this.passengerDao = passengerDao;
    }

    @Transactional
    public void buyTicket(TrainDto trainDto, Long passengerId, String carType) {

        {
            LocalDateTime departTime = Converter.convertStringToLocalDateTime(trainDto.getLocalSrcDepartDateTime());
            if (LocalDateTime.now().isAfter(departTime.minusMinutes(10))) {
                throw new RuntimeException("LESS THAN 10 MIN TO TRAIN DEPARTURE"); //todo  CREATE CUSTOM EXCEPTION 1??
            }
        }

        Train train = this.trainDao.getTrainById(trainDto.getId());
        {
            Set<Ticket> trainTickets = train.getTickets();
            for (Ticket ticket : trainTickets) {

                if (ticket.getPassenger().getId().equals(passengerId)) {
                    throw new RuntimeException("THE PASSENGER HAS ALREADY REGISTERED ON THIS TRAIN");
                    //todo  CREATE CUSTOM EXCEPTION 3??
                }

            }
        }

        String[] ar = trainDto.getLocalRoute().split(" ");
        String fromName = ar[0];
        String toName = ar[2];

        Station from = this.stationDao.getStationByName(fromName);
        Station to = this.stationDao.getStationByName(toName);

        {
            List<SpecRoutePoint> srPoints = train.getSpecRoutePoints();

            boolean wasStationFromCaught = false;
            for (SpecRoutePoint srPoint : srPoints) {

                if (srPoint.getRoutePoint().getStation() == to) break;
                if (srPoint.getRoutePoint().getStation() == from) wasStationFromCaught = true;
                if (wasStationFromCaught) {  //todo  CREATE CUSTOM EXCEPTION 2??
                    //if (srPoint.getTicketsLeft() == 0) throw new RuntimeException("THERE'S NO TICKETS LEFT");
                }
            }
        }


        Passenger passenger = this.passengerDao.getPassengerById(passengerId);

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setTrain(train);
        ticket.setStationFrom(from);
        ticket.setStationTo(to);
        ticket.setDepartureDateTime(Converter.convertStringToLocalDateTime(trainDto.getLocalSrcDepartDateTime()));
        ticket.setArrivalDateTime(Converter.convertStringToLocalDateTime(trainDto.getLocalDstArrivalDateTime()));


        List<TrainCar> trainCars = train.getTrainCars();

        for (TrainCar car : trainCars) {

            if (car.getType() != TrainCarType.valueOf(carType)) continue;

            List<SeatsStateAtPoint> seatsAtPoint = car.getSeatsStateAtPoints();

            int indexOfEmptySeat = -1;

            for (int i = 0; i < seatsAtPoint.size(); i++) {


                List<Boolean> seats = seatsAtPoint.get(i).getSeatStates();

                for (int j = 0; j < seats.size(); j++) {

                    Boolean seat = seats.get(j);
                    if (!seat) {


                    }

                }




            }



        }









        GenericTrain genTrain = this.genericTrainDao.getGenericTrainById(trainDto.getId());




    }





























}
