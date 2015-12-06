package com.existmaster.study;

/**
 * Created by existmaster on 2015. 12. 6..
 */
public abstract class AURLParser implements IURLParser<URLModel>{

    @Override
    public String getParserType() {
        return "URLParser";
    }

}
