<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.servlet.jsp.jstl.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bid Submitted</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #ffffa0; /* Bright yellow background */
            padding: 30px;
            border: 1px solid #ccc;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 450px;
            text-align: center;
        }
        h1 {
            font-size: 2em;
            color: #333;
            margin-bottom: 5px;
        }
        .message {
            margin-bottom: 25px;
            font-size: 1em;
        }
        .data-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff; /* White background for table */
            border: 1px solid #000;
        }
        .data-table th, .data-table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }
        .data-table th {
            width: 35%;
            font-weight: bold;
            background-color: #e0e0e0;
        }
        .data-table td {
            font-weight: bold;
        }
        .item-name {
            font-size: 1.5em;
            font-weight: bold;
            margin: 15px 0;
            color: #000;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Bid Submitted</h1>
        
        <div class="message">
            Your bid is now active. If your bid is successful, you will be notified within 24 hours of the close of bidding.
        </div>

        <div class="item-name">${bidData.itemName}</div>

        <table class="data-table">
            <tr>
                <th>Item ID:</th>
                <td>${bidData.itemId}</td>
            </tr>
            <tr>
                <th>Name:</th>
                <td>${bidData.yourName}</td>
            </tr>
            <tr>
                <th>Email address:</th>
                <td>${bidData.emailAddress}</td>
            </tr>
            <tr>
                <th>Bid price:</th>
                <td>Rs${bidData.amountBid}</td>
            </tr>
            <tr>
                <th>Auto-increment price:</th>
                <td>${bidData.autoIncrement ? 'true' : 'false'}</td>
            </tr>
        </table>
    </div>

</body>
</html>
