package com.flight_reservation_app_5.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Reservation extends AbstractEntity{
	
	private boolean checkedIn;
	
    private int numberofBags;
   
    @OneToOne
    private Passenger passenger;
    
    @OneToOne
    private Flight flight;
    
	public boolean isCheckedIn() {
		return checkedIn;
	}
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}
	public int getNumberofBags() {
		return numberofBags;
	}
	public void setNumberofBags(int numberofBags) {
		this.numberofBags = numberofBags;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
    
}
