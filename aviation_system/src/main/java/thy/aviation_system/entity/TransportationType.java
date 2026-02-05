package thy.aviation_system.entity;

public enum TransportationType {
    FLIGHT,
    BUS,
    SUBWAY,
    UBER;

    public boolean isGroundTransport() {
        return this != FLIGHT;
    }

}
