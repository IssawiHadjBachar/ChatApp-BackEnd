package com.chat.Repositories;

import com.chat.Entities._user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<_user, Long> {
    _user findByUsername(String username);
}
