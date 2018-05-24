<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="id" class="java.lang.Integer" scope="request" />
<jsp:useBean id="name" class="java.lang.String" scope="request" />
<jsp:useBean id="date" class="java.util.Date" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BeanStartWebApp2</title>
</head>
<body>



<%=id %> + <%=name %> =  <%=date %> <br>


</body>
</html>