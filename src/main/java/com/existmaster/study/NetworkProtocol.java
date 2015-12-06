package com.existmaster.study;

/**
 * Created by existmaster on 2015. 12. 6..
 */
public enum NetworkProtocol {
    HTTP("HTTP", "80"), FTP("FTP", "20"), SMTP("SMTP", "25"),
    SNMP("SNMP", "161"), HTTPS("HTTPS", "443");

    private final String protocol;
    private final String defaultPort;

    NetworkProtocol(String protocol, String defaultPort) {
        this.protocol = protocol.toUpperCase();
        this.defaultPort = defaultPort;
    }

    public String getDefaultPort(){
        return this.defaultPort;
    }

    public static NetworkProtocol getProtocol(String protocol){
        switch(protocol.toUpperCase()){
            case "HTTP" : return HTTP;
            case "FTP" : return FTP;
            case "SMTP" : return SMTP;
            case "SNMP" : return SNMP;
            case "HTTPS" : return HTTPS;
            default: throw new AssertionError("Unknown Protocol: " + protocol);
        }
    }
}
