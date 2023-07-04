package com.flight_reservation_app_5.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flight_reservation_app_5.dto.ReservationRequest;
import com.flight_reservation_app_5.entity.Flight;
import com.flight_reservation_app_5.entity.Passenger;
import com.flight_reservation_app_5.entity.Reservation;
import com.flight_reservation_app_5.repository.FlightRepository;
import com.flight_reservation_app_5.repository.PassengerRepository;
import com.flight_reservation_app_5.repository.ReservationRepository;
import com.flight_reservation_app_5.utilities.PdfGenerator;



@Service
public class ReservationServiceImpl implements ReservationService {

	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	
	@Override
	public Reservation bookFlight( ReservationRequest request) {
		
	String filePath = "D:\\Spring boot Project\\flight_reservation_app_5\\tickets\\reservation";
		
		Passenger  passenger =new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		passengerRepo.save(passenger);
		
		
		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberofBags(0);
		
		
		reservationRepo.save(reservation);
		
		PdfGenerator pdf =new PdfGenerator();
		pdf.generatePDF(filePath="D:\\Spring boot Project\\flight_reservation_app_5\\tickets"+reservation.getId()+".pdf", request.getFirstName(), request.getEmail(), request.getPhone(), flight.getOperatingAirlines(), flight.getDateOfDeparture(), flight.getDepartureCity(), flight.getArrivalCity());
		
		return reservation;
	}

}
