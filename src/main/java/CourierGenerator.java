import static java.util.UUID.randomUUID;

public class CourierGenerator {
    public static Courier randomCourier() {

        return new Courier()
                .setLogin(randomUUID().toString())
                .setPassword(randomUUID().toString())
                .setFirstName(randomUUID().toString());
    }
}
