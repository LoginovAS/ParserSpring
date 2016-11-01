<%@ page import="java.util.*" %>

<html>
<body>
<h1>Selected records:</h1>
<p>

<%
  List records = (List) request.getAttribute("records");
  Iterator it = records.iterator();
  while(it.hasNext()) {
    out.print("<br>" + it.next());
  }
%>

</body>
</html>