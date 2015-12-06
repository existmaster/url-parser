package com.existmaster.study;

/**
 * Created by existmaster on 2015. 12. 6..
 */
public interface IURLParser<URL> extends IParser {
    public URL parseUrl(String url);
}
