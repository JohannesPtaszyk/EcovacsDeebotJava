package dev.pott.sucks.api;

public final class EcovacsApiConfiguration {
    private final String deviceId;
    private final String username;
    private final String password;
    private final String continent;
    private final String country;
    private final String language;
    private final String clientKey;
    private final String clientSecret;
    private final String authClientKey;
    private final String authClientSecret;

    public EcovacsApiConfiguration(String deviceId, String username, String password, String continent, String country,
            String language, String clientKey, String clientSecret, String authClientKey, String authClientSecret) {
        this.deviceId = deviceId;
        this.username = username;
        this.password = password;
        this.continent = continent;
        this.country = country;
        this.language = language;
        this.clientKey = clientKey;
        this.clientSecret = clientSecret;
        this.authClientKey = authClientKey;
        this.authClientSecret = authClientSecret;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getResource() {
        return deviceId.substring(0, 8);
    }

    public String getAuthOpenId() {
        return "global";
    }

    public String getTimeZone() {
        return "GMT-8";
    }

    public String getRealm() {
        return "ecouser.net";
    }

    public String getPortalAUthRequestWith() {
        return "users";
    }

    public String getOrg() {
        return "ECOWW";
    }

    public String getEdition() {
        return "ECOGLOBLE";
    }

    public String getBizType() {
        return "ECOVACS_IOT";
    }

    public String getChannel() {
        return "google_play";
    }

    public String getAppCode() {
        return "global_e";
    }

    public String getAppVersion() {
        return "1.6.3";
    }

    public String getDeviceType() {
        return "1";
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getAuthClientKey() {
        return authClientKey;
    }

    public String getAuthClientSecret() {
        return authClientSecret;
    }
}
