package com.example.cdwebbe.controller;

import com.example.cdwebbe.DTO.UserDTO;
import com.example.cdwebbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserAdminController {
    @Autowired
    UserService userService;

    /**
     * Request:
     *  + Danh sách các thông tin của user;
     *  + Tìm kiếm theo name + mail + gender + address;
     * @Note: Cần phân lại quyền:
     *  + Hiện tại API này ai cũng có thể lấy được danh sách tất cả user;
     *      => Chỉ admin mới được quyền call API này, còn user không được phép;
     * @param limit
     * @return
     */
    @GetMapping("/user-list")
    public ResponseEntity<?> getUserList(
            @RequestParam(name = "page", required = false ,defaultValue = "1" ) int page,
            @RequestParam(name = "limit", required = false ,defaultValue = "10" ) int limit,
            @RequestParam (name = "search", required = false) String search){
        Pageable pageable= PageRequest.of(page -1, limit);
        if (search != null){
            return ResponseEntity.ok().body(userService.findBySearch(search, pageable));
        }
        return ResponseEntity.ok().body(userService.findByPageable(pageable));
    }

    /**
     * Request: Xóa các user;
     * @Note: Cần phân lại quyền:
     *  + Hiện tại API này chỉ cần là user là có thể xóa được các user;
     *      => Chỉ admin mới được quyền call API này, còn user không được phép;
     * @param ids
     * @return
     */
    @DeleteMapping("/user-list")
    public ResponseEntity<?>  deleteUserList(
            @RequestParam(name="id", required = false) Long []ids
    ){
        userService.delete(ids);
        return ResponseEntity.ok().build();
    }

    /**
     * Request: Xem chi tiết thông tin của user;
     * @Note: Cần phân lại quyền:
     *  + Hiện tại API này ai cũng có thể lấy được thông tin chi tiết của user;
     *       => Chỉ admin mới được quyền call API này, còn user không được phép;
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetail(@PathVariable(name = "id", required = false) Long id){
        UserDTO userDTO=userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }
}
