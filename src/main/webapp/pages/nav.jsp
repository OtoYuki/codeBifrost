<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Nav</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="<%=request.getContextPath()%>/stylesheets/nav.css"
    />
  </head>
  <body>
    <!-- partial:index.partial.html -->
    <div class="tabs">
      <!-- TAB 1 -->
      <input type="radio" name="tab" id="tab-01" checked />
      <label for="tab-01">
        <div class="icon">
          <a href="../index.jsp">
            <span class="detail"></span>
          </a>
          <span class="glass"></span>
          <span class="background"></span>
        </div>
      </label>
      <!-- TAB 2 -->
      <input type="radio" name="tab" id="tab-02" />
      <label for="tab-02">
        <div class="icon">
          <span class="detail"></span>
          <span class="glass"></span>
          <span class="background"></span>
        </div>
      </label>
      <!-- TAB 3 -->
      <input type="radio" name="tab" id="tab-03" />
      <label for="tab-03">
        <div class="icon">
          <span class="detail"></span>
          <span class="glass"></span>
          <span class="background"></span>
        </div>
      </label>
      <!-- TAB 4 -->
      <input type="radio" name="tab" id="tab-04" />
      <label for="tab-04">
        <div class="icon">
          <span class="detail"></span>
          <span class="glass"></span>
          <span class="background"></span>
        </div>
      </label>
    </div>

    <!-- SVG -->
    <svg xmlns="http://www.w3.org/2000/svg" height="0" width="0">
      <clipPath id="path-icon-01">
        <path
          d="M25.182 26.601A4.844 4.844 0 0121.762 28H4.838C2.166 28 0 25.85 0 23.197V11.014A3.464 3.464 0 011.354 8.35l9.125-7.305a4.336 4.336 0 015.529-.1l9.338 7.405a3.462 3.462 0 011.254 2.663v12.195c0 1.273-.51 2.493-1.418 3.392z"
        />
      </clipPath>
      <clipPath id="path-detail-01">
        <rect width="11.657" height="2.743" x="7.2" y="20.914" rx="1.371" />
      </clipPath>
      <clipPath id="path-icon-02">
        <path
          d="M20.076 0H7.938C3.192 0 0 3.332 0 8.288v11.438C0 24.668 3.192 28 7.938 28h12.138C24.822 28 28 24.668 28 19.726V8.288C28 3.332 24.822 0 20.076 0z"
        />
      </clipPath>
      <clipPath id="path-detail-02">
        <path
          d="M21.325 11.52l-4.001 5.164a1.034 1.034 0 01-1.478.184l-3.84-3.012-3.46 4.475a1.035 1.035 0 01-1.908-.603 1.007 1.007 0 01.222-.639l4.136-5.335a1.035 1.035 0 011.465-.197l3.841 3.024 3.373-4.339a1.022 1.022 0 011.453-.209c.452.354.54 1.003.197 1.463v.025z"
        />
      </clipPath>
      <clipPath id="path-icon-03">
        <path
          d="M20.076 0H7.938C3.192 0 0 3.332 0 8.288v11.438C0 24.668 3.192 28 7.938 28h12.138C24.822 28 28 24.668 28 19.726V8.288C28 3.332 24.822 0 20.076 0z"
        />
      </clipPath>
      <clipPath id="path-detail-03">
        <path
          d="M6.19 13.833a1.62 1.62 0 001.615 1.615 1.62 1.62 0 001.615-1.615c0-.888-.727-1.613-1.615-1.613s-1.614.725-1.614 1.613zm7.644 1.615a1.62 1.62 0 01-1.615-1.614c0-.888.726-1.613 1.615-1.613.888 0 1.614.725 1.614 1.613a1.62 1.62 0 01-1.614 1.614zm6.027 0a1.62 1.62 0 01-1.614-1.614c0-.888.726-1.613 1.614-1.613.888 0 1.601.725 1.601 1.613s-.713 1.615-1.6 1.615z"
        />
      </clipPath>
      <clipPath id="path-icon-04">
        <path
          d="M28 14c0 7.732-6.268 14-14 14S0 21.732 0 14C0 6.27 6.268 0 14 0s14 6.27 14 14z"
        />
      </clipPath>
      <clipPath id="path-detail-04">
        <path
          d="M19.404 9.044l-2.268 7.168a.912.912 0 01-.616.617l-7.14 2.239c-.476.155-.938-.308-.784-.784l2.24-7.182a.935.935 0 01.616-.616l7.168-2.24a.629.629 0 01.784.798"
        />
      </clipPath>
    </svg>

    <!-- partial -->
  </body>
</html>
