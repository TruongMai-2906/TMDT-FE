package com.example.cdwebbe.controller;

import com.example.cdwebbe.model.Category;
import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.OrderDetail;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.payload.ChartResponse;
import com.example.cdwebbe.payload.Response;
import com.example.cdwebbe.repository.CategoryRepository;
import com.example.cdwebbe.repository.OrderDetailRepository;
import com.example.cdwebbe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/admin/chart")
public class ChartAdminController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

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
        } else if (time.equals("week")){
            SimpleDateFormat format= new SimpleDateFormat("dd");
            for (int i=1; i <=7; i++){
                dateEnd = calendar.getTime();
                System.out.println("dateStart"+calendar.getTime());
                calendar.add(Calendar.DAY_OF_WEEK, -1);
                System.out.println("dateEnd"+calendar.getTime());
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
        } else if (time.equals("month")){
            SimpleDateFormat format= new SimpleDateFormat("dd");
            for (int i=1; i <=30; i++){
                dateEnd = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, -1);
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
        } else if (time.equals("last-month")){
            SimpleDateFormat format= new SimpleDateFormat("dd");
            calendar.add(Calendar.MONTH, -1);
            for (int i=1; i <=30; i++){
                dateEnd = calendar.getTime();
                calendar.add(Calendar.DAY_OF_MONTH, -1);
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
        } else if (time.equals("last-year")){
            SimpleDateFormat format= new SimpleDateFormat("MM");
            for (int i=1; i <=12; i++){
                dateEnd = calendar.getTime();
                calendar.add(Calendar.MONTH, -1);
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

    /**
     * 1. Tính được tổng doanh thu >
     * @param time
     * @return
     * backgroudColor (màu #)
     * #--
     */
    @GetMapping ("/percent-revenue")
    public ResponseEntity<?> getPercentRevenue(@RequestParam(name= "time", required = false, defaultValue = "tatca") String time){
        List<String> labelList=new ArrayList<>();
        List<Double> netRevenue=new ArrayList<>();
        List<String> backgroudColor=new ArrayList<>();
        String [] color =   {"#FFBF00", "#F5F5DC", "#9966CC", "#3D2B1F", "#7FFFD4", "#000000", "#007FFF",
                            "#0000FF", "#964B00", "#F0DC82", "#CC5500", "#C41E3A", "#960018","#ACE1AF","#DE3163", "#7FFF00" };

        //Lấy danh sách các category trong database?
        List<Category> categoryList=new ArrayList<>();
        categoryList = categoryRepository.findAll();

        //Tổng doanh thu tất cả đơn hàng
        double totalRevenue = 0;
        List<Order> orderList = new ArrayList<>();
        orderList= orderRepository.findAll();
        for (Order order : orderList){
            for (OrderDetail orderDetail: order.getOrderDetailList()){
                totalRevenue +=orderDetail.getTotalPrice();
            }
        }

        System.out.println("Tổng tất cả doanh thu của tất cả :"+totalRevenue );
        double totalRevenueByCategory=0;

        //Tổng daonh thu theo category;
        int count=0;
        for (Category category: categoryList){
            for (OrderDetail orderDetail: orderDetailRepository.findAllByProductCategory(category)){
                totalRevenueByCategory += orderDetail.getTotalPrice();
            }
            System.out.println("Tổng tất cả doanh thu của "+category.getKeywork()+" :"+totalRevenueByCategory );
            if (totalRevenueByCategory !=0){
                labelList.add(category.getKeywork());
                System.out.println("Category có trong order:" + category.getKeywork());
                System.out.println("Chưa chia 100: "+((double)totalRevenueByCategory/totalRevenue ) );
                netRevenue.add( ( (double)totalRevenueByCategory/totalRevenue )*100 );
                totalRevenueByCategory =0;
                backgroudColor.add(color[count++]);
            }
        }

        ChartResponse chartResponse =new ChartResponse();
        chartResponse.setTime(time);
        chartResponse.setLabelList(labelList);
        chartResponse.setNetRevenue(netRevenue);
        chartResponse.setBackgroudColor(backgroudColor);

        Response response=new Response();
        response.setStatusCode(HttpStatus.OK);
        response.setMessage("Successfully submitted data percent net revenue!");
        response.setData(chartResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
