import com.existmaster.study.NetworkProtocol;
import com.existmaster.study.URLModel;
import com.existmaster.study.URLParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by existmaster on 2015. 12. 6..
 */
public class UrlParserTest {

    public static final String NORMAL_URL = "http://www.naver.com:8080/subdirectory";
    public static final String DEFAULT_PORT_URL = "http://www.naver.com/subdirectory";
    private URLParser urlParser;
    private URLModel urlModel;

    @Before
    public void setUp() throws Exception {
        urlParser = new URLParser();

    }

    @Test
    public void Test_ParseFunction(){
        urlModel = urlParser.parseUrl(NORMAL_URL);
        assertEquals(NetworkProtocol.HTTP, urlModel.getProtocol());
        assertEquals("www.naver.com", urlModel.getHost());
        assertEquals("8080", urlModel.getPort());
        assertEquals("subdirectory", urlModel.getSub());
    }

    @Test
    public void substring() {
        String s = "abcdaefghc";
        System.out.println(s.indexOf("cd"));
    }

    @Test
    public void DefaultPort(){
        urlModel = urlParser.parseUrl(DEFAULT_PORT_URL);
        assertEquals("www.naver.com", urlModel.getHost());
        assertEquals("80", urlModel.getPort());

    }
    @Test(expected = AssertionError.class)
    public void URLCheck_WrongProtocol_ExpectedException(){
        urlModel = urlParser.parseUrl("http2://www.naver.com:8080/subdir");
    }

}
