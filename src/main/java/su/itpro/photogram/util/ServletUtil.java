package su.itpro.photogram.util;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.servlet.enums.PageSelector;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.converter.DateConverter;

public class ServletUtil {

  private static final String ACCOUNT_ATTRIBUTE_NAME = "account";
  private static final String PREFIX_JSP_PATH = "/WEB-INF/jsp/";
  private static final String JSP_PATH_PATTERN = "%s%s.jsp";
  private static final String SERVLET_PATH_PATTERN = "%s/%s";
  private static final String SERVLET_PARAM_PATTERN = "%s/%s/%s";

  private ServletUtil() {
  }

  public static AccountDto getAccountFromSession(HttpServletRequest req) {
    return (AccountDto) req.getSession().getAttribute(ACCOUNT_ATTRIBUTE_NAME);
  }

  public static String getJspPage(PageSelector selector) {
    return JSP_PATH_PATTERN.formatted(PREFIX_JSP_PATH, selector.get());
  }

  public static String getServletPath(String contextPath, PathSelector selector) {
    return SERVLET_PATH_PATTERN.formatted(contextPath, selector.get());
  }

  public static String getServletPath(String contextPath, PathSelector selector, String param) {
    return SERVLET_PARAM_PATTERN.formatted(contextPath, selector.get(), param);
  }

  public static String getServletPath(String contextPath, PathSelector selector, String param,
                                      Map<String, String> queryParams) {
    String servletPath = getServletPath(contextPath, selector, param);
    return (Objects.isNull(queryParams) || queryParams.isEmpty())
           ? servletPath
           : servletPath + '?' + queryParams.entrySet().stream()
               .map(e -> e.getKey() + '=' + e.getValue())
               .collect(joining("&"));
  }

  public static String variableOfQueryPath(String path) {
    if (valueOrNull(path) == null) {
      return null;
    }
    return path.startsWith("/") ? path.substring(1) : path;
  }

  public static String getValue(HttpServletRequest request, String name) {
    String inputValue = request.getParameter(name);
    return (valueOrNull(inputValue) != null) ? inputValue : null;
  }

  public static boolean getBoolean(HttpServletRequest request, String name) {
    return Boolean.parseBoolean(request.getParameter(name));
  }

  public static String getValueAndStrip(HttpServletRequest request, String name) {
    return stringStrip(getValue(request, name));
  }

  public static String getPreparedPhone(HttpServletRequest request, String name) {
    return replaceOrNull("\\D", "", stringStrip(getValue(request, name)));

  }

  public static LocalDate parseDate(HttpServletRequest request, String name) {
    String inputValue = valueOrNull(request.getParameter(name));
    return (inputValue != null) ? DateConverter.parseDate(inputValue) : null;
  }

  public static String valueOrNull(String value) {
    if (value != null && !value.isBlank()) {
      return value;
    }
    return null;
  }

  public static void writeDate(InputStream inputStream, HttpServletResponse resp, int bufferSize) {
    try (inputStream; OutputStream outputStream = resp.getOutputStream()) {
      byte[] buffer = new byte[bufferSize];
      int read;
      while ((read = inputStream.read(buffer)) > 0) {
        outputStream.write(buffer, 0, read);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static String stringStrip(String value) {
    if (value == null) {
      return null;
    }
    return value.strip();
  }

  private static String replaceOrNull(String regex, String replacement, String value) {
    if (value == null) {
      return null;
    }
    return value.replaceAll(regex, replacement);
  }
}
