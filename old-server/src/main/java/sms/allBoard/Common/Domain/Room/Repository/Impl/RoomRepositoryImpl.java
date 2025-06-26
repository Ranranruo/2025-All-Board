package sms.allBoard.Common.Domain.Room.Repository.Impl;

import sms.allBoard.Common.Domain.Room.Model.Room;
import sms.allBoard.Common.Domain.Room.Repository.RoomRepository;

import java.util.Optional;
import java.util.Set;

public class RoomRepositoryImpl implements RoomRepository {
    @Override
    public void save(Room room) {

    }

    @Override
    public Set<Room> findAll() {
        return Set.of();
    }

    @Override
    public Optional<Room> findById(String id) {
        return null;
    }
}
