package com.existmaster.study;

/**
 * Created by existmaster on 2015. 12. 6..
 */
public class URLParser extends AURLParser{

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
        int url_end_index = urlModel.getUrl().indexOf(":");

        urlModel.setProtocol(NetworkProtocol.getProtocol(
                urlModel.getUrl().substring(url_start_index, url_end_index)));
    }

    private void parseHostAndPort(URLModel urlModel) {
        int host_start_index = urlModel.getUrl().indexOf("//");
        int host_end_index = urlModel.getUrl().indexOf("/", host_start_index+2);

        String fullhost = urlModel.getUrl().substring(host_start_index+2, host_end_index);

        if (fullhost.contains(":")) {
            urlModel.setHost(fullhost.substring(0, fullhost.indexOf(":")));
            urlModel.setPort(fullhost.substring(fullhost.indexOf(":") + 1));
        } else {
            urlModel.setHost(fullhost);
            urlModel.setPort(urlModel.getProtocol().getDefaultPort());
        }

        if (host_start_index == -1) throw new AssertionError();
        if (urlModel.getHost().equals("")) throw new AssertionError("NotFoundHost");

    }

    private void parseSubDirectory(URLModel urlModel) {
        int host_start_index = urlModel.getUrl().indexOf("//") + 2;
        int host_end_index = urlModel.getUrl().indexOf("/", host_start_index);
        urlModel.setSub(urlModel.getUrl().substring(host_end_index + 1));
    }
}
