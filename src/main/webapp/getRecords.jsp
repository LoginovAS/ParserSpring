<%@ page language="java" %>

<html>
<head>
    <title>ESK363 log records</title>
    <script type="text/javascript">
    	function changeFormState(){
    		var selectBox = document.getElementById("dates");
    		var startDateInput = document.getElementById("startDate");
    		var endDateInput = document.getElementById("endDate");
    		var selectedOptValue = selectBox.options[selectBox.selectedIndex].value;

    		if (selectedOptValue == "custom"){
    			startDateInput.readOnly = false;
    			endDateInput.readOnly = false;
    		} else {
    			startDateInput.readOnly = true;
    			endDateInput.readOnly = true;
    		}

    		if (selectedOptValue != "custom"){
    			var d = new Date("10.16.2016 05:00:17");
    			endDateInput.value = d.toLocaleString().replace(",","");
    			d.setMinutes(d.getMinutes() - selectedOptValue)
    			startDateInput.value = d.toLocaleString().replace(",","");
    		}
    	}
    </script>
</head>
<body>
    <form method="POST" action="getRecords.do">
        Select time:
    	<select id="dates" name="dates" onchange="changeFormState();">
    		<option value="1">1 minute</option>
    		<option value="5" selected>5 minutes</option>
    		<option value="10">10 minutes</option>
    		<option value="30">30 minutes</option>
    		<option value="60">1 hour</option>
    		<option value="custom">Customize date</option>
    	</select>
    	<br>
        <br>
	    <input id="startDate" type="text" name="startDate" readOnly="true">
	    <input id="endDate" type="text" name="endDate" readOnly="true">
        <button name="submit">Submit</button>
    </form>
</body>
<html>