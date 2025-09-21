package com.example.studyroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studyroom.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
