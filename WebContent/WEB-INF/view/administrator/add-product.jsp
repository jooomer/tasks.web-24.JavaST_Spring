<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layouts/taglib.jsp"%>


<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="ua.store.properties.lang" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1>
	<fmt:message key="add_product.Add_new_product" />
</h1>
<br>

<form:form commandName="product">

	<pre>
	<fmt:message key="add_product.Product_type" />:			<form:select path="productType.name" cssClass="form-control">
															<form:option value="">-- SELECT TYPE OF PRODUCT --</form:option>
															<form:options items="${listOfProductTypes}" itemValue="name"
																			itemLabel="name" />
															</form:select>
															<form:errors path="productType.name" />
	

	<fmt:message key="add_product.Product_name" />:			<form:input path="name"/>
															<form:errors path="name" />

	<fmt:message key="add_product.Product_price" />: 			<form:input path="price"/>

	<fmt:message key="add_product.Quantity_in_stock" />: 	<form:input path="quantityInStock"/>

	<fmt:message key="add_product.Product_description" />:		
	<textarea name="description" rows="5" cols="70">
	</textarea>

	<input type="submit" value="<fmt:message key="add_product.Save_new_product_button" />" />
	</pre>

</form:form> 

