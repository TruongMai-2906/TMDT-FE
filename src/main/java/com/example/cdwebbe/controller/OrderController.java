package com.example.cdwebbe.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.cdwebbe.payload.ResponseOrderUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdwebbe.DTO.OrderDTO;
import com.example.cdwebbe.config.ModelMapperConfig;
import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.CartItem;
import com.example.cdwebbe.model.Order;
import com.example.cdwebbe.model.OrderDetail;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.payload.ApiResponse;
import com.example.cdwebbe.payload.ChangeToOrderRequest;
import com.example.cdwebbe.repository.CartItemRepository;
import com.example.cdwebbe.repository.CartRepository;
import com.example.cdwebbe.repository.OrderDetailRepository;
import com.example.cdwebbe.repository.OrderRepository;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.repository.UserRepository;
import com.example.cdwebbe.security.CurrentUser;
import com.example.cdwebbe.security.UserPrincipal;

import com.google.common.reflect.TypeToken;
//import com.example.cdwebbe.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderDetailRepository orderDetailRepository;
	@Autowired
	ModelMapper mapper;


	@PostMapping("/checkoutOrder")
	public ResponseEntity<?> checkoutOrder(@CurrentUser UserPrincipal currentUser,
										   @RequestBody ChangeToOrderRequest changeToOrderRequest) {

//		saveToOrder(currentUser, changeToOrderRequest);
		try{
			System.out.println(changeToOrderRequest);
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
				return ResponseEntity.ok().body(new ApiResponse(false, "Bạn phải đăng nhập để xem orders"));
			}
			User user = userRepository.findOnedById(currentUser.getId());
			Cart cartMain = user.getCart();
			List<CartItem> cartList = cartItemRepository.findAllByCartId(cartMain.getId());
			if (cartList.size() == 0) {
				return ResponseEntity.ok().body(new ApiResponse(false, "Chưa có cart item"));
			}
			double totalPriceCart = 0;
			for (CartItem cartItem : cartList) {
				double temp = cartItem.getTotalPrice();
				totalPriceCart = totalPriceCart + temp;
			}

			Order order = new Order();
			order.setTotalPriceOrder(totalPriceCart);
			order.setShipfee(changeToOrderRequest.getFeeTotal());
			User userEntity = this.userRepository.findById(currentUser.getId()).get();
			order.setUser(userEntity);
			order.setDateCreate(new Date());
			order.setAddress(changeToOrderRequest.getAddress());
			order.setPhoneNumber(changeToOrderRequest.getPhoneNumber());
			orderRepository.save(order);
			for (int i = 0; i < cartList.size(); i++) {
				Product product = productRepository.findOneById((long) cartList.get(i).getProduct().getId());
//			Product product = productRepository.findOneById((long) changeToOrderRequest.getIdProducts()[i]);
				System.out.println(product);
				if (product != null) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setProduct(product);
					orderDetail.setOrder(order);
//				xu li quantity & totalOrderDetailPrice
					CartItem cartItemEntity = handleQuantityAndTotalPriceProduct(userEntity, product);
					orderDetail.setTotalPrice(cartItemEntity.getTotalPrice());
					orderDetail.setQuantity(cartItemEntity.getQuantity());
					orderDetail.setStatus(false);
					orderDetailRepository.save(orderDetail);

				}

			}
//		xoa từng cartItem theo Id của cartId
			orderRepository.save(order);
			Cart cart = cartRepository.findByUser(userEntity);
			System.out.println("iddddd" + cart.getId());
			cartItemRepository.deleteAllByCart(cart);
			return ResponseEntity.ok().body(new ApiResponse(true,"Đặt hàng thành công"));
		}catch (Exception e){
			return ResponseEntity.ok().body(new ApiResponse(true,"Đặt hàng thất bại "));
		}
	}

//	public ResponseEntity<?> saveToOrder(UserPrincipal userDetails, ChangeToOrderRequest changeToOrderRequest) {
//
//
//
//	}



	private CartItem handleQuantityAndTotalPriceProduct(User userEntity, Product product) {
		Cart cart = cartRepository.findByUser(userEntity);
		CartItem cartItemEntity = cartItemRepository.findByCartAndProduct(cart, product);

		return cartItemEntity;

	}
	@GetMapping("/listOrder")
	public ResponseEntity<?> getListOrderByUserId(@CurrentUser UserPrincipal currentUser) {
 
		 User user = userRepository.findOnedById(currentUser.getId());
		 List<Order> orders=orderRepository.findByUserId(user.getId());
//		 System.out.println(user);
//		 List<OrderDTO> result = new ArrayList<>();

		 List<ResponseOrderUser> responseList= new ArrayList<>();
		 for (Order order : orders) {
			 List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(order.getId());
			 ResponseOrderUser responseOrderUser = new ResponseOrderUser(order.getId(),order.getDateCreate(),orderDetailList);
			 responseList.add(responseOrderUser);

		}
		 return ResponseEntity.ok().body(responseList);
	}

}
