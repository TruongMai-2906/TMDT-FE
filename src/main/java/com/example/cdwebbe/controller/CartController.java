package com.example.cdwebbe.controller;

import com.example.cdwebbe.model.Cart;
import com.example.cdwebbe.model.CartItem;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.payload.ApiResponse;
import com.example.cdwebbe.payload.CartRequest;
import com.example.cdwebbe.payload.ListCartItemRespon;
import com.example.cdwebbe.repository.CartItemRepository;
import com.example.cdwebbe.repository.CartRepository;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.repository.UserRepository;
import com.example.cdwebbe.security.CurrentUser;
import com.example.cdwebbe.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping("/add")

    public ResponseEntity<?> AddIntoCartItem(@CurrentUser UserPrincipal currentUser, @RequestBody CartRequest cartRequest){
        try{
            System.err.println("card request là "+cartRequest);
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
                return ResponseEntity.ok().body(new ApiResponse(false,"Bạn phải đăng nhập để thêm vào cart"));
            }
            User user = userRepository.findOnedById(currentUser.getId());
            Cart cartMain = user.getCart();
            if(user.getCart()==null){

                cartMain = new Cart();
                cartMain.setId(1L);
                cartMain.setUser(user);
                cartRepository.save(cartMain);
            }
            Product product = productRepository.findOneById(cartRequest.getProductid());
            if(product==null){
                return ResponseEntity.ok().body(new ApiResponse(false,"Chưa có product  này"));
            }
            if(cartItemRepository.existsCartItemByProductIdAndCartId(cartRequest.getProductid(),cartMain.getId())){
                CartItem cardItem =  cartItemRepository.findOneByProductId(cartRequest.getProductid());
                cardItem.setQuantity(cardItem.getQuantity()+1);
                cardItem.setCart(cartMain);
                cardItem.setTotalPrice(cardItem.getQuantity()*cardItem.getTotalPrice());

                return ResponseEntity.ok().body( cartItemRepository.save(cardItem));
            }

            CartItem cartItem = new CartItem(cartRequest.getQuantity(),product.getPrice(),product,cartMain);
            cartItem=cartItemRepository.save(cartItem);
            return ResponseEntity.ok().body(cartItem);
        }catch (Exception e){
            return  ResponseEntity.ok().body(new ApiResponse(false,"Lôi rồi b oi"+e.toString()));
        }

    }

    @PostMapping("/update")
    public ResponseEntity<?> RemoveCartItem(@CurrentUser UserPrincipal currentUser,@RequestParam(value = "action",defaultValue = "add") String action, @RequestBody CartRequest cartRequest){

        try{
            System.err.println("card request là "+cartRequest);
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
                return ResponseEntity.ok().body(new ApiResponse(false,"Bạn phải đăng nhập để thêm vào cart"));
            }
            User user = userRepository.findOnedById(currentUser.getId());
            Cart cartMain = user.getCart();
            if(user.getCart()==null){
                return ResponseEntity.ok().body( new ApiResponse(false,"Tính hack web tui à bạn"));
            }


            if(cartItemRepository.existsCartItemByProductIdAndCartId(cartRequest.getProductid(),cartMain.getId())){

            }else{
                return ResponseEntity.ok().body( new ApiResponse(false,"Cart item chứa product này ko còn tồn tại"));
            }

            switch (action){
                case "add":

                        CartItem cardItem =  cartItemRepository.findOneCartItemByProductIdAndCartId(cartRequest.getProductid(),cartMain.getId());
                        cardItem.setQuantity(cartRequest.getQuantity());
                        Product product = productRepository.findById(cardItem.getProduct().getId()).get();
                        cardItem.setTotalPrice(cardItem.getQuantity()*product.getPrice());
                        cartItemRepository.save(cardItem);
                        List<CartItem> cartList = cartItemRepository.findAllByCartId(cartMain.getId());
                        double totalPriceCart=0;
                        for (CartItem cartItem : cartList) {
                            double temp = cartItem.getTotalPrice();
                            totalPriceCart = totalPriceCart+temp;
                        }
                        ListCartItemRespon listCartItemRespon = new ListCartItemRespon(cartList,totalPriceCart);
                        return ResponseEntity.ok().body(listCartItemRespon);
//                        return ResponseEntity.ok().body( cartItemRepository.save(cardItem));

                case "remove":
                    CartItem cardItemRemove =  cartItemRepository.findOneCartItemByProductIdAndCartId(cartRequest.getProductid(),cartMain.getId());
                    cartItemRepository.deleteById(cardItemRemove.getId());
                    return  ResponseEntity.ok().body(new ApiResponse(true,"Xóa thành công"));

                default:
                    return  ResponseEntity.ok().body(new ApiResponse(false,"Lôi rồi b oi"));


            }

//
        }catch (Exception e){
            return  ResponseEntity.ok().body(new ApiResponse(false,"Lôi rồi b oi"+e.toString()));
        }
    }

    @GetMapping("/getListCardItem") // get list card item cua 1 user
    public ResponseEntity<?> GetListCartItemOfUser(@CurrentUser UserPrincipal currentUser){

        try{
            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
                return ResponseEntity.ok().body(new ApiResponse(false,"Bạn phải đăng nhập để xóa cart"));
            }
            User user = userRepository.findOnedById(currentUser.getId());
            System.err.println("user là"+user);
            Cart cartMain = user.getCart();
            if(user.getCart()==null){
                return ResponseEntity.ok().body(new ApiResponse(false,"Bạn chưa có sản phẩm nào ở giỏ hàng , hãy đi shopping nào"));
            }
            List<CartItem> cartList = cartItemRepository.findAllByCartId(cartMain.getId());
            double totalPriceCart=0;
            for (CartItem cartItem : cartList) {
                double temp = cartItem.getTotalPrice();
                totalPriceCart = totalPriceCart+temp;
            }
            ListCartItemRespon listCartItemRespon = new ListCartItemRespon(cartList,totalPriceCart);
            return ResponseEntity.ok().body(listCartItemRespon);


        }catch (Exception e){
            return  ResponseEntity.ok().body(new ApiResponse(false,"Lôi rồi b oi"+e.toString()));
        }

    }



}
