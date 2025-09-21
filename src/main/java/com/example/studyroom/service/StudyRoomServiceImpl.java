package com.example.studyroom.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import com.example.studyroom.dto.req.StudyRoomRequestDtoAdmin;
import com.example.studyroom.dto.req.StudyRoomRequestDtoUser;
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
			if (roomRepository.existsByRoomNameAndLocation(req.getRoomName(), req.getLocation())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 등록된 방입니다");
			}
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

	@Transactional
	@Override
	public StudyRoomResponseDto reservationsRoom(StudyRoomRequestDtoUser req, HttpServletRequest request) {

		Object role = request.getAttribute("Role");
		if (role.equals("ROLE_USER")) {

			if (reservationRepository.existsByRoomIdAndStartAtAndEndAt(req.getRoomId(), req.getStartAt(), req.getEndAt())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 예약된 시간대입니다");
			}

			Reservation reservation = Reservation.builder()
				.roomId(req.getRoomId())
				.startAt(req.getStartAt())
				.endAt(req.getEndAt())
				.build();

			if (reservation.isValid(req.getStartAt(), req.getEndAt())) {
				Reservation savedReservation = reservationRepository.save(reservation);
				return StudyRoomResponseDto.from(savedReservation);
			}

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "시작시간과 종료시간을 다시 한번 확인해주세요");

		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "유저만 접근 가능합니다.");
		}
	}
}
