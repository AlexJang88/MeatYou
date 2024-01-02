<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sales Graph</title>
    <!-- Include Chart.js from CDN -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div style="width: 600px; height: 400px;">
        <canvas id="sales-chart"></canvas>
    </div>
    <script>
        var ctx = document.getElementById('sales-chart').getContext('2d');
        var salesChart = new Chart(ctx, {
            type: 'line', // The type of chart we want to create
            data: { // Data for our dataset
                labels: ['January', 'February', 'March', 'April', 'May', 'June'], // Example labels
                datasets: [{
                    label: 'Sales', // Name of the data
                    backgroundColor: 'rgb(255, 99, 132)', // Color of the dots
                    borderColor: 'rgb(255, 99, 132)', // Color of the line
                    data: [0, 10000, 5000, 15000, 20000, 30000], // Example sales data
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Amount in USD'
                        }
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: 'Monthly Sales Data'
                    },
                    legend: {
                        display: true
                    }
                }
            }
        });
    </script>
</body>
</html>
