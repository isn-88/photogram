package su.itpro.photogram.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import su.itpro.photogram.exception.auth.ForbiddenException;
import su.itpro.photogram.model.dto.AccountDto;
import su.itpro.photogram.service.AccountService;
import su.itpro.photogram.service.impl.AccountServiceImpl;
import su.itpro.photogram.servlet.enums.PathSelector;
import su.itpro.photogram.util.ServletUtil;

@WebFilter("/*")
public class AuthFilter implements Filter {

  private final List<String> resourcePath;
  private final List<String> publicPaths;
  private final List<String> userPaths;
  private final List<String> adminPaths;
  private final List<String> moderatorPaths;


  public AuthFilter() {
    resourcePath = List.of("/favicon.ico", "/css", "/img", "/js", "/webfonts");
    publicPaths = List.of(PathSelector.LOGIN.get(), PathSelector.REGISTRATION.get(),
                          PathSelector.LOGOUT.get()
    );
    userPaths = List.of("/account", "/comment", "/complaint", PathSelector.EDIT.get(),
                        PathSelector.HOME.get(), "/icon", "/image", "/password",
                        PathSelector.POST.get(), "/profile", "/search",
                        "/subscribe", "/unsubscribe", "/like"
    );
    adminPaths = List.of(PathSelector.ADMIN.get());
    moderatorPaths = List.of(PathSelector.MODERATOR.get(), PathSelector.POST.get(),
                             "/icon", "/image"
    );
  }

  private final AccountService accountService = AccountServiceImpl.getInstance();

  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    HttpSession session = req.getSession(false);

    String servletPath = req.getServletPath();

    if (servletPath.equals("/")) {
      filterChain.doFilter(servletRequest, servletResponse);
    }

    if (session == null) {
      if (isPublicPath(req) || isResourcePath(req)) {
        filterChain.doFilter(servletRequest, servletResponse);
      } else {
        resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.LOGIN));
      }
    } else {
      AccountDto account = (AccountDto) session.getAttribute("account");
      if (account != null && accountService.checkStatus(account.id())) {
        if (isPublicPath(req) || isResourcePath(req)) {
          filterChain.doFilter(servletRequest, servletResponse);
        } else {
          switch (account.role()) {
            case USER -> {
              if (isUserPath(req)) {
                filterChain.doFilter(servletRequest, servletResponse);
              } else {
                throw new ForbiddenException("Forbidden");
              }
            }

            case ADMIN -> {
              if (isAdminPath(req)) {
                filterChain.doFilter(servletRequest, servletResponse);
              } else {
                throw new ForbiddenException("Forbidden");
              }
            }

            case MODERATOR -> {
              if (isModeratorPath(req)) {
                filterChain.doFilter(servletRequest, servletResponse);
              } else {
                throw new ForbiddenException("Forbidden");
              }
            }

            default -> {
              throw new IllegalStateException();
            }
          }
        }
      } else {
        if (isPublicPath(req) || isResourcePath(req)) {
          filterChain.doFilter(servletRequest, servletResponse);
        } else {
          resp.sendRedirect(ServletUtil.getServletPath(req.getContextPath(), PathSelector.LOGIN));
        }
      }
    }
  }

  private boolean isResourcePath(HttpServletRequest req) {
    return findPath(req.getServletPath(), resourcePath);
  }

  private boolean isPublicPath(HttpServletRequest req) {
    return findPath(req.getServletPath(), publicPaths);
  }

  private boolean isUserPath(HttpServletRequest req) {
    return findPath(req.getServletPath(), userPaths);
  }

  private boolean isAdminPath(HttpServletRequest req) {
    return findPath(req.getServletPath(), adminPaths);
  }

  private boolean isModeratorPath(HttpServletRequest req) {
    return findPath(req.getServletPath(), moderatorPaths);
  }

  private boolean findPath(String servletPath, List<String> paths) {
    for (String path : paths) {
      if (servletPath.startsWith(path)) {
        return true;
      }
    }
    return false;
  }
}
