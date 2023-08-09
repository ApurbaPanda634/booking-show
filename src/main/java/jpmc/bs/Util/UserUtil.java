package jpmc.bs.Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class UserUtil {

    //private static final Logger log = LogManager.getLogger(UserUtil.class);

    final String TICKET_PREFIX="TKT";

    /*
     * Method: generateTicketNumber
     * @return: String (generated ticket number)
     * Method to generate Unique Ticket number
     */
    public String generateTicketNumber()
    {
        //log.info("Generating unique ticket number");
        long generatedTicket = (long) (Math.random()*Math.pow(10,10));
        return String.valueOf(TICKET_PREFIX+generatedTicket);
    }

    /*
     * Method: checkIfExpired
     * @param: dateTime
     * @return: boolean (if expired)
     * Method to check if cancelWindow expired
     */
    public boolean checkIfExpired(String dateTime)
    {
        //log.info("Check if cancellation time expired");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime);
        return LocalDateTime.now().isBefore(localDateTime);
    }


    /*
     * Method: getExpiryTime
     * @param: minutes
     * @return: String (string value of localDateTime)
     * Method to add cancelWindowExpiry to current time
     */
    public String getExpiryTime(int minutes)
    {
        //log.info("get expiry time");
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(minutes);
        return expirationTime.toString();
    }
}
