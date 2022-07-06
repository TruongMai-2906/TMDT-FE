package com.example.cdwebbe.service;

import com.example.cdwebbe.DTO.UserDTO;
import com.example.cdwebbe.model.User;
import com.example.cdwebbe.payload.UserListResponse;
import org.springframework.data.domain.Pageable;

public interface UserService {
    String deleteUser(long id);
    User getUserById(Long id);
    User save(User u);

    /**
     *
     * @param pageable
     * @return
     */
    public UserListResponse findByPageable(Pageable pageable);

    /**
     * Xóa 1 user bằng id
     * @param id
     */
    public void delete(Long id);

    /**
     *
     * @return
     */
    public UserDTO findById(Long id);

    /**
     *
     * @param search
     * @param pageable
     * @return
     */
    public UserListResponse findBySearch(String search, Pageable pageable);
}
