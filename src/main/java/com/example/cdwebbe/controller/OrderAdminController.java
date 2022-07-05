package com.example.cdwebbe.controller;

import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.OrderDetail;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.payload.ApiResponse;
import com.example.cdwebbe.payload.EditOrderRequest;
import com.example.cdwebbe.payload.OuputListOderAdmin;
import com.example.cdwebbe.payload.ResponseOrderUser;
import com.example.cdwebbe.repository.OrderRepository;
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

    @GetMapping("/getAllListOrderUser")
    public ResponseEntity<?> getAllOrderForAdmin(@CurrentUser UserPrincipal currentUser,@RequestParam(defaultValue ="1") int pageIndex) {
            try{
                Sort sort = Sort.by("id").ascending();
                Pageable pageable = PageRequest.of(pageIndex,1,sort);
                List<Order> orders = orderRepository.findAll(pageable).getContent();
                OuputListOderAdmin outputListOderAdmin = new OuputListOderAdmin();

                return ResponseEntity.ok().body(orders);
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
