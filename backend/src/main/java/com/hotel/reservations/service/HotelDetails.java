import java.util.Date;
import java.util.List;

import com.hotel.reservations.interfaces.IHotelDetails;
import com.hotel.reservations.entity.Room;

public class HotelDetails implements IHotelDetails {

    String getHotelDetails();

    List<Room> getRooms();

    String getRoomDetails(int roomId);

    boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate);
    
}
