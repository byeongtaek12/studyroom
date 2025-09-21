package com.example.studyroom.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.studyroom.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	boolean existsByRoomNameAndLocation(String roomName, String location);
}
