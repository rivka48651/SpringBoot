package com.mysite.myspringboot.dal;
import com.mysite.myspringboot.model.Room;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room,Integer>{
 Room findByRoomNumber(int roomNumber);
}
