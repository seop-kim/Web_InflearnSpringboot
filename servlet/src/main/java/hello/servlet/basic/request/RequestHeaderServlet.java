package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        extracted(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);

    }


    private void extracted(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE-START ---");

        String method = request.getMethod();
        System.out.println("method = " + method);

        String protocol = request.getProtocol();
        System.out.println("protocol = " + protocol);

        String scheme = request.getScheme();
        System.out.println("scheme = " + scheme);

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        String queryString = request.getQueryString();
        System.out.println("queryString = " + queryString);

        boolean secure = request.isSecure();
        System.out.println("secure = " + secure);

        System.out.println("--- REQUEST-LINE-END ---");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers-start ---");

        // 옛날방법
        //        Enumeration<String> headerNames = request.getHeaderNames();
        //        while (headerNames.hasMoreElements()) {
        //            String headerName = headerNames.nextElement();
        //            System.out.println("headerName = " + headerName);
        //        }

        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + " : " + headerName));

        System.out.println("--- Headers-end ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        String serverName = request.getServerName();
        System.out.println("serverName = " + serverName);

        int serverPort = request.getServerPort();
        System.out.println("serverPort = " + serverPort);

        System.out.println("--- [Accept-Language 편의 조회] ---");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));

        Locale locale = request.getLocale();
        System.out.println("locale = " + locale);
        System.out.println();

        System.out.println("--- [Cookie 편의 조회] ---");

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println("cookie = " + cookie);
            }
        }

        System.out.println();

        System.out.println("--- [Content 편의 조회] ---");
        String contentType = request.getContentType();
        System.out.println("contentType = " + contentType);
        int contentLength = request.getContentLength();
        System.out.println("contentLength = " + contentLength);
        String characterEncoding = request.getCharacterEncoding();
        System.out.println("characterEncoding = " + characterEncoding);

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote info]");
        String remoteHost = request.getRemoteHost();
        System.out.println("remoteHost = " + remoteHost);
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr = " + remoteAddr);
        int remotePort = request.getRemotePort();
        System.out.println("remotePort = " + remotePort);
        System.out.println();

        System.out.println("[Local info]");
        String localName = request.getLocalName();
        System.out.println("localName = " + localName);
        String localAddr = request.getLocalAddr();
        System.out.println("localAddr = " + localAddr);
        int localPort = request.getLocalPort();
        System.out.println("localPort = " + localPort);
        System.out.println();
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
