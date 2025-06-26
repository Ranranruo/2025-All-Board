package sms.allBoard.Common.Domain.Room.Repository;

import sms.allBoard.Common.Domain.Room.Model.Room;

import java.util.Optional;
import java.util.Set;

public interface RoomRepository {
    void save(Room room);
    Set<Room> findAll();
    Optional<Room> findById(String id);
}
