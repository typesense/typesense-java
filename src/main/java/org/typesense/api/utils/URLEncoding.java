package org.typesense.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Utility class for JavaScript compatible UTF-8 encoding and decoding.
 *
 * @see http://stackoverflow.com/questions/607176/java-equivalent-to-javascripts-encodeuricomponent-that-produces-identical-output
 * @author John Topley
 */
public class URLEncoding {
    /**
     * Encodes the passed String as UTF-8 using an algorithm that's compatible
     * with JavaScript's <code>encodeURIComponent</code> function. Returns
     * <code>null</code> if the String is <code>null</code>.
     *
     * @param s The String to be encoded
     * @return the encoded String
     */
    public static String encodeURIComponent(String s) {
        String result = null;

        try {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    /**
     * Private constructor to prevent this class from being instantiated.
     */
    private URLEncoding() {
        super();
    }
}
