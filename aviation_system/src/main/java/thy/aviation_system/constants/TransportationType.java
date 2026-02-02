package thy.aviation_system.constants;

public enum TransportationType {
    FLIGHT,
    BUS,
    SUBWAY,
    UBER;

    public boolean isGroundTransport() {
        return this != FLIGHT;
    }

}
