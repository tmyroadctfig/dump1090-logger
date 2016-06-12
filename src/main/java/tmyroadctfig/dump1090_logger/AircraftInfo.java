package tmyroadctfig.dump1090_logger;

import java.util.Locale;
import java.util.Objects;

/**
 * <p>Hold information for a single aircraft.</p>
 *
 * <p>Parsed from JSON, e.g.
 * <pre>{"hex":"7c6d28", "squawk":"4356", "flight":"VOZ9001 ", "lat":-34.0, "lon":150.0, "validposition":1, "altitude":8000,  "vert_rate":-960,
 * "track":61, "validtrack":1,"speed":286, "messages":533, "seen":0}</pre></p>
 */
public class AircraftInfo
{
    /**
     * The ICAO24 hex address.
     */
    public String hex;

    /**
     * The squawk frequency.
     */
    public String squawk;

    /**
     * The flight number.
     */
    public String flight;

    /**
     * The latitude.
     */
    public double lat;

    /**
     * The longitude.
     */
    public double lon;

    /**
     * The altitude (in feet).
     */
    public int altitude;

    public int vert_rate;

    public int track;

    public int validtrack;

    /**
     * The speed (in knots?)
     */
    public int speed;

    /**
     * The number of messages received for this aircraft.
     */
    public int messages;

    /**
     * The number of seconds since a message was received from this aircraft.
     */
    public int seen;

    @Override
    public String toString()
    {
        return String.format(Locale.ROOT, "icao24:'%s' squawk:'%s' flight:'%s' lat:%.5f lon:%.5f altitude:%d vert_rate:%d track:%d validtrack:%b speed:%d messages:%d seen:%d",
            hex, squawk, Objects.toString(flight, "").trim(), lat, lon, altitude, vert_rate, track, validtrack == 1, speed, messages, seen);
    }
}
