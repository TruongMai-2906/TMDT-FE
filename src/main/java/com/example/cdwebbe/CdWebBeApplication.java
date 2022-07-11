package com.example.cdwebbe;

        import com.example.cdwebbe.model.Order;
        import com.example.cdwebbe.model.Product;
        import com.example.cdwebbe.model.User;
        import com.example.cdwebbe.repository.OrderRepository;
        import com.example.cdwebbe.repository.ProductRepository;
        import com.example.cdwebbe.repository.UserRepository;
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
        System.out.println("Good Luck !");
    }
}

