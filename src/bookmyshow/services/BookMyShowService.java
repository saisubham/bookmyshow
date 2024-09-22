package bookmyshow.services;

import java.util.List;
import java.util.Set;

import bookmyshow.models.Booking;
import bookmyshow.models.CinemaHall;
import bookmyshow.models.City;
import bookmyshow.models.Movie;
import bookmyshow.models.Payment;
import bookmyshow.models.Seat;
import bookmyshow.models.Show;
import bookmyshow.models.User;

public class BookMyShowService {
    private final CityService cityService;
    private final CinemaHallService cinemaHallService;
    private final UserService userService;
    private final MovieService movieService;
    private final ShowService showService;
    private final SeatService seatService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    public BookMyShowService(CityService cityService, CinemaHallService cinemaHallService, UserService userService,
                             MovieService movieService, ShowService showService, SeatService seatService,
                             BookingService bookingService, PaymentService paymentService) {
        this.cityService = cityService;
        this.cinemaHallService = cinemaHallService;
        this.userService = userService;
        this.movieService = movieService;
        this.showService = showService;
        this.seatService = seatService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    public void findAllCities() {
        List<City> cities = cityService.findAll();
        System.out.println(cities);
    }

    public void findMoviesByCity(String cityName) {
        List<Movie> movies = movieService.findAll().stream()
                .filter(movie -> movie.getCityNames().contains(cityName))
                .toList();
        System.out.println(movies);
    }

    public void findShowsByMovie(String movieName) {
        Movie movie = movieService.findById(movieName);
        Set<String> showIds = movie.getShows();
        List<Show> shows = showIds.stream().map(showService::findById).toList();
        System.out.println(shows);
    }

    public void findAvailableSeatsByShowId(String showId) {
        List<Seat> seats = seatService.findAllAvailableByShowId(showId);
        System.out.println(seats);
    }

    public void bookTicket(String seatId, String userId, long paymentDelayMillis) {
        if (!bookingService.book(seatId, userId)) {
            return;
        }
        try {
            Thread.sleep(paymentDelayMillis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
        Payment payment = paymentService.makePayment(seatId, userId);
        if (payment == null) {
            return;
        }
        Booking booking = bookingService.book(payment.getId());
//        System.out.println(booking);
    }

    public void run() throws InterruptedException {
        City city = cityService.addCity("delhi");
        CinemaHall cinemaHall = cinemaHallService.addCinemaHall("inox", city.getName());
        Show show = showService.addShow("show 1", cinemaHall.getName());
        Movie movie = movieService.addMovie("3 idiots");
        movie.addCityAndShow(city.getName(), show.getName());
        Seat seat1 = seatService.addSeat(show.getName(), "seat 1");
        Seat seat2 = seatService.addSeat(show.getName(), "seat 2");
        Seat seat3 = seatService.addSeat(show.getName(), "seat 3");
        User john = userService.addUser("john");
        User alice = userService.addUser("alice");
        User bob = userService.addUser("bob");
        User mary = userService.addUser("mary");

        findAllCities();
        findMoviesByCity(city.getName());
        findShowsByMovie(movie.getName());
        findAvailableSeatsByShowId(show.getName());

        System.out.println("---");
        bookingService.book(seat1.getId(), john.getName());
        findAvailableSeatsByShowId(show.getName());

        System.out.println("---");
        Thread.sleep(4000);
        findAvailableSeatsByShowId(show.getName());

        Thread t1 = new Thread(() -> bookTicket(seat1.getId(), alice.getName(), 4000));
        Thread t2 = new Thread(() -> bookTicket(seat1.getId(), john.getName(), 4000));
        Thread t3 = new Thread(() -> bookTicket(seat1.getId(), bob.getName(), 4000));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("---");
        System.out.println(seatService.findAllByShowId(show.getName()));
    }

}
