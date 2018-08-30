package com.myccnice.practice.manual.spi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ServiceConfigurationError;

public class FileUtil {

    public static Enumeration<URL> getConfig(String fullName, ClassLoader loader) {
        Enumeration<URL> configs = null;
        try {
            if (loader == null) {
                configs = ClassLoader.getSystemResources(fullName);
            } else {
                configs = loader.getResources(fullName);
            }
        } catch (IOException x) {
            throw new ServiceConfigurationError("");
        }
        return configs;
    }

    public static List<String> readLines(URL u) {
        InputStream in = null;
        BufferedReader r = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            in = u.openStream();
            r = new BufferedReader(new InputStreamReader(in, "utf-8"));
            int lc = 1;
            while ((lc = parseLine( r, lc, names)) >= 0);
        } catch (IOException x) {
            throw new ServiceConfigurationError("");
        } finally {
            try {
                if (r != null) r.close();
                if (in != null) in.close();
            } catch (IOException y) {
                throw new ServiceConfigurationError("");
            }
        }
        return names;
    }

    private static int parseLine(BufferedReader r, int lc, List<String> names) throws IOException, ServiceConfigurationError {
        String ln = r.readLine();
        if (ln == null) {
            return -1;
        }
        int ci = ln.indexOf('#');
        if (ci >= 0) ln = ln.substring(0, ci);
        ln = ln.trim();
        int n = ln.length();
        if (n != 0) {
            if ((ln.indexOf(' ') >= 0) || (ln.indexOf('\t') >= 0))
                throw new ServiceConfigurationError("");
            int cp = ln.codePointAt(0);
            if (!Character.isJavaIdentifierStart(cp))
                throw new ServiceConfigurationError("");
            for (int i = Character.charCount(cp); i < n; i += Character.charCount(cp)) {
                cp = ln.codePointAt(i);
                if (!Character.isJavaIdentifierPart(cp) && (cp != '.'))
                    throw new ServiceConfigurationError("");
            }
            if (!names.contains(ln))
                names.add(ln);
        }
        return lc + 1;
    }
}
