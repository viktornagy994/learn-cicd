package hu.progmasters.kingfishair.controller;

import com.google.zxing.BarcodeFormat;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import hu.progmasters.kingfishair.domain.*;
import hu.progmasters.kingfishair.dto.AirportIdsForReservation;
import hu.progmasters.kingfishair.dto.AirportOptionForReservationForm;
import hu.progmasters.kingfishair.dto.FilghtOptionForReservationForm;
import hu.progmasters.kingfishair.dto.adminReservation.AdminFlightReservationsItem;
import hu.progmasters.kingfishair.dto.adminRouteForm.CreateRoute;
import hu.progmasters.kingfishair.dto.incoming.NewReservationCommand;
import hu.progmasters.kingfishair.dto.outgoing.*;
import hu.progmasters.kingfishair.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Value("${backend.url}")
    private String backendUrl;

    @Value("${frontend.url}")
    private String frontendUrl;

    private ReservationService reservationService;
    private final TemplateEngine templateEngine;
    private ServletContext servletContext;

    @Autowired
    public ReservationController(ReservationService reservationService, TemplateEngine templateEngine, ServletContext servletContext) {
        this.reservationService = reservationService;
        this.templateEngine = templateEngine;
        this.servletContext = servletContext;
    }


    @GetMapping("/my-reservation-future")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<ReservationListItemForUser>> getFutureReservationListForUser() {

        return new ResponseEntity<>(reservationService.getFutureReservationListForUser(), HttpStatus.OK);
    }

    @GetMapping("/my-reservation-past")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<ReservationListItemForUser>> getPastReservationListForUser() {

        return new ResponseEntity<>(reservationService.getPastReservationListForUser(), HttpStatus.OK);
    }

    @GetMapping("/my-reservation-pending")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<ReservationListItemForUser>> getPendingReservationListForUser() {

        return new ResponseEntity<>(reservationService.getPendingReservationListForUser(), HttpStatus.OK);
    }

    @GetMapping("/reservation-details/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<ReservationDetailsForUser> getReservationDetails(@PathVariable Long id) {
        ReservationDetailsForUser reservationDetailsForUser = reservationService.getReservationDetails(id);
        if (reservationDetailsForUser != null) {
            return ResponseEntity.ok(reservationDetailsForUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-airport-list-for-reservation-form")

    public ResponseEntity<List<AirportOptionForReservationForm>> getAirportListForReservationForm() {
        return new ResponseEntity<>(this.reservationService.getAirportListForReservationForm(), HttpStatus.OK);
    }

    @PostMapping("/get-flight-list-for-reservation-form")
    public ResponseEntity<List<FilghtOptionForReservationForm>> getFlightListForReservationForm(@RequestBody AirportIdsForReservation airportIdsForReservation) {
        return new ResponseEntity<>(this.reservationService.getFlightListForReservationForm
                (airportIdsForReservation.getDepartureAirportId(), airportIdsForReservation.getArrivalAirportId(), airportIdsForReservation.getDatePicker()), HttpStatus.OK);
    }

    @GetMapping("/get-reservation-form-data")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<ReservationFormData> getReservationFormData() {
        ReservationFormData reservationFormData = new ReservationFormData(getGenders(), getExtraAddonOptions());
        return new ResponseEntity<>(reservationFormData, HttpStatus.OK);
    }

    private List<GenderOption> getGenders() {
        List<GenderOption> genderOptions = new ArrayList<>();
        for (Gender gender : Gender.values()) {
            genderOptions.add(new GenderOption(gender));
        }
        return genderOptions;
    }

    private List<ExtraAddonOption> getExtraAddonOptions() {
        List<ExtraAddonOption> extraAddonOptions = new ArrayList<>();
        for (ExtraAddon extraAddon : ExtraAddon.values()) {
            extraAddonOptions.add(new ExtraAddonOption(extraAddon));
        }
        return extraAddonOptions;
    }

    @PostMapping("/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Long> saveReservation(@RequestBody List<@Valid NewReservationCommand> newReservationCommandArray, @PathVariable Long id) {
        Long reservationId = reservationService.saveReservation(newReservationCommandArray, id);
        return new ResponseEntity<>(reservationId, HttpStatus.CREATED);
    }

    @PutMapping("/pay/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Long> payReservation(@PathVariable Long id) {
        Long reservationId = reservationService.payReservation(id);
        return new ResponseEntity<>(reservationId, HttpStatus.CREATED);
    }

    @GetMapping("/reservation-seats/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<ReservedSeats>> getReservationSeats(@PathVariable Long id) {
        List<ReservedSeats> reservedSeats = reservationService.getReservedSeats(id);
        if (reservedSeats != null) {
            return ResponseEntity.ok(reservedSeats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/barcodes/zxing/qrcode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<BufferedImage> generateQRCode(@PathVariable("id") String id) throws Exception {

        String barcodeText = backendUrl+"/api/reservations/checkin/"+id;

        return new ResponseEntity<>(reservationService.generateQRCodeImage(barcodeText), HttpStatus.OK);
    }

    @GetMapping("/checkin/{id}")
    public ResponseEntity<Void> changeCheckinStatus(@PathVariable Long id) {
       reservationService.changeCheckedinStatus(id);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/checkin-status/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Boolean> getCheckinStatus(@PathVariable Long id) {

        return new ResponseEntity<Boolean>(reservationService.getCheckinStatus(id), HttpStatus.CREATED);
    }

    @GetMapping("/get-ticket")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getTicket(@RequestParam String reservationId, @RequestParam String seatId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long reservationIdLong=Long.parseLong(reservationId);
        Long seatIdLong=Long.parseLong(seatId);
        Reservation reservation =reservationService.findReservationById(reservationIdLong);
        Seat seat =reservationService.findSeatById(seatIdLong);
        String barcodeText = backendUrl+"/api/reservations/checkin/"+reservationIdLong;
        File QRCode=reservationService.generateQRCodeImageToPdf(barcodeText);

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("reservationId", reservation.getId());
        context.setVariable("reservationDate", reservation.getReservationHoldingDeadline());

        context.setVariable("departureAirport", reservation.getFlight().getRoute().getDepartureAirport().getName());
        context.setVariable("std", reservation.getFlight().getStDeparture().toString());
        context.setVariable("arrivalAirport", reservation.getFlight().getRoute().getArrivalAirport().getName());
        context.setVariable("sta", reservation.getFlight().getStArrival().toString());

        context.setVariable("passengerName",seat.getName());
        context.setVariable("passengerIdentificationNumber", seat.getIdentificationNumber());
        context.setVariable("passengerGender", seat.getGender());
        context.setVariable("choosenExtras", seat.getExtraAddonList());
        context.setVariable("seatNumber", seat.getSeatNumber());

        context.setVariable("QRcode", QRCode);

        String orderHtml = templateEngine.process("pdf-input", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();

        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }


    @GetMapping("/admin-get-flight-reservations/{id}")
    public ResponseEntity<List<AdminFlightReservationsItem>> getAdminFlightReservations(@PathVariable("id") Long flightId) {
        List<AdminFlightReservationsItem> flightReservations = this.reservationService.getAdminFlightReservations(flightId);
        return new ResponseEntity<>(flightReservations,HttpStatus.OK);
    }

    @GetMapping("/admin-delete-seat/{id}")
    public ResponseEntity adminDeleteSeat(@PathVariable("id") Long seatId) {
        this.reservationService.adminDeleteSeat(seatId);
        return new ResponseEntity(HttpStatus.OK);
    }



}
