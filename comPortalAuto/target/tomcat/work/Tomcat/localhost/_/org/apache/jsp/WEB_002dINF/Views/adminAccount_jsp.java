/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-07-11 08:06:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.Views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminAccount_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Личный кабинет админа</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<p><b>Данные пользователя</b></p>\r\n");
      out.write("<form action=\"updateUserData\" enctype=\"text/plain\" method=\"get\" name=\"Admin\">\r\n");
      out.write("    <p>ФИО:&nbsp;<input maxlength=\"100\" name=\"fio\" type=\"text\" required value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fioSub}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></p>\r\n");
      out.write("    <p>Адрес:&nbsp;<input maxlength=\"100\" name=\"addr\" type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${addrSub}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></p>\r\n");
      out.write("    <p>Моб. телефон:&nbsp;<input maxlength=\"11\" name=\"phone\" required type=\"text\" value=\"8");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${phoneSub}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></p>\r\n");
      out.write("    <p>Email:&nbsp;<input maxlength=\"50\" name=\"email\" type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${emailSub}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></p>\r\n");
      out.write("    <p>&nbsp; <input type=\"submit\" value=\"Обновить\"></p>\r\n");
      out.write("</form>\r\n");
      out.write("<form id=\"promo\" action=\"showPromoCreation\" enctype=\"text/plain\" method=\"get\">\r\n");
      out.write("    <p>&nbsp; <input type=\"submit\" value=\"Создать объявление\"></p>\r\n");
      out.write("</form>\r\n");
      out.write("<form id=\"rootDelete\" action=\"deletePromo\" enctype=\"text/plain\" method=\"post\">\r\n");
      out.write("    <p>Номер машины:&nbsp;<input maxlength=\"8\" name=\"num\" type=\"text\" required-pattern=\"^[a-zA-Z0-9]+$\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${info2}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </p>\r\n");
      out.write("    <p>&nbsp; <input type=\"submit\" value=\"Удалить объявление\"></p>\r\n");
      out.write("</form>\r\n");
      out.write("<form id=\"rootBlock\" action=\"blockUser\" enctype=\"text/plain\" method=\"post\">\r\n");
      out.write("    <p>Логин пользователя:&nbsp;<input maxlength=\"50\" name=\"login\" type=\"text\" placeholder=\"кого блокируем?\" required-pattern=\"^[a-zA-Z0-9]+$\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${info}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</p>\r\n");
      out.write("    <p>&nbsp; <input type=\"submit\" value=\"Забл/разбл пользователя\"></p>\r\n");
      out.write("</form>\r\n");
      out.write("<form id=\"rootLogOut\" action=\"logOut\" enctype=\"text/plain\" method=\"get\">\r\n");
      out.write("    <p>&nbsp; <input type=\"submit\" value=\"Выйти\"></p>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
