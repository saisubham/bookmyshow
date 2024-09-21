package bookmyshow;

import bookmyshow.services.BookMyShowService;
import bookmyshow.services.BookingService;
import bookmyshow.services.CinemaHallService;
import bookmyshow.services.CityService;
import bookmyshow.services.MovieService;
import bookmyshow.services.PaymentService;
import bookmyshow.services.SeatService;
import bookmyshow.services.ShowService;
import bookmyshow.services.UserService;

public class Main {
    public static void main(String[] args) {
        CityService cityService = new CityService();
        CinemaHallService cinemaHallService = new CinemaHallService();
        MovieService movieService = new MovieService();
        SeatService seatService = new SeatService();
        ShowService showService = new ShowService();
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService(seatService);
        BookingService bookingService = new BookingService(seatService, paymentService);

        BookMyShowService bookMyShowService = new BookMyShowService(cityService, cinemaHallService, userService,
                movieService, showService, seatService, bookingService, paymentService);

        try {
            bookMyShowService.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
