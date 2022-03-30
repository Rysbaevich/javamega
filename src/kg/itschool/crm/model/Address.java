package kg.itschool.crm.model;

public final class Address extends BaseEntity {
    private String state;
    private String city;
    private String region;
    private String district;
    private String street;
    private String apartment;

    public Address() {
        super();
    }

    public Address(Long id, String state, String city, String region, String district, String street, String apartment) {
        super(id);
        this.state = state;
        this.city = city;
        this.region = region;
        this.district = district;
        this.street = street;
        this.apartment = apartment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", apartment='" + apartment + '\'' +
                ", id=" + id +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
