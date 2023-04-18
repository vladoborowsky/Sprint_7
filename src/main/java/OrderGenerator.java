import static java.util.UUID.randomUUID;

import java.util.Random;


public class OrderGenerator {
    public static Order randomOrder() {
        Random random = new Random();
        String date = "2023-0" + random.nextInt(9) + "-" + random.nextInt(2) + "" + random.nextInt(9);

        return new Order()
                .setFirstName(randomUUID().toString())
                .setLastName(randomUUID().toString())
                .setAddress(randomUUID().toString())
                .setMetroStation(randomUUID().toString())
                .setPhone(randomUUID().toString())
                .setRentTime(random.nextInt(10))
                .setDeliveryDate(date)
                .setComment(randomUUID().toString());
    }
}
