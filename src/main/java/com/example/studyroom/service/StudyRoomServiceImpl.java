package com.example.studyroom.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import com.example.studyroom.dto.req.StudyRoomRequestDtoAdmin;
import com.example.studyroom.dto.res.StudyRoomResponseDto;
import com.example.studyroom.dto.res.StudyRoomResponseDtoGet;
import com.example.studyroom.entity.Reservation;
import com.example.studyroom.entity.Room;
import com.example.studyroom.repository.ReservationRepository;
import com.example.studyroom.repository.RoomRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyRoomServiceImpl implements StudyRoomService{
	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;

	@Transactional
	@Override
	public StudyRoomResponseDto registrationRoom(StudyRoomRequestDtoAdmin req, HttpServletRequest request) {

		Object role = request.getAttribute("Role");
		if (role.equals("ROLE_ADMIN")) {
			Room room = Room.builder()
				.roomName(req.getRoomName())
				.location(req.getLocation())
				.personnel(req.getPersonnel())
				.build();
			Room savedRoom = roomRepository.save(room);
			return StudyRoomResponseDto.from(savedRoom);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "관리자만 접근 가능합니다.");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<StudyRoomResponseDtoGet> checkRoom(LocalDate date) {

		LocalDateTime start = date.atStartOfDay();
		LocalDateTime end = start.plusDays(1);
		List<Reservation> reservationList = reservationRepository.findByDate(start, end);

		return reservationList.stream()
			.map(StudyRoomResponseDtoGet::from)
			.toList();
	}
}
