<%@ page language="java" %>

<html>
<title>ESK363 log records</title>
<body>
    <form method="POST" action="getRecords.do">
        Set date range:
        <input name="startDate">
        <input name="endDate">
        <button name="submit">Submit</button>
    </form>
</body>
<html>