package com.example.cdwebbe.controller;

import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.payload.ChartResponse;
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
        List<Order> orderList= new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        if (time.equals("today")){
            SimpleDateFormat format= new SimpleDateFormat("HH a");
            for (int i=1; i <=24; i++){
                dateEnd = calendar.getTime();
                calendar.add(Calendar.HOUR_OF_DAY, -1);
                dateStart = calendar.getTime();

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
        } else if (time.equals("yesterday")){
            SimpleDateFormat format= new SimpleDateFormat("HH a");
            calendar.add(Calendar.DAY_OF_WEEK, -1);
            for (int i=1; i <=24; i++){
                dateEnd = calendar.getTime();
                calendar.add(Calendar.HOUR_OF_DAY, -1);
                dateStart = calendar.getTime();

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
        } else if (time.equals("Last 7 days")){
            SimpleDateFormat format= new SimpleDateFormat("dd");
            for (int i=1; i <=7; i++){
                dateStart = calendar.getTime();
                System.out.println("dateStart"+calendar.getTime());
                calendar.add(Calendar.DAY_OF_WEEK, -1);
                System.out.println("dateEnd"+calendar.getTime());
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
        } else if (time.equals("Last 30 days")){
            SimpleDateFormat format= new SimpleDateFormat("dd");
            for (int i=1; i <=30; i++){
                dateStart = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, -1);
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
        } else if (time.equals("Last month")){
            SimpleDateFormat format= new SimpleDateFormat("dd");
            calendar.add(Calendar.MONTH, -1);
            for (int i=1; i <=30; i++){
                dateStart = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, -1);
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
        } else if (time.equals("Last 1 year")){
            SimpleDateFormat format= new SimpleDateFormat("MM");
            for (int i=1; i <=12; i++){
                dateStart = calendar.getTime();
                calendar.add(Calendar.MONTH, -1);
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

        ChartResponse chartResponse =new ChartResponse();
        chartResponse.setTime(time);
        chartResponse.setLabelList(labelList);
        chartResponse.setAmountOrder(amountOrder);
        chartResponse.setNetRevenue(netRevenue);

        Response response=new Response();
        response.setStatusCode(HttpStatus.OK);
        response.setMessage("Successfully submitted data net revenue!");
        response.setData(chartResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping ("/total-revenue")
    public ResponseEntity<?> getTotalRevenue(){
        return null;
    }

}
