package com.example.cdwebbe.controller;

import com.example.cdwebbe.payload.Response;
import com.example.cdwebbe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/chart")
public class ChartAdminController {
    @Autowired
    OrderRepository orderRepository;

    /**
     * Today:       Jul 11 - Jul 11, 2022;
     * Yesterday:   Jul 10 - Jul 10, 2022;
     * Last 7 days: Jul 5 - Jul 11, 2022;
     * Last 30 days:Jun 12 - Jul 11, 2022;
     * This month:  Jul 1 - Jul 31, 2022;
     * Last month:  Jun 1 - Jun 31, 2022;
     * => Structure: monthFirst dayFirst - monthEnd dayEnd, year;
     *
     * @return
     */
    @GetMapping ("/net-revenue")
    public ResponseEntity<?> getDataNetRevenue(
            @RequestParam(name = "time", required = false, defaultValue = "today") String time,
            @RequestParam(name = "dateStart", required = false) Date dateStart,
            @RequestParam(name = "dateStart", required = false) Date dateEnd
    ){
        List<String> labelList = new ArrayList<>();
        List<Integer> amountOrder = new ArrayList<>();
        List<Double> netRevenue = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        if (time.equals("today")){
            SimpleDateFormat format= new SimpleDateFormat("HH a");
            for (int i=1; i <=24; i++){
                calendar.add(Calendar.HOUR_OF_DAY, -1);
                format.format(calendar.getTime());
            }
        }
        if (time.equals("yesterday")){

        }
        if (time.equals("week")){
            for (int i=1; i <=7; i++){
                calendar.add(Calendar.DAY_OF_WEEK, -1);
            }
        }
        if (time.equals("month")){
            for (int i=1; i <=30; i++){
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
        }
        if (time.equals("year")){
            for (int i=1; i <=12; i++){
                calendar.add(Calendar.MONTH, -1);
            }
        }
        Response response=new Response();
        response.setStatusCode(HttpStatus.OK);
        response.setMessage("Successfully submitted data net revenue!");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping ("/total-revenue")
    public ResponseEntity<?> getTotalRevenue(){
        return null;
    }

}
