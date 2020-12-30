<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stations</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<h2>ALL STATIONS</h2>
<div>
    <form name="addStation" method="post" action="#">
        <label for="name">new station name:</label>
        <input id="name" type="text" placeholder="station name"
               minlength="3" maxlength="50" autocomplete="off" required><br>
        <label for="confirm_name">confirm name:</label>
        <input type="text" id="confirm_name" placeholder="confirm name" required>
        <input type="submit" value="create">
    </form>
</div>

<div>
    <ul class="stations">
        <li>
            <span>Moscow</span>
            <button data-station-id="1">delete</button>
        </li>
        <li>
            <span>New-York</span>
            <button data-station-id="2">delete</button>
        </li>
        <li>Warsaw</li>
        <li>...</li>
    </ul>
</div>

<br><button id="suk">stations</button>

<script src="resources/js/stations.js"></script>
<script src="resources/js/confirm-two-inputs.js"></script>
<script>window.confirmTwoInputs('name', 'confirm_name', 'Names')</script>


</body>
</html>
