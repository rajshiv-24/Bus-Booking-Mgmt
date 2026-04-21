export interface Customer {
  custId: number;
  custName: string;
  phoneNo: string;
}

export interface BusRoute {
  id: number;
  src: string;
  dest: string;
}

export interface RouteSchedule {
  id: number;
  busRoute: BusRoute;
  departureTime: string;
  scheduleDt: string;
  avlSeats: number;
  totSeats: number;
  schStatus: string;
}

export interface PassengerDTO {
  passengerName: string;
  passengerAge: number;
  seatNo: string;
}

export interface BookingRequest {
  scheduleId: number;
  custId: number;
  passengers: PassengerDTO[];
}

export interface BusBooking {
  id: number;
  schedule: RouteSchedule;
  customer: Customer;
  bookingDt: string;
  bookingStatus: string;
}

export interface Passenger {
  id: number;
  passengerName: string;
  passengerAge: number;
  seatNo: string;
}

export interface AdminScheduleRequest {
  routeId: number;
  departureTime: string;
  scheduleDt: string;
  avlSeats: number;
  totSeats: number;
  schStatus: string;
}
