package com.example.studyroom.service;

import org.springframework.stereotype.Service;

import com.example.studyroom.dto.req.StudyRoomRequestDtoAdmin;
import com.example.studyroom.dto.res.StudyRoomResponseDto;
import com.example.studyroom.entity.Reservation;
import com.example.studyroom.repository.ReservationRepository;
import com.example.studyroom.repository.RoomRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyRoomServiceImpl implements StudyRoomService{
	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;


}
