package com.example.cdwebbe;

        import com.example.cdwebbe.model.Order;
        import com.example.cdwebbe.model.Product;
        import com.example.cdwebbe.repository.OrderRepository;
        import com.example.cdwebbe.repository.ProductRepository;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.autoconfigure.domain.EntityScan;
        import org.springframework.context.ApplicationContext;
        import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

        import javax.annotation.PostConstruct;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.time.ZoneId;
        import java.time.ZonedDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.*;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        CdWebBeApplication.class,
        Jsr310JpaConverters.class
})
public class CdWebBeApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {

        ApplicationContext context =  SpringApplication.run(CdWebBeApplication.class, args);

        OrderRepository orderRepository = context.getBean(OrderRepository.class);

        List<String> labelList = new ArrayList<>();
        List<Integer> amountOrder = new ArrayList<>();
        List<Double> netRevenue = new ArrayList<>();
        String time = "today";
        Calendar calendar = Calendar.getInstance();
        Date dateStart;
        Date dateEnd;
        List<Order> orderList =new ArrayList<>();

        if (time.equals("today")){
            SimpleDateFormat format= new SimpleDateFormat("HH a");
            for (int i=1; i <=24; i++){
                dateStart = calendar.getTime();
                calendar.add(Calendar.HOUR_OF_DAY, -1);
                dateEnd = calendar.getTime();

                // Lấy các order between dateStart and dateEnd
                orderList = orderRepository.findAllByDateCreateBetween(dateStart, dateEnd);

                // Tính số lượng đơn hàng;
                amountOrder.add(orderList.size());

                // Tính tổng doanh thu = totalPriceOrder của các order cộng với nhau;
                double netRevenueUnit=0;
                for (Order order: orderList){
                    netRevenueUnit += order.getTotalPriceOrder();
                }
                netRevenue.add(netRevenueUnit);

                calendar.add(Calendar.HOUR_OF_DAY, +7);
                labelList.add(format.format(calendar.getTime()));
                calendar.add(Calendar.HOUR_OF_DAY, -7);
            }
        }

        System.out.println("Good Luck !");
    }
}

