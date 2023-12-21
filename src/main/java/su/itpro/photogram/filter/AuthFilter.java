package su.itpro.photogram.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebFilter({"/account/*", "/comment/*", "/edit", "/home", "/home/*", "/icon/*", "/image/*",
            "/password", "/post/*", "/profile/*", "/subscribe/*", "/unsubscribe/*"})
public class AuthFilter implements Filter {

  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    HttpSession session = req.getSession(false);
    if (session != null) {
      AccountDto account = (AccountDto) session.getAttribute("account");
      if (account != null && accountService.checkStatus(account.id())) {
        filterChain.doFilter(servletRequest, servletResponse);
        return;
      }
    }
    resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.LOGIN));
  }
}
