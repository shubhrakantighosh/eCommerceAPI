package com.masai.repository;

import com.masai.model.UserSession;
import com.masai.model.UserSessionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSessionRepository extends JpaRepository<UserSession,Integer> {

    @Query("select new com.masai.model.UserSessionDTO(userSession.uuid,userSession.user.userName,userSession.start,userSession.end) from UserSession userSession")
    List<UserSessionDTO>userSessions();

}
