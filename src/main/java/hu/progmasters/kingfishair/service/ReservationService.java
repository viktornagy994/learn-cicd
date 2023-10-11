package hu.progmasters.kingfishair.service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.html2pdf.HtmlConverter;
import hu.progmasters.kingfishair.domain.*;
import hu.progmasters.kingfishair.dto.AirportOptionForReservationForm;
import hu.progmasters.kingfishair.dto.FilghtOptionForReservationForm;
import hu.progmasters.kingfishair.dto.adminReservation.AdminFlightReservationsItem;
import hu.progmasters.kingfishair.dto.adminReservation.AdminReservationItem;
import hu.progmasters.kingfishair.dto.incoming.FlightDetails;
import hu.progmasters.kingfishair.dto.incoming.NewReservationCommand;
import hu.progmasters.kingfishair.domain.RegisteredUser;
import hu.progmasters.kingfishair.dto.outgoing.ReservationDetailsForUser;
import hu.progmasters.kingfishair.dto.outgoing.ReservationListItemForUser;
import hu.progmasters.kingfishair.dto.outgoing.ReservedSeats;
import hu.progmasters.kingfishair.repository.ReservationRepository;
import hu.progmasters.kingfishair.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
@Transactional
public class ReservationService {


    private ReservationRepository reservationRepository;
    private UserService userService;
    private AirportService airportService;
    private FlightService flightService;
    private RouteService routeService;
    private SeatRepository seatRepository;

    private SeatService seatService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, AirportService airportService, FlightService flightService, RouteService routeService, UserService userService1, SeatRepository seatRepository, SeatService seatService) {
        this.reservationRepository = reservationRepository;

        this.airportService = airportService;
        this.flightService = flightService;
        this.routeService = routeService;
        this.userService = userService1;
        this.seatRepository = seatRepository;
        this.seatService = seatService;
    }

    public List<ReservationListItemForUser> getFutureReservationListForUser() {
        RegisteredUser registeredUser = userService.findAuthenticatedUsersAccount();
        LocalDateTime std = LocalDateTime.now();
        return reservationRepository.findAllByCustomerForFuture(registeredUser, std).stream()
                .map(ReservationListItemForUser::new).collect(Collectors.toList());
    }

    public List<ReservationListItemForUser> getPastReservationListForUser() {

        RegisteredUser registeredUser = userService.findAuthenticatedUsersAccount();
        LocalDateTime std = LocalDateTime.now();
        return reservationRepository.findAllByCustomerForPast(registeredUser, std).stream()
                .map(ReservationListItemForUser::new).collect(Collectors.toList());

    }
    public List<ReservationListItemForUser> getPendingReservationListForUser() {

        RegisteredUser registeredUser = userService.findAuthenticatedUsersAccount();
        LocalDateTime std = LocalDateTime.now();
        Date date =new Date();
        return reservationRepository.findAllByCustomerForPending(registeredUser, date, std).stream()
                .map(ReservationListItemForUser::new).collect(Collectors.toList());

    }


    public List<AirportOptionForReservationForm> getAirportListForReservationForm() {
        List<Airport> airportList = this.airportService.getAirportListForReservationForm();
        List<AirportOptionForReservationForm> airportOptionForReservationFormList = airportList.stream()
                .map(AirportOptionForReservationForm::new).collect(Collectors.toList());
        return airportOptionForReservationFormList;
    }

    public List<FilghtOptionForReservationForm> getFlightListForReservationForm(Long departureAirportId, Long
            arrivalAirportId, LocalDateTime datePicker) {
        List<FilghtOptionForReservationForm> flightOptionList = new ArrayList<>();
        Airport departureAirport = this.airportService.getAirportById(departureAirportId);
        Airport arrivalAirport = this.airportService.getAirportById(arrivalAirportId);
        if (departureAirport != null && arrivalAirport != null) {
            try {
                Route route = this.routeService.getRouteByAirports(departureAirport, arrivalAirport);
                List<Flight> flightList = flightService.getFlightsByRoute(route,datePicker);
                for (Flight flight : flightList) {
                    Long reservedSeats = 0L;
                    Long totalSeats = flight.getPlane().getNumberOfSeats()-reservedSeats;
                    for (Reservation reservation : flight.getReservationList()) {
                        if (reservation.isPaid() || reservation.getReservationHoldingDeadline().compareTo(new Date())>0){
                            reservedSeats+=reservation.getSeatList().size();
                        }
                    }
                    if (totalSeats > reservedSeats) {
                        FilghtOptionForReservationForm flightOption = new FilghtOptionForReservationForm(flight);
                        flightOption.setFreeSeats(totalSeats - reservedSeats);
                        flightOptionList.add(flightOption);
                    }
                }


            } catch (Exception e) {

            }
        }
        return flightOptionList;
    }

    public Long saveReservation(List<NewReservationCommand> newReservationCommandArray, Long id) {
        Reservation reservation = new Reservation();
        updateValues(newReservationCommandArray, id, reservation);
        Reservation savedReservation = reservationRepository.save(reservation);
        for (Seat seat : reservation.getSeatList()) {
            seat.setReservation(savedReservation);
            seatRepository.save(seat);
        }
        Date date = new Date();
       Date holdingDate = this.addMins(date,10);
        reservation.setReservationHoldingDeadline(holdingDate);
        return reservationRepository.save(reservation).getId();
    }
    public static Date addMins(Date d, int mins){
        d.setTime(d.getTime() + mins * 1000L * 60L);
        return d;
    }

    private void updateValues(List<NewReservationCommand> newReservationCommandArray, Long id, Reservation
            reservation) {
        Flight flight = this.flightService.findById(id);
        reservation.setFlight(flight);
        reservation.setCustomer(this.userService.findAuthenticatedUsersAccount());
        Long totalCost = reservation.getFlight().getRoute().getTicketFare();
        List<Seat> seatList = newReservationCommandArray.stream().map(Seat::new).collect(Collectors.toList());
        for (Seat seat : seatList) {
            seat.setReservation(reservation);
            for (ExtraAddon extraAddon : seat.getExtraAddonList()) {
                totalCost += extraAddon.getPrice();
            }
        }
        reservation.setSeatList(seatList);
        reservation.setTotalFare(totalCost);
    }

    public ReservationDetailsForUser getReservationDetails(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        return new ReservationDetailsForUser(reservation);
    }

    public List<ReservedSeats> getReservedSeats(Long id) {
        Flight flight = this.flightService.findById(id);
        Date date = new Date();
        List<Reservation> reservationList = this.reservationRepository.findAllByFlight(flight,date);
        List<Seat> allReservedSeat = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            for (Seat seat : reservation.getSeatList()) {
                allReservedSeat.add(seat);
            }

        }
        List<ReservedSeats> reservedSeats = allReservedSeat.stream()
                .map(ReservedSeats::new).collect(Collectors.toList());
        return reservedSeats;
    }

    public Long payReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        reservation.setPaid(true);
        reservationRepository.save(reservation);
        return id;
    }


    public  BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public File generateQRCodeImageToPdf(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);
        File qrImg = new File("qr-code.png");
        BufferedImage bfi = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(bfi, "PNG", qrImg);
        return qrImg;
    }



    public void changeCheckedinStatus(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        reservation.setCheckedIn(true);
        reservationRepository.save(reservation);

    }

    public boolean getCheckinStatus(Long id) {

        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        return reservation.isCheckedIn();
    }

    public void getTicketInPDF(Long reservationIdLong, Long seatIdLong)throws IOException {
        HtmlConverter.convertToPdf(new File("./pdf-input.html"),new File("demo-html.pdf"));

    }

    public Reservation findReservationById(Long id){
        return this.reservationRepository.findById(id).orElseThrow();

    }

    public Seat findSeatById(Long id){
        return this.seatService.findSeatById(id);

    }

    public List<AdminFlightReservationsItem> getAdminFlightReservations(Long flightId) {
        List<AdminFlightReservationsItem> result = new ArrayList<>();
        List<Reservation> reservationList = this.reservationRepository.findAllByFlightId(flightId);
        for (Reservation reservation : reservationList) {

            List<AdminReservationItem> adminReservationItemList = new ArrayList<>();
            for (Seat seat : reservation.getSeatList()) {
                adminReservationItemList.add(new AdminReservationItem(seat));
            }

            result.add(new AdminFlightReservationsItem(
                    reservation.getCustomer().getName(),
                    reservation.getCustomer().getEmail(),
                    reservation.getCustomer().getPhoneNumber(),
                    reservation.getCustomer().getAddress(),
                    adminReservationItemList
            ));

        }

        return result;
    }

    public void adminDeleteSeat(Long seatId) {
        Seat seat = this.seatRepository.findById(seatId).orElse(null);
        if (seat != null) {
            this.seatRepository.delete(seat);
        }
    }


}

