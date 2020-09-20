package com.tw.tdd.lockerrobot.domain;

import com.tw.tdd.lockerrobot.enums.LockerTypeEnum;
import com.tw.tdd.lockerrobot.exceptions.NoAvailableSpaceException;
import com.tw.tdd.lockerrobot.exceptions.TypeNotMatchException;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class Locker {

    private LockerTypeEnum type;
    private Integer capacity;
    private HashMap<Ticket, Bag> bagTicketMap = new HashMap<>();

    public Locker(LockerTypeEnum type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if (this.getAvailableSpaceNumber() == 0) {
            throw new NoAvailableSpaceException();
        } else {
            Ticket ticket = new Ticket(type);
            bagTicketMap.put(ticket, bag);
            return ticket;
        }
    }

    public Integer getAvailableSpaceNumber() {
        return this.capacity - bagTicketMap.size();
    }

    public Bag getBag(Ticket ticket) {
        if(ticket.getLockerType().equals(type)){
            return bagTicketMap.remove(ticket);
        }else{
            throw new TypeNotMatchException();
        }
    }
}
