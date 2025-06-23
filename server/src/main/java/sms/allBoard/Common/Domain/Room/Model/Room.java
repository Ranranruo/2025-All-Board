package sms.allBoard.Common.Domain.Room.Model;

import sms.allBoard.Common.Domain.Room.Enum.RoomStatus;

public abstract class Room {
    private String uuid;
    private int playerCount;
    private int maxPlayers;
    private int minPlayers;
    private RoomStatus status;
}
