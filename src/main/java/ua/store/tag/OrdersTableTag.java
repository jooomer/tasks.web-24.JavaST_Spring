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

import ua.store.controller.order.OrderController;
import ua.store.tag.OrdersList;

/**
 * @author Sergey
 *
 */
@SuppressWarnings("serial")
public class OrdersTableTag extends BodyTagSupport {

	private static final Logger logger = LogManager
			.getLogger(OrdersTableTag.class);

	private String head;
	private int rows;
	private OrdersList ordersList;

	public void setHead(String head) {
		this.head = head;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public int doStartTag() throws JspException {

			logger.debug("doStartTag() started");

		// get list of all products
		ordersList = (OrdersList) pageContext.getSession().getAttribute("ordersList");

		// send list of products to a browser
		try {
			JspWriter out = pageContext.getOut();
			out.write("<table style='width:95%'><colgroup span='2' title='title' />");
			out.write("<caption>" + "List of products" + "</caption>");
			out.write("<thead><tr>"
					+ "<th scope='col'>" + "orderId" + "</th>"
					+ "<th scope='col'>" + "Date" + "</th>"
					+ "<th scope='col'>" + "Amount" + "</th>"
					+ "<th scope='col'>" + "Order status" + "</th>"
					+ "<th scope='col'>" + "Comments" + "</th>"
					+ "<th scope='col'>" + "Products"	+ "</tr></thead>");
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
			ordersList.resetIterator();
		}
		return EVAL_PAGE;
	}
}
