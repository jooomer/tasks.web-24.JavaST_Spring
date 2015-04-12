/**
 * 
 */
package ua.store.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.store.controller.user.UsersController;

/**
 * @author Sergey
 *
 */
@SuppressWarnings("serial")
public class UserTableTag extends BodyTagSupport {
	
	private static final Logger logger = LogManager.getLogger(UserTableTag.class);

	private String head;
	private int rows;
	private UserList userList;

	public void setHead(String head) {
		this.head = head;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public int doStartTag() throws JspException {

		logger.debug("doStartTag() - begin");

		// get list of all users
		userList = (UserList) pageContext.getSession().getAttribute("userList");

		logger.debug("userList is gotten.");

		// send list of users to a browser
		try {
			JspWriter out = pageContext.getOut();
			out.write("<table style='width:95%'><colgroup span='2' title='title' />");
			out.write("<caption>" + "List of users" + "</caption>");
			out.write("<thead><tr>"
					+ "<th scope='col'>" + "Id" + "</th>"
					+ "<th scope='col'>" + "Type" + "</th>"
					+ "<th scope='col'>" + "userName" + "</th>"
					+ "<th scope='col'>" + "First name" + "</th>"
					+ "<th scope='col'>" + "Last name" + "</th>"
					+ "<th scope='col'>" + "Email" + "</th>"
					+ "<th scope='col'>" + "Phone" + "</th>"
					+ "<th scope='col'>" + "Address" + "</th>"
					+ "<th scope='col'>" + "Comments" + "</th>"
					+ "<th scope='col'>" + "Black list"	+ "</tr></thead>");
			out.write("<tbody><tr><td>");
		} catch (IOException e) {
			logger.error("An error occurred while writing by JspWriter", e);
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doAfterBody() throws JspException {
		if (rows-- > 1) {
			try {
				pageContext.getOut().write("</td></tr><tr><td>");
			} catch (IOException e) {
				throw new JspException(e.getMessage());
			}
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("</td></tr></tbody></table>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new JspException(e.getMessage());
		} finally {
			userList.resetIterator();
		}
		return EVAL_PAGE;
	}
}
