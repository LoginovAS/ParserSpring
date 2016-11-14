<%@ page language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.JSONObject" %>

<html>
<head>
    <title>ESK363 log records</title>

    <%
        JSONObject records = new JSONObject();
        if (request.getAttribute("records") != null)
            records = (JSONObject) request.getAttribute("records");
    %>

    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

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
    			var d = new Date();
    			var cdt_offset = 9;
    			d.setHours(d.getHours() - cdt_offset);
    			endDateInput.value = d.toLocaleString().replace(",","");
    			d.setMinutes(d.getMinutes() - selectedOptValue);
    			startDateInput.value = d.toLocaleString().replace(",","");
    		}
    	}

    	    // Load the Visualization API and the AreaChart package.
            google.charts.load('current', {'packages':['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
              var jsonData = <%= records %>;

              // Create data table out of JSON data loaded from server.
              var data = new google.visualization.DataTable(jsonData);

              // Instantiate and draw chart, passing in some options.
              var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
              chart.draw(data, {width: 1600, height: 400});
            }

    </script>
</head>
<body>
    <form method="POST" action="getRecords">
        Select time (CDT TimeZone):
    	<select id="dates" name="dates" onchange="changeFormState();">
    		<option value="1">1 minute</option>
    		<option value="5" selected>5 minutes</option>
    		<option value="10">10 minutes</option>
    		<option value="30">30 minutes</option>
    		<option value="60">1 hour</option>
    		<option value="360">6 hours</option>
    		<option value="720">12 hours</option>
    		<option value="1440">1 day</option>
    		<option value="custom">Customize date</option>
    	</select>
    	<br>
        <br>
	    <input id="startDate" type="text" name="startDate" readOnly="true">
	    <input id="endDate" type="text" name="endDate" readOnly="true">
        <button name="submit">Submit</button>
    </form>
    <br/>
        <!--Div that will hold the area chart-->
        <div id="chart_div"></div>
</body>
<html>