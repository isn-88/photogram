package su.itpro.photogram.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;


@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String language = ServletUtil.getValue(req, "lang");
    req.getSession().setAttribute("lang", language);

    Map<String, String> queryPathParam = new HashMap<>();
    if (Objects.nonNull(language)) {
      queryPathParam.put("lang", language);
    }
    String prevPage = req.getHeader("referer");
    String pagePath = (prevPage != null)
                      ? ServletUtil.getPathWithoutQueryParam(prevPage)
                      : ServletUtil.getServletPath(req.getContextPath(), PathSelector.LOGIN);

    resp.sendRedirect(pagePath + ServletUtil.getQueryPath(queryPathParam));
  }
}
