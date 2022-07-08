package com.example.cdwebbe.controller;

import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.OrderDetail;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.payload.*;
import com.example.cdwebbe.repository.OrderRepository;
import com.example.cdwebbe.repository.UserRepository;
import com.example.cdwebbe.security.CurrentUser;
import com.example.cdwebbe.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/order/")
public class OrderAdminController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllListOrderUser")
    public ResponseEntity<?> getAllOrderForAdmin(@CurrentUser UserPrincipal currentUser,@RequestParam(defaultValue ="1") int pageIndex) {
            try{
                Sort sort = Sort.by("id").ascending();
                Pageable pageable = PageRequest.of(pageIndex,1,sort);
                List<Order> orders = orderRepository.findAll(pageable).getContent();
                List<Order> ordersAll = orderRepository.findAll();
                int totalPage= 1;
                if (ordersAll.size()>12){
                    totalPage=ordersAll.size()/12;
                }

                List<OuputListOderAdmin> ketqua = new ArrayList<>();

                for(int i=0;i<orders.size();i++){
                    Order orderTemp = orders.get(i);
                    OuputListOderAdmin temp = new OuputListOderAdmin();
                    temp.setId(orderTemp.getId());
                    temp.setAddress(orderTemp.getAddress());
                    temp.setShipfee(orderTemp.getShipfee());
                    temp.setDatecreated(orderTemp.getDateCreate().toString());
                    temp.setPhone_number(orderTemp.getPhoneNumber());
                    temp.setTotal_price_order(orderTemp.getTotalPriceOrder());
                    temp.setUserid(orderTemp.getUser().getUsername());
                    temp.setStatus(orderTemp.getStatus());
                    ketqua.add(temp);
//                    User user =userRepository.find


                }

                GetListOrderAdmin getListOrderAdmin = new GetListOrderAdmin(ketqua,totalPage);
                return ResponseEntity.ok().body(getListOrderAdmin);
            }catch (Exception e){

            }

        return ResponseEntity.ok().body(null);

    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> DeleteOder(@CurrentUser UserPrincipal currentUser,@PathVariable("id") Long idoder) {
        try{
            orderRepository.deleteById(idoder);
            return ResponseEntity.ok().body(new ApiResponse(true,"Xóa đơn hàng thành công"));
        }catch (Exception e){
            return ResponseEntity.ok().body(new ApiResponse(false,"Xóa thất bại"+e.toString()));
        }


    }

    @PostMapping("/Update")
    public ResponseEntity<?> UpdateOrder(@CurrentUser UserPrincipal currentUser, @RequestBody EditOrderRequest editOrderRequest) {
        try{
            Order order = orderRepository.findById(editOrderRequest.getId()).get();
            order.setAddress(editOrderRequest.getAddress());
            order.setDateCreate(editOrderRequest.getDateCreate());
            order.setDateDelivery(editOrderRequest.getDateDelivery());
            order.setPhoneNumber(editOrderRequest.getPhoneNumber());
            order.setShipfee(editOrderRequest.getShipfee());
            order.setTotalPriceOrder(editOrderRequest.getTotalPriceOrder());

            orderRepository.save(order);
            return ResponseEntity.ok().body(new ApiResponse(true,"Cập nhật thành công"));
        }catch (Exception e){

        }
        return ResponseEntity.ok().body(new ApiResponse(false,"Thất bại"));

    }

}
