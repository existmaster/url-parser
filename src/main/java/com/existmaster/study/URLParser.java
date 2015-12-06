package com.existmaster.study;

/**
 * Created by existmaster on 2015. 12. 6..
 */
public class URLParser extends AURLParser{

    public static final String PROTOCOL_DIVIDER = "://";
    public static final String HOST_DEVIDER = "/";
    public static final String PORT_DEVIDER = ":";

    @Override
    public URLModel parseUrl(String url) {
        URLModel urlModel = new URLModel(url);
        parseProtocol(urlModel);
        parseHostAndPort(urlModel);
        parseSubDirectory(urlModel);
        return urlModel;
    }

    private void parseProtocol(URLModel urlModel) {
        int url_start_index = 0;
        int url_end_index = urlModel.getUrl().indexOf(PROTOCOL_DIVIDER);

        if (url_end_index == -1) throw new AssertionError();

        urlModel.setProtocol(NetworkProtocol.getProtocol(
                urlModel.getUrl().substring(url_start_index, url_end_index)));
    }

    private void parseHostAndPort(URLModel urlModel) {
        int host_start_index = urlModel.getUrl().indexOf(PROTOCOL_DIVIDER);
        int host_end_index = urlModel.getUrl().indexOf(HOST_DEVIDER, host_start_index+3);

        String fullhost = urlModel.getUrl().substring(host_start_index + PROTOCOL_DIVIDER.length(), host_end_index);

        if (fullhost.contains(PORT_DEVIDER)) {
            urlModel.setHost(fullhost.substring(0, fullhost.indexOf(PORT_DEVIDER)));
            urlModel.setPort(fullhost.substring(fullhost.indexOf(PORT_DEVIDER) + 1));
        } else {
            urlModel.setHost(fullhost);
            urlModel.setPort(urlModel.getProtocol().getDefaultPort());
        }

        if (host_start_index == -1) throw new AssertionError();
        if (urlModel.getHost().equals("")) throw new AssertionError("NotFoundHost");

    }

    private void parseSubDirectory(URLModel urlModel) {
        int host_start_index = urlModel.getUrl().indexOf(PROTOCOL_DIVIDER) + PROTOCOL_DIVIDER.length();
        int host_end_index = urlModel.getUrl().indexOf(HOST_DEVIDER, host_start_index);
        urlModel.setSub(urlModel.getUrl().substring(host_end_index + 1));
    }
}
