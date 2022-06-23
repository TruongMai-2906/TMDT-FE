package com.example.cdwebbe.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



	@PostMapping("/checkoutOrder")
	public ResponseEntity<?> checkoutOrder(@CurrentUser UserPrincipal currentUser,
			@RequestBody ChangeToOrderRequest changeToOrderRequest) {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return ResponseEntity.ok().body(new ApiResponse(false, "Bạn phải đăng nhập để xem orders"));
		}
	
	
		saveToOrder(currentUser, changeToOrderRequest);
		return ResponseEntity.ok().body("showCheckOutSuccess");

	}

	public void saveToOrder(UserPrincipal userDetails, ChangeToOrderRequest changeToOrderRequest) {
		System.out.println(changeToOrderRequest);
//		
		 User user = userRepository.findOnedById(userDetails.getId());
		 Cart cartMain = user.getCart();
		   List<CartItem> cartList = cartItemRepository.findAllByCartId(cartMain.getId());
           double totalPriceCart=0;
           for (CartItem cartItem : cartList) {
               double temp = cartItem.getTotalPrice();
               totalPriceCart = totalPriceCart+temp;
           }
//		
		Order order = new Order();
		order.setTotalPriceOrder(totalPriceCart);
		order.setShipfee(changeToOrderRequest.getFeeTotal());
		User userEntity = this.userRepository.findById(userDetails.getId()).get();
		order.setUser(userEntity);
		order.setDateCreate(new Date());
		orderRepository.save(order);
		for (int i = 0; i < changeToOrderRequest.getIdProducts().length; i++) {
			Product product = productRepository.findOneById((long) changeToOrderRequest.getIdProducts()[i]);
			System.out.println(product);
			if (product != null) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(product);
				orderDetail.setOrder(order);
//				xu li quantity & totalOrderDetailPrice
				CartItem cartItemEntity = handleQuantityAndTotalPriceProduct(userEntity, product);
				orderDetail.setTotalPrice(cartItemEntity.getTotalPrice());
				orderDetail.setQuantity(cartItemEntity.getQuantity());

				orderDetailRepository.save(orderDetail);

			} else {

			}

		}
//		xoa từng cartItem theo Id của cartId
		orderRepository.save(order);

		Cart cart = cartRepository.findByUser(userEntity);
		System.out.println("iddddd" + cart.getId());
		cartItemRepository.deleteAllByCart(cart);

	}

	private CartItem handleQuantityAndTotalPriceProduct(User userEntity, Product product) {
		Cart cart = cartRepository.findByUser(userEntity);
		CartItem cartItemEntity = cartItemRepository.findByCartAndProduct(cart, product);

		return cartItemEntity;

	}

}
