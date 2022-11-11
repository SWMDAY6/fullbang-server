package day6.fullbang.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.fullbang.domain.Room;
import day6.fullbang.dto.response.RoomResponseDto;
import day6.fullbang.service.RoomService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/room/{placeId}")
    public List<RoomResponseDto> readRoomsByPlaceId(
        @PathVariable("placeId") Long placeId,
        @RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkInDateTime) {

        List<Room> roomsByPlaceId = roomService.findRoomsByPlaceId(placeId);

        List<RoomResponseDto> responseRoomsDto = roomService.convertRoomIntoRoomDto(roomsByPlaceId,
            checkInDateTime);

        return responseRoomsDto;
    }

}
