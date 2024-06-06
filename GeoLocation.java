/**
 * Represents a geographical location with city, state, and coordinates (latitude and longitude).
 * This class implements the Comparable interface to allow sorting by city name.
 */
public class GeoLocation implements Comparable<GeoLocation> {
    private String city;
    private String state;
    private double latitude;
    private double longitude;

    /**
     * Constructs a new GeoLocation object with specified city, state, latitude, and longitude.
     * 
     * @param city the city name
     * @param state the state name
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     */
    public GeoLocation(String city, String state, double latitude, double longitude) {
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the city name of this location.
     * 
     * @return the city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the state name of this location.
     * 
     * @return the state name
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the latitude of this location.
     * 
     * @return the latitude coordinate
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of this location.
     * 
     * @return the longitude coordinate
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the city name of this location.
     * 
     * @param city the city name to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state name of this location.
     * 
     * @param state the state name to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the latitude of this location.
     * 
     * @param latitude the latitude coordinate to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets the longitude of this location.
     * 
     * @param longitude the longitude coordinate to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Compares this location with another GeoLocation based on the city name.
     * 
     * @param other the GeoLocation to compare to
     * @return a negative integer, zero, or a positive integer as this location is less than, 
     *         equal to, or greater than the specified location
     */
    @Override
    public int compareTo(GeoLocation other) {
        return this.city.compareTo(other.city);
    }

    /**
     * Returns a string representation of this location formatted as city, state, latitude, and longitude.
     * 
     * @return a formatted string representing this location
     */
    @Override
    public String toString() {
        return String.format("%-25s %-6s %9.5f %15.5f", city, state, latitude, longitude);
    }
}